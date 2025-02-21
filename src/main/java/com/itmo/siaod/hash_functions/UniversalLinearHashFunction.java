package com.itmo.siaod.hash_functions;

import com.itmo.siaod.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.exceptions.TooBigNumberException;
import com.itmo.siaod.prime_numbers.PrimeGenerator;

import java.util.*;

public class UniversalLinearHashFunction implements IUniversalHashFunction {
    private Integer a;
    private Integer b;
    private Integer p;
    private Integer m;
    private Random rnd;
    private IPrimeGenerator primeGenerator;

    public UniversalLinearHashFunction(List<Integer> allPossibleKeys, Integer hashTableSize) throws TooBigNumberException {
        this.rnd = new Random();
        this.primeGenerator = new PrimeGenerator();

        int maxPossibleKey = allPossibleKeys.stream().max(Comparator.naturalOrder()).get();

        p = Math.toIntExact(this.primeGenerator.findNextPrime((long) maxPossibleKey));
        if (p == null){
            throw new TooBigNumberException();
        }
        m = hashTableSize;

        shuffleCoefficients();
    }

    @Override
    public Integer hash(Integer key) {
        return ((a * key + b) % p) % m;
    }

    protected void shuffleCoefficients(){
        a = (rnd.nextInt() & Integer.MAX_VALUE) % p;
        if (a == 0){
            a = 1;
        }
        b = (rnd.nextInt() & Integer.MAX_VALUE) % p;
    }

}
