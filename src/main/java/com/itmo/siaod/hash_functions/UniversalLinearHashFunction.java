package com.itmo.siaod.hash_functions;

import com.itmo.siaod.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.exceptions.TooBigNumberException;
import com.itmo.siaod.prime_numbers.PrimeGenerator;

import java.util.*;

public class UniversalLinearHashFunction implements IUniversalHashFunction {
    private Long a;
    private Long b;
    private Long p;
    private Long m;
    private Random rnd;
    private IPrimeGenerator primeGenerator;

    public UniversalLinearHashFunction(List<Integer> allPossibleKeys, Integer hashTableSize) throws TooBigNumberException {
        this.rnd = new Random();
        this.primeGenerator = new PrimeGenerator();

        int maxPossibleKey = allPossibleKeys.stream().max(Comparator.naturalOrder()).get();

        p = this.primeGenerator.findNextPrime((long) maxPossibleKey);
        if (p == null || p < 0){
            throw new TooBigNumberException("p number is negative.");
        }
        m = (long) hashTableSize;

        shuffleCoefficients();
    }

    @Override
    public Long hash(Integer key) throws TooBigNumberException {
        Long res = ((a * key + b) % p) % m;
        if (res < 0)
            throw new TooBigNumberException("hash is negative.");
        return res;
    }

    protected void shuffleCoefficients() throws TooBigNumberException {
        a = (rnd.nextInt() & Integer.MAX_VALUE) % p;
        if (a == 0){
            a = 1L;
        }
        if (a < 0){
            throw new TooBigNumberException("a number is negative.");
        }
        b = (rnd.nextInt() & Integer.MAX_VALUE) % p;
        if (b < 0){
            throw new TooBigNumberException("b number is negative.");
        }
    }

}
