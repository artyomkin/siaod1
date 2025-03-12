package com.itmo.siaod.lsh.similarity_identifier;

import java.util.List;

public interface ISimilarityIdentifier {

    //boolean isSimilar(Set<Integer> a, Set<Integer> b) throws TooBigNumberException;

    List<List<Integer>> getPreliminarySimilarSetsIndices();

}
