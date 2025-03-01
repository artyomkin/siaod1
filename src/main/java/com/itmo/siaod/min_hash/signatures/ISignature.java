package com.itmo.siaod.min_hash.signatures;

import java.util.ArrayList;
import java.util.List;

public interface ISignature {

    List<Integer> getMinHashes();

    static Integer countIntersected(ISignature a, ISignature b) {
        List<Integer> intersection = new ArrayList<>(a.getMinHashes());
        intersection.retainAll(b.getMinHashes());
        return intersection.size();
    }

    static Integer countUnited(ISignature a, ISignature b) {
        List<Integer> union = new ArrayList<>(a.getMinHashes());
        union.addAll(b.getMinHashes());
        return union.size();
    }
}
