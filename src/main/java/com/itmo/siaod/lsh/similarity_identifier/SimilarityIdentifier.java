package com.itmo.siaod.lsh.similarity_identifier;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.lsh.hash_tables.HashTableSiaod;
import com.itmo.siaod.lsh.hash_tables.IHashTableSiaod;
import com.itmo.siaod.lsh.hash_tables.bands.IBand;
import com.itmo.siaod.lsh.hash_tables.bands.IBandToHashKeyMapper;
import com.itmo.siaod.lsh.hash_tables.bands.IBander;
import com.itmo.siaod.lsh.model.Point;
import com.itmo.siaod.lsh.lsh.LSH;
import com.itmo.siaod.lsh.model.PointCenterer;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.ArrayList;
import java.util.List;

public class SimilarityIdentifier implements ISimilarityIdentifier {

    private List<List<Integer>> preliminarySimilarSignaturesIndices;
    private List<List<Boolean>> signatures;
    private LSH lsh;
    private List<Point> centeredPoints;
    private PointCenterer pointCenterer;

    public SimilarityIdentifier(List<Point> points, List<Double> timings) {
        this.pointCenterer = new PointCenterer(points);
        this.centeredPoints = this.pointCenterer.getCenteredPoints();
        List<Point> centeredPoints = this.pointCenterer.getCenteredPoints();
        this.lsh = new LSH(centeredPoints);
        this.signatures = this.lsh.getSignatures();
        this.preliminarySimilarSignaturesIndices = calculatePreliminarySimilarIndices(timings);
    }

    protected List<List<Integer>> calculatePreliminarySimilarIndices(List<Double> timings) {
        IHashTableSiaod hashTable = distributeSignatures(signatures, timings);
        return hashTable.getAllEntries().stream()
                .map(listOfEntries -> listOfEntries.stream()
                        .map(IEntry::getValue)
                        .toList()
                ).toList();
    }

    @Override
    public List<List<Integer>> getPreliminarySimilarSetsIndices() {
        return this.preliminarySimilarSignaturesIndices;
    }

    //public List<List<Point>> distinctPoints(List<List<Integer>> indices) {
    //    List<List<Point>> res = new ArrayList<>();
    //    for (List<Integer> curIndices : indices) {
    //        List<Point> points = new ArrayList<>();
    //        if (curIndices.size() < 2) continue;
    //        for (Integer index : curIndices){
    //            points.add(this.pointCenterer.uncenterPoint(this.centeredPoints.get(index)));
    //        }
    //        res.add(points);
    //    }
    //}

    @Override
    public String toString() {
        List<String> res = new ArrayList<>();
        for (List<Integer> curSimilarIndices : preliminarySimilarSignaturesIndices) {
            if (curSimilarIndices.size() < 2) continue;
            ArrayList<String> rowArr = new ArrayList<>();
            for (Integer index : curSimilarIndices){
                rowArr.add(this.pointCenterer.uncenterPoint(this.centeredPoints.get(index)).toString());
            }
            res.add(String.join(", ", rowArr));
        }
        //List<String> distinctArr = res.stream().distinct().toList();
        List<String> distinctArr = res;
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : distinctArr){
            stringBuilder.append(s).append("\n");
        }
        String s = stringBuilder.toString();
        return s.isEmpty() ? "No similarities found" : s;
    }

    protected static IHashTableSiaod distributeSignatures(List<List<Boolean>> signatures, List<Double> timings) {
        //Double start = (double) System.currentTimeMillis();
        List<IBand> bands = IBander.splitIntoBands(signatures);
        //Double splitBandsEnd = (double) System.currentTimeMillis();
        List<List<Integer>> bandsHashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        //Double mapHashKeysEnd = (double) System.currentTimeMillis();
        IHashTableSiaod hashTable = new HashTableSiaod(10 * bands.size() * signatures.size());
        //Double hashTableInitEnd = (double) System.currentTimeMillis();
        for (int i = 0; i < bandsHashKeys.size(); i++){
            List<Integer> curBandHashKeys = bandsHashKeys.get(i);
            for (int signatureIndex = 0; signatureIndex < signatures.size(); signatureIndex++) {
                try {
                    hashTable.put(curBandHashKeys.get(signatureIndex), signatureIndex);
                } catch (TooBigNumberException e) {
                    throw new RuntimeException();
                }
            }
        }
        Double distributeEnd = (double) System.currentTimeMillis();
        //double splitBands = splitBandsEnd - start;
        //double mapHashKeys = mapHashKeysEnd - splitBandsEnd;
        //double hashTableInit = hashTableInitEnd - mapHashKeysEnd;
        //double distribute = distributeEnd - hashTableInitEnd;
        //timings.addAll(List.of(splitBands, mapHashKeys, hashTableInit, distribute));
        return hashTable;
    }


}
