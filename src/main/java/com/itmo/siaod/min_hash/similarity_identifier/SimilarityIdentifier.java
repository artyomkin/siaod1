package com.itmo.siaod.min_hash.similarity_identifier;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.min_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.min_hash.hash_tables.IHashTableSiaod;
import com.itmo.siaod.min_hash.hash_tables.bands.IBand;
import com.itmo.siaod.min_hash.hash_tables.bands.IBandToHashKeyMapper;
import com.itmo.siaod.min_hash.hash_tables.bands.IBander;
import com.itmo.siaod.min_hash.signatures.IMinHash;
import com.itmo.siaod.min_hash.signatures.ISignature;
import com.itmo.siaod.min_hash.signatures.MinHash;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimilarityIdentifier implements ISimilarityIdentifier {

    private List<List<Integer>> preliminarySimilarSignaturesIndices;
    private List<ISignature> signatures;
    private IMinHash minHash;

    public SimilarityIdentifier(List<Set<Integer>> sets) {
        this.minHash = new MinHash(sets);
        this.signatures = this.minHash.getSignatures();
        this.preliminarySimilarSignaturesIndices = calculatePreliminarySimilarSetsIndices();
    }

    protected List<List<Integer>> calculatePreliminarySimilarSetsIndices() {
        IHashTableSiaod hashTable = distributeSignatures(signatures);
        return hashTable.getAllEntries().stream()
                .map(listOfEntries -> listOfEntries.stream()
                        .map(IEntry::getValue)
                        .toList()
                ).toList();
    }

    @Override
    public List<List<Integer>> getPreliminarySimilarSetsIndices() {
        return this.preliminarySimilarSignaturesIndices;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (List<Integer> curSimilarIndices : preliminarySimilarSignaturesIndices) {
            if (curSimilarIndices.size() < 2) continue;

            res.append(curSimilarIndices).append("\n");
        }
        String s = res.toString();
        return s.isEmpty() ? "No similarities found" : s;
    }

    protected static IHashTableSiaod distributeSignatures(List<ISignature> signatures) {
        List<IBand> bands = IBander.splitIntoBands(signatures);
        List<List<Integer>> bandsHashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        IHashTableSiaod hashTable = new HashTableSiaod(10 * ((int) Math.pow(bands.size() * signatures.size(),2)));
        for (int bandIndex = 0; bandIndex < bandsHashKeys.size(); bandIndex++){
            List<Integer> curBandHashKeys = bandsHashKeys.get(bandIndex);
            for (int signatureIndex = 0; signatureIndex < signatures.size(); signatureIndex++){
                try {
                    hashTable.put(curBandHashKeys.get(signatureIndex), signatureIndex);
                } catch (TooBigNumberException e){
                    throw new RuntimeException();
                }
            }
        }
        return hashTable;
    }
}
