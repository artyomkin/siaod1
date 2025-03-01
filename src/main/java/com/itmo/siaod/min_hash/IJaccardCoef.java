package com.itmo.siaod.min_hash;

import com.itmo.siaod.min_hash.signatures.ISignature;

public interface IJaccardCoef {
    static Double evalSimilarity(ISignature a, ISignature b){
        return (double) (ISignature.countIntersected(a, b) / ISignature.countUnited(a, b));
    }
}
