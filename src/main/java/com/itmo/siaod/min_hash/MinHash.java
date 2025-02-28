package com.itmo.siaod.min_hash;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_functions.UniversalLinearHashFunction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class MinHash implements IMinHash {

    private final static Double ERROR = 0.05;
    private final static Double ERROR_PROBABILITY = 0.05;
    private ArrayList<UniversalLinearHashFunction> functions;

    public MinHash(){
        this.functions = new ArrayList<>();
        int functionsNumber = calculateFunctionsNumber(ERROR, ERROR_PROBABILITY);
        for (int i = 0; i < functionsNumber; i++) {
            this.functions.add(new UniversalLinearHashFunction());
        }
    }

    @Override
    public ArrayList<Integer> minHash(LinkedHashSet<Integer> x) throws TooBigNumberException {
        if (x == null) return null;
        ArrayList<Integer> minHashes = new ArrayList<>();
        for (Integer elem : x) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < functions.size(); j++){
                Long hash = this.functions.get(j).hash(elem);
                if (hash < min) min = Math.toIntExact(hash);
            }
            minHashes.add(min);
        }
        return minHashes;
    }

    private static Integer calculateFunctionsNumber(Double error, Double probability) {
        return (int) (1 / Math.pow(error, 2) * Math.log(2 / probability));
    }

}
