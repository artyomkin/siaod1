import com.itmo.siaod.lsh.lsh.LSH;
import com.itmo.siaod.lsh.model.Point;
import com.itmo.siaod.lsh.similarity_identifier.ISimilarityIdentifier;
import com.itmo.siaod.lsh.similarity_identifier.SimilarityIdentifier;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.profiler.Profiler;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws TooBigNumberException, CollisionException {
        //Profiler.profilePerfectHash();
        //Profiler pr = new Profiler();
        //ProfilingReport r1 = pr.profileHashTableSiaodOperations(1_000_000, 10_000_000);
        //ProfilingReport r2 = pr.profileHashTableSiaodOperations(2_000_000, 10_000_000);
        //ProfilingReport r3 = pr.profileHashTableSiaodOperations(3_000_000, 10_000_000);
        //ProfilingReport r4 = pr.profileHashTableSiaodOperations(4_000_000, 10_000_000);
        //ProfilingReport r5 = pr.profileHashTableSiaodOperations(5_000_000, 10_000_000);
        //ArrayList<ProfilingReport> reports = new ArrayList<>();
        //reports.add(r1);
        //reports.add(r2);
        //reports.add(r3);
        //reports.add(r4);
        //reports.add(r5);
        //ProfilingReport.print(reports);

        //List<List<List<Double>>> allTimings = new ArrayList<>();
        //int init = 200_000;
        //int iterations = 1;
        //for (Integer i = 0; i < 1; i++){
        //    List<List<Double>> iTimings = new ArrayList<>();
        //    for (int iter = 0; iter < iterations; iter++){
        //        List<Point> points = Point.generateRandomPoints(init * (i + 1), 100_000_000);
        //        List<Double> timings = new ArrayList<>();
        //        ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(points, timings);
        //        iTimings.add(timings);
        //    }
        //    allTimings.add(iTimings);
        //}

        //for (int i = 0; i < allTimings.size(); i++){
        //    System.out.println(avgs(allTimings.get(i)));
        //}

        //List<Point> points = Point.generateRandomPoints(1_00_000, 10_000_000);
        //List<Point> points = List.of(new Point(800,800), new Point(801,801), new Point(1000, 1000));
        //List<Double> timings = new ArrayList<>();
        //ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(points, timings);
        //System.out.println(similarityIdentifier.toString());

        //demoPerfectHash();
        //demoExtendibleHash();
        demoLSH();
    }

    public static List<Double> avgs(List<List<Double>> data){
        List<Double> avgs = new ArrayList<>();
        for (int i = 0; i < data.getFirst().size(); i++){
            avgs.add(0d);
        }
        for (int i = 0; i < data.size(); i++){
            List<Double> row = data.get(i);
            for (int j = 0; j < row.size(); j++){
                avgs.set(j, avgs.get(j) + row.get(j));
            }
        }
        for (int i = 0; i < data.getFirst().size(); i++){
            avgs.set(i, avgs.get(i) / data.size());
        }
        return avgs;
    }

    public static void demoPerfectHash() throws TooBigNumberException, CollisionException {
        List<Integer> possibleKeys = List.of(1,52, 42, 353, 12);
        com.itmo.siaod.perfect_hash.IHashTableSiaod ht = new HashTableSiaod(possibleKeys);
        ht.put(1, 24);
        ht.put(353, 412);
        System.out.println(ht.get(1));
        System.out.println(ht.get(353));
        System.out.println(ht.get(42));
        ht.delete(353);
        System.out.println(ht.get(353));
    }

    public static void demoExtendibleHash() {
        com.itmo.siaod.extendible_hash.IHashTableSiaod ht = new com.itmo.siaod.extendible_hash.hash_tables.HashTableSiaod();
        ht.put(122, 241);
        ht.put(255, 124);
        ht.put(256, 1);
        System.out.println(ht.get(122));
        System.out.println(ht.get(255));
        System.out.println(ht.get(256));
        System.out.println(ht.get(254));
        ht.delete(256);
        System.out.println(ht.get(256));
    }

    public static void demoLSH(){
        List<Point> points = List.of(new Point(0, 0), new Point(1,1), new Point(2,2), new Point(1000,1000));
        ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(points, new ArrayList<>());
        System.out.println(similarityIdentifier.toString());
    }
}