package com.itmo.siaod.profiler;

import java.util.ArrayList;

public class ProfilingReport {
    public Double memoryUsedMB;
    public Double elapsedTimeSeconds;
    public Integer keysCount;
    public Integer maxKey;

    public static void print(ArrayList<ProfilingReport> reps) {
        System.out.format(
                "%20s%20s%20s%20s",
                "Keys count",
                "Maximum key",
                "Memory used (MB)",
                "Elapsed Time (sec)"
        );
        for (ProfilingReport profilingReport : reps){
            System.out.println();
            System.out.format(
                    "%20d%20d%20f%20f",
                    profilingReport.keysCount,
                    profilingReport.maxKey,
                    profilingReport.memoryUsedMB,
                    profilingReport.elapsedTimeSeconds
            );
        }
    }

    public static ProfilingReport toAvg(ArrayList<ProfilingReport> reps){
        ProfilingReport rep = new ProfilingReport();
        rep.memoryUsedMB = reps.stream().mapToDouble(report -> report.memoryUsedMB).average().getAsDouble();
        rep.elapsedTimeSeconds = reps.stream().mapToDouble(report -> report.elapsedTimeSeconds).average().getAsDouble();
        rep.keysCount = (int) reps.stream().mapToInt(report -> report.keysCount).average().getAsDouble();
        rep.maxKey = (int) reps.stream().mapToInt(report -> report.maxKey != null ? report.maxKey : 0).average().getAsDouble();
        return rep;
    }
}
