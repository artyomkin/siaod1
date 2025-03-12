package com.itmo.siaod.lsh.similarity_identifier;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.lsh.hash_tables.HashTableSiaod;
import com.itmo.siaod.lsh.hash_tables.IHashTableSiaod;
import com.itmo.siaod.lsh.hash_tables.bands.IBand;
import com.itmo.siaod.lsh.hash_tables.bands.IBandToHashKeyMapper;
import com.itmo.siaod.lsh.hash_tables.bands.IBander;
import com.itmo.siaod.lsh.model.Point;
import com.itmo.siaod.lsh.signatures.LSH;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.List;

public class SimilarityIdentifier implements ISimilarityIdentifier {

    private List<List<Integer>> preliminarySimilarSignaturesIndices;
    private List<List<Boolean>> signatures;
    private LSH lsh;

    public SimilarityIdentifier(List<Point> points) {
        this.lsh = new LSH(points);
        this.signatures = this.lsh.getSignatures();
        this.preliminarySimilarSignaturesIndices = calculatePreliminarySimilarIndices();
    }

    protected List<List<Integer>> calculatePreliminarySimilarIndices() {
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

    protected static IHashTableSiaod distributeSignatures(List<List<Boolean>> signatures) {
        List<IBand> bands = IBander.splitIntoBands(signatures);
        List<List<Integer>> bandsHashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        IHashTableSiaod hashTable = new HashTableSiaod(10 * bands.size() * signatures.size());
        for (List<Integer> curBandHashKeys : bandsHashKeys) {
            for (int signatureIndex = 0; signatureIndex < signatures.size(); signatureIndex++) {
                try {
                    hashTable.put(curBandHashKeys.get(signatureIndex), signatureIndex);
                } catch (TooBigNumberException e) {
                    throw new RuntimeException();
                }
            }
        }
        return hashTable;
    }
}
