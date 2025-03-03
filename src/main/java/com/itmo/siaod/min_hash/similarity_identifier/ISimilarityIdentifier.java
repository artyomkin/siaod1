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

    boolean isSimilar(Set<Integer> a, Set<Integer> b) throws TooBigNumberException;

    List<List<Integer>> getPreliminarySimilarSetsIndices();

    static IHashTableSiaod distributeSignatures(List<ISignature> signatures) {
        List<IBand> bands = IBander.splitIntoBands(signatures);
        List<List<Integer>> bandsHashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        IHashTableSiaod hashTable = new HashTableSiaod(bands.size() * signatures.size());
        for (int signatureIndex = 0; signatureIndex < signatures.size(); signatureIndex++) {
            List<Integer> curBandHashKeys = bandsHashKeys.get(signatureIndex);
            for (int bandHashKey : curBandHashKeys) {
                try {
                    hashTable.put(bandHashKey, signatureIndex);
                } catch (TooBigNumberException e){
                    throw new RuntimeException();
                }
            }
        }
        return hashTable;
    }
}
