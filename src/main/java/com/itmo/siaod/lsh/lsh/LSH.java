package com.itmo.siaod.lsh.lsh;

import com.itmo.siaod.lsh.hash_tables.HashTableSiaod;
import com.itmo.siaod.lsh.hash_tables.IHashTableSiaod;
import com.itmo.siaod.lsh.hash_tables.bands.IBand;
import com.itmo.siaod.lsh.hash_tables.bands.IBandToHashKeyMapper;
import com.itmo.siaod.lsh.hash_tables.bands.IBander;
import com.itmo.siaod.lsh.model.GeneralLine;
import com.itmo.siaod.lsh.model.ILine;
import com.itmo.siaod.lsh.model.Point;
import com.itmo.siaod.lsh.model.PointCenterer;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.text.CollationKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LSH {

    private List<ILine> lines;
    private List<List<Boolean>> signatures;
    private int NUMBER_OF_LINES = 3000;
    private PointCenterer pointCenterer;
    private IHashTableSiaod hashTable;
    private List<Point> points;

    public LSH(List<Point> points){
        this.points = points;
        double maxX = points.stream().map(p -> Math.abs(p.x)).max(Comparator.naturalOrder()).get();
        double maxY = points.stream().map(p -> Math.abs(p.y)).max(Comparator.naturalOrder()).get();
        double maxDistance = Math.max(maxX, maxY);
        this.lines = GeneralLine.generateLines(NUMBER_OF_LINES, maxDistance);
        this.signatures = new ArrayList<>();
        for (int i = 0; i < points.size(); i++){
            this.signatures.add(new ArrayList<>());
            Point p = points.get(i);
            for (int j = 0; j < lines.size(); j++){
                this.signatures.get(i).add(lines.get(j).getLocation(p.x, p.y).equals(ILine.Location.UPPER));
            }
        }
        this.pointCenterer = new PointCenterer(points);
        this.hashTable = distributeSignatures(this.signatures);
    }

    public List<Point> findKNearestNeighbors(int k, Point p){
        List<Boolean> signature = new ArrayList<>();
        for (ILine line : this.lines){
            signature.add(line.getLocation(p.x, p.y).equals(ILine.Location.UPPER));
        }
        List<IBand> bands = IBander.splitIntoBands(List.of(signature));
        List<MutableIntList> hashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        MutableIntList pointsIndices = IntLists.mutable.empty();
        for (int bandNum = 0; bandNum < hashKeys.size(); bandNum++){
            for (int i = 0; i < hashKeys.get(bandNum).size(); i++){
                try {
                    MutableIntList bucket = (((HashTableSiaod)this.hashTable).getWholeBucket(hashKeys.get(bandNum).get(i)));
                    for (int j = 0; j < bucket.size(); j++){
                        pointsIndices.add(bucket.get(j));
                    }
                } catch (TooBigNumberException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        pointsIndices = pointsIndices.distinct();
        List<Point> candidatesPoints = new ArrayList<>();
        for (int i = 0; i < pointsIndices.size(); i++){
            candidatesPoints.add(this.points.get(pointsIndices.get(i)));
        }
        Collections.sort(candidatesPoints, (p1, p2) -> Double.compare(distance(p1, p), distance(p2, p)));
        if (candidatesPoints.getFirst().equals(p)) {
            candidatesPoints.removeFirst();
        }
        if (candidatesPoints.isEmpty()){
            return null;
        }
        List<Point> nearest = candidatesPoints.subList(0, Math.min(k, candidatesPoints.size()));
        for (int i = 0; i < nearest.size(); i++){
            nearest.set(i, nearest.get(i));
        }
        return nearest;
    }

    protected double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    protected static IHashTableSiaod distributeSignatures(List<List<Boolean>> signatures) {
        List<IBand> bands = IBander.splitIntoBands(signatures);
        List<MutableIntList> bandsHashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        IHashTableSiaod hashTable = new HashTableSiaod(10 * bands.size() * signatures.size());
        for (int i = 0; i < bandsHashKeys.size(); i++){
            MutableIntList curBandHashKeys = bandsHashKeys.get(i);
            for (int signatureIndex = 0; signatureIndex < signatures.size(); signatureIndex++) {
                try {
                    hashTable.put(curBandHashKeys.get(signatureIndex), signatureIndex);
                } catch (TooBigNumberException e) {
                    throw new RuntimeException();
                }
            }
        }
        return hashTable;
    }

}
