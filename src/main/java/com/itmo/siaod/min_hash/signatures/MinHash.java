package com.itmo.siaod.min_hash.signatures;

import java.util.*;

public class MinHash implements IMinHash {

    private List<List<Integer>> universalSetPermutations;
    private Set<Integer> universalSet;
    private List<ISignature> signatures;

    public MinHash(List<Set<Integer>> sets){
        this.universalSet = toUniversalSet(sets);
        this.universalSetPermutations = permuteSetNTimes(this.universalSet, 20);
        this.signatures = calculateSignatures(this.universalSetPermutations, sets);
    }

    @Override
    public List<ISignature> getSignatures() {
        return this.signatures;
    }

    protected static List<ISignature> calculateSignatures(List<List<Integer>> universalSetPermutations, List<Set<Integer>> originalSets){
        List<ISignature> signatures = new ArrayList<>();
        return originalSets.stream()
                .map(set -> calculateSignature(universalSetPermutations, set))
                .toList();
    }

    protected static ISignature calculateSignature(List<List<Integer>> universalSetPermutations, Set<Integer> originalSet){
        List<Integer> signatureNumbers = new ArrayList<>();
        for (List<Integer> universalSetPermutation : universalSetPermutations){
            signatureNumbers.add(getMinHashOf(originalSet, universalSetPermutation));
        }
        return new Signature(signatureNumbers);
    }

    protected static Set<Integer> toUniversalSet(List<Set<Integer>> sets){
        Set<Integer> result = new HashSet<>();
        for (Set<Integer> curSet : sets){
            result.addAll(curSet);
        }
        return result;
    }

    protected static Integer getMinHashOf(Set<Integer> originalSet, List<Integer> universalSetPermutation) {
        if (originalSet == null || universalSetPermutation == null) return null;
        for (Integer universalSetElem : universalSetPermutation){
            for (Integer originalSetElem : originalSet){
                if (originalSetElem.equals(universalSetElem)){
                    return originalSetElem;
                }
            }
        }
        throw new RuntimeException("Universal set doesn't contain original set element.");
    }

    protected static List<List<Integer>> permuteSetNTimes(Set<Integer> set, Integer n){
        List<List<Integer>> permutations = new ArrayList<>();
        for (int i = 0; i < n; i++){
            permutations.add(permute(set));
        }
        return permutations;
    }

    protected static List<Integer> permute(Set<Integer> set){
        List<Integer> l = new ArrayList<>(set.stream().toList());
        Collections.shuffle(l);
        return l;
    }

}
