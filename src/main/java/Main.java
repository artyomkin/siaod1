import com.itmo.siaod.lsh.model.GeneralLine;
import com.itmo.siaod.lsh.model.ILine;
import com.itmo.siaod.lsh.model.Point;
import com.itmo.siaod.lsh.similarity_identifier.ISimilarityIdentifier;
import com.itmo.siaod.lsh.similarity_identifier.SimilarityIdentifier;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.profiler.Profiler;

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

        int init = 1000;
        for (Integer i = 0; i < 10; i++){
            double start = System.currentTimeMillis();
            List<Point> points = Point.generateRandomPoints(init * (i + 1), 100_000_000);
            ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(points);
            double end = System.currentTimeMillis();
            System.out.println(end - start);
        }

    }
}