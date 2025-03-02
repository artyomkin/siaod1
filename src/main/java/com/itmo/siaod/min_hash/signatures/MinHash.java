package com.itmo.siaod.min_hash.signatures;
import com.itmo.siaod.min_hash.hash_tables.bands.IBander;
import com.itmo.siaod.min_hash.hash_functions.UniversalLinearHashFunction;

import java.util.*;

public class MinHash implements IMinHash {

    protected ArrayList<UniversalLinearHashFunction> functions;

    public MinHash(){
        this.functions = new ArrayList<>();
        int functionsNumber = calculateFunctionsNumber();
        for (int i = 0; i < functionsNumber; i++) {
            this.functions.add(new UniversalLinearHashFunction());
        }
    }

    @Override
    public ISignature getSignatureOf(Set<Integer> x) {
        if (x == null) return null;
        return new Signature(this.functions.stream().map(func -> getMinHashOf(x, func)).toList());
    }

    protected static Integer getMinHashOf(Set<Integer> x, UniversalLinearHashFunction function) {
        if (x == null || function == null) return null;
        return Math.toIntExact(x.stream().map(function::hash).min(Comparator.naturalOrder()).get());
    }

    protected static Integer calculateFunctionsNumber() {
        return IBander.BAND_NUMBER * IBander.BAND_SIZE;
    }

}
