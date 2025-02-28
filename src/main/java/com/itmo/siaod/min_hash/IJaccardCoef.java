package com.itmo.siaod.min_hash;

public interface IJaccardCoef {
    static Double evalSimilarity(ISignature a, ISignature b){
        return (double) (ISignature.countIntersected(a, b) / ISignature.countUnited(a, b));
    }
}
