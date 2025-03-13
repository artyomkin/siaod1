import com.itmo.siaod.lsh.model.GeneralLine;
import com.itmo.siaod.lsh.model.ILine;
import com.itmo.siaod.lsh.model.Point;
import com.itmo.siaod.lsh.similarity_identifier.ISimilarityIdentifier;
import com.itmo.siaod.lsh.similarity_identifier.SimilarityIdentifier;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.profiler.Profiler;
import com.itmo.siaod.perfect_hash.profiler.ProfilingReport;

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
        //int init = 100;
        //int iterations = 30;
        //for (Integer i = 0; i < 20; i++){
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
        List<Point> points = Point.generateRandomPoints(1_000_000, 100_000_000);
        List<Double> timings = new ArrayList<>();
        ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(points, timings);
        System.out.println(similarityIdentifier.toString());
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
}