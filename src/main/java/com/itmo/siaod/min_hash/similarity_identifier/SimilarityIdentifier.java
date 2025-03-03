package com.itmo.siaod.min_hash.similarity_identifier;

import com.itmo.siaod.min_hash.hash_tables.IHashTableSiaod;
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
        this.minHash = new MinHash();

        this.signatures = sets.stream().map(set -> {
            try {
                return minHash.getSignatureOf(set);
            } catch (TooBigNumberException e) {
                throw new RuntimeException(e);
            }
        }).toList();
        this.preliminarySimilarSignaturesIndices = calculatePreliminarySimilarSetsIndices();
    }

    protected List<List<Integer>> calculatePreliminarySimilarSetsIndices() {
        IHashTableSiaod hashTable = ISimilarityIdentifier.distributeSignatures(signatures);
        return hashTable.getAllEntries().stream()
                .map(listOfEntries -> listOfEntries.stream()
                        .map(IEntry::getValue)
                        .toList()
                ).toList();
    }

    @Override
    public boolean isSimilar(Set<Integer> a, Set<Integer> b) throws TooBigNumberException {
        return IJaccardCoef.evalSimilarity(minHash.getSignatureOf(a), minHash.getSignatureOf(b)) >= 0.8;
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
        return res.toString();
    }
}
