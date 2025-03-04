package com.itmo.siaod.min_hash.similarity_identifier;

import com.itmo.siaod.min_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.min_hash.hash_tables.IHashTableSiaod;
import com.itmo.siaod.min_hash.hash_tables.bands.IBand;
import com.itmo.siaod.min_hash.hash_tables.bands.IBandToHashKeyMapper;
import com.itmo.siaod.min_hash.hash_tables.bands.IBander;
import com.itmo.siaod.min_hash.signatures.ISignature;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.List;
import java.util.Set;

public interface ISimilarityIdentifier {

    //boolean isSimilar(Set<Integer> a, Set<Integer> b) throws TooBigNumberException;

    List<List<Integer>> getPreliminarySimilarSetsIndices();

}
