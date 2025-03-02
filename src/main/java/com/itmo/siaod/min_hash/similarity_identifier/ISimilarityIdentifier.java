package com.itmo.siaod.min_hash.similarity_identifier;

import com.itmo.siaod.extendible_hash.IHashTableSiaod;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.extendible_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.min_hash.hash_tables.bands.IBand;
import com.itmo.siaod.min_hash.hash_tables.bands.IBandToHashKeyMapper;
import com.itmo.siaod.min_hash.hash_tables.bands.IBander;
import com.itmo.siaod.min_hash.signatures.ISignature;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.List;
import java.util.Set;

public interface ISimilarityIdentifier {

    boolean isSimilar(Set<Integer> a, Set<Integer> b) throws TooBigNumberException;

    List<List<Integer>> getPreliminarySimilarSetsIndices();

    static IHashTableSiaod distributeSignatures(List<ISignature> signatures) {
        List<IBand> bands = IBander.splitIntoBands(signatures);
        List<List<Integer>> bandsHashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        IHashTableSiaod hashTable = new HashTableSiaod();
        for (int signatureIndex = 0; signatureIndex < signatures.size(); signatureIndex++) {
            List<Integer> bandHashKeys = bandsHashKeys.get(signatureIndex);
            for (int bandHashKey : bandHashKeys) {
                hashTable.put(bandHashKey, signatureIndex);
            }
        }
        return hashTable;
    }
}
