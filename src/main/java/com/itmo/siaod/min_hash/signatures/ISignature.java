package com.itmo.siaod.min_hash.signatures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface ISignature {

    List<Integer> getMinHashes();

    static Integer countIntersected(ISignature a, ISignature b) {
        Set<Integer> intersection = new HashSet<>(a.getMinHashes());
        intersection.retainAll(b.getMinHashes());
        return intersection.size();
    }

    static Integer countUnited(ISignature a, ISignature b) {
        Set<Integer> union = new HashSet<>(a.getMinHashes());
        union.addAll(b.getMinHashes());
        return union.size();
    }
}
