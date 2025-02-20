package com.itmo.siaod;

import com.itmo.siaod.exceptions.CannotFindNextPrimeException;

import java.util.List;
import java.util.Random;

public class UniversalLinearHashFunction implements IUniversalHashFunction {
    private Integer a;
    private Integer b;
    private Integer p;
    private Integer m;
    private Random rnd;
    private IPrimeGenerator primeGenerator;

    public UniversalLinearHashFunction(List<Integer> allPossibleKeys, Integer hashTableSize) throws CannotFindNextPrimeException {
        this.rnd = new Random();
        this.primeGenerator = new PrimeGenerator();

        List<Integer> allUniquePossibleKeys = allPossibleKeys.stream().distinct().toList();
        Integer allUniquePossibleKeysCount = allUniquePossibleKeys.size();

        p = Math.toIntExact(this.primeGenerator.findNextPrime(Long.valueOf(allUniquePossibleKeysCount)));
        if (p == null){
            throw new CannotFindNextPrimeException();
        }

        a = (rnd.nextInt() & Integer.MAX_VALUE) % p;
        if (a == 0){
            a = 1;
        }
        b = (rnd.nextInt() & Integer.MAX_VALUE) % p;
        m = hashTableSize;
    }

    @Override
    public Integer hash(Integer key) {
        return ((a * key + b) % p) % m;
    }

}
