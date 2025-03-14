package com.itmo.siaod.profiler;

import com.itmo.siaod.lsh.lsh.LSH;
import com.itmo.siaod.lsh.model.Point;
import com.itmo.siaod.lsh.similarity_identifier.ISimilarityIdentifier;
import com.itmo.siaod.lsh.similarity_identifier.SimilarityIdentifier;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.List;

import org.openjdk.jol.info.ClassLayout;


public class Profiler {

    private OperatingSystemMXBean operatingSystemBean = ManagementFactory.getOperatingSystemMXBean();

    public long getMemoryUsage(Object o){
        return ClassLayout.parseInstance(o).instanceSize();
    }

    public HashTableSiaod createHashTableSiaod(Integer keysCount, Integer maxKey) throws TooBigNumberException {

        ArrayList<Integer> possibleKeys = new ArrayList<>();
        int possibleKeysSize = RandomSiaod.nextInt() % keysCount + 1;
        for (int i = 0; i < possibleKeysSize; i++) {
            possibleKeys.add(RandomSiaod.nextInt() % maxKey);
        }
        List<Integer> uniqPossibleKeys = possibleKeys.stream().distinct().toList();
        return new HashTableSiaod(uniqPossibleKeys);

    }

    public ProfilingReport profileHashTableSiaodBuild(Integer keysCount, Integer maxKey) throws TooBigNumberException {
        long timeStart = System.currentTimeMillis();
        HashTableSiaod hashTableSiaod = createHashTableSiaod(keysCount, maxKey);
        long timeEnd = System.currentTimeMillis();

        ProfilingReport rep = new ProfilingReport();
        rep.elapsedTimeSeconds = (timeEnd - timeStart) / 1000d;
        rep.memoryUsedMB = getMemoryUsage(hashTableSiaod.buckets.toArray()) / Math.pow(2, 20);
        rep.keysCount = keysCount;
        rep.maxKey = maxKey;
        return rep;
    }

    public ProfilingReport profileHashTableSiaodOperations(Integer keysCount, Integer maxKey) throws TooBigNumberException, CollisionException {
        HashTableSiaod hashTableSiaod = createHashTableSiaod(keysCount, maxKey);

        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            int rndIndex = RandomSiaod.nextInt() % hashTableSiaod.possibleKeys.size();
            int key = hashTableSiaod.possibleKeys.get(rndIndex);
            int val = RandomSiaod.nextInt() % 1000 + 1000;

            hashTableSiaod.put(key, val);
        }
        long timeEnd = System.currentTimeMillis();
        ProfilingReport rep = new ProfilingReport();
        rep.elapsedTimeSeconds = (timeEnd - timeStart) / 1000d;
        rep.memoryUsedMB = 0d;
        rep.keysCount = keysCount;
        rep.maxKey = maxKey;
        return rep;
    }

    public static void profilePerfectHash() throws TooBigNumberException, CollisionException {
        Profiler profiler = new Profiler();
        int keysCountInit = 1_000_000;
        int maxKeyInit = 1_000_000;
        int iterations = 1;
        int steps = 1000;

        ArrayList<ProfilingReport> avgReps = new ArrayList<>();
        for (int i = 1; i < iterations + 1; i++) {
            int keysCount = keysCountInit * i;
            int maxKey = maxKeyInit * i;
            ArrayList<ProfilingReport> iterationReps = new ArrayList<>();
            System.out.println(iterations - i + " profiling iterations left..");
            for (int j = 0; j < steps; j++) {
                //ProfilingReport rep = profiler.profileHashTableSiaodBuild(keysCount, maxKey);
                ProfilingReport rep = profiler.profileHashTableSiaodOperations(keysCount, maxKey);
                iterationReps.add(rep);
            }
            avgReps.add(ProfilingReport.toAvg(iterationReps));
        }
        ProfilingReport.print(avgReps);
    }

    public ProfilingReport profileExtendibleHashTableSiaodOperations(Integer keysCount)  {
        com.itmo.siaod.extendible_hash.IHashTableSiaod hashTableSiaod = new com.itmo.siaod.extendible_hash.hash_tables.HashTableSiaod();

        long timeStart = System.currentTimeMillis();
        for (int i = 0; i < keysCount; i++) {
            int key = RandomSiaod.nextInt() % 1_000_000 + 1000;
            int val = RandomSiaod.nextInt() % 1_000_000 + 1000;

            hashTableSiaod.put(key, val);
        }
        long timeEnd = System.currentTimeMillis();
        ProfilingReport rep = new ProfilingReport();
        rep.elapsedTimeSeconds = (timeEnd - timeStart) / 1000d;
        rep.memoryUsedMB = getMemoryUsage(hashTableSiaod.getAllEntries().toArray()) / Math.pow(2, 20);
        rep.keysCount = keysCount;
        return rep;

    }

    public static void profileExtendibleHash() {
        Profiler profiler = new Profiler();
        int keysCountInit = 1_000_000;
        int iterations = 1;
        int steps = 1000;

        ArrayList<ProfilingReport> avgReps = new ArrayList<>();
        for (int i = 1; i < iterations + 1; i++) {
            int keysCount = keysCountInit * i;
            ArrayList<ProfilingReport> iterationReps = new ArrayList<>();
            System.out.println(iterations - i + " profiling iterations left..");
            for (int j = 0; j < steps; j++) {
                ProfilingReport rep = profiler.profileExtendibleHashTableSiaodOperations(keysCount);
                iterationReps.add(rep);
            }
            avgReps.add(ProfilingReport.toAvg(iterationReps));
        }
        ProfilingReport.print(avgReps);
    }

    public ProfilingReport profileLSHSearch(Integer pointsCount)  {
        List<Point> points = Point.generateRandomPoints(pointsCount, 100_000_000);
        List<Double> timings = new ArrayList<>();
        long timeStart = System.currentTimeMillis();
        LSH lsh = new LSH(points);
        ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(points, timings);
        long timeEnd = System.currentTimeMillis();

        ProfilingReport rep = new ProfilingReport();
        rep.elapsedTimeSeconds = (timeEnd - timeStart) / 1000d;
        rep.memoryUsedMB = getMemoryUsage(similarityIdentifier.getPreliminarySimilarSetsIndices().toArray()) / Math.pow(2, 20);
        rep.keysCount = pointsCount;
        return rep;

    }

    public static void profileLSH() {
        Profiler profiler = new Profiler();
        int pointsCountInit = 100_000;
        int iterations = 1;
        int steps = 1000;

        ArrayList<ProfilingReport> avgReps = new ArrayList<>();
        for (int i = 1; i < iterations + 1; i++) {
            int pointsCount = pointsCountInit * i;
            ArrayList<ProfilingReport> iterationReps = new ArrayList<>();
            System.out.println(iterations - i + " profiling iterations left..");
            for (int j = 0; j < steps; j++) {
                ProfilingReport rep = profiler.profileLSHSearch(pointsCount);
                iterationReps.add(rep);
            }
            avgReps.add(ProfilingReport.toAvg(iterationReps));
        }
        ProfilingReport.print(avgReps);
    }
}
