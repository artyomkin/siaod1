package com.itmo.siaod.min_hash.similarity_identifier;

import com.itmo.siaod.min_hash.signatures.ISignature;

public interface IJaccardCoef {
    static Double evalSimilarity(ISignature a, ISignature b){
        return (double) (ISignature.countIntersected(a, b) / ISignature.countUnited(a, b));
    }
}
