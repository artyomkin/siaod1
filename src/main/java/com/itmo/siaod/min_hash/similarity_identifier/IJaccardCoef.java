package com.itmo.siaod.min_hash.similarity_identifier;

import com.itmo.siaod.min_hash.signatures.ISignature;

public interface IJaccardCoef {
    static Double evalSimilarity(ISignature a, ISignature b){
        return ISignature.countIntersected(a, b) / ((double) ISignature.countUnited(a, b));
    }
}
