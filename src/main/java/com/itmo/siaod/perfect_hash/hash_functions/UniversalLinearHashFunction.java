package com.itmo.siaod.perfect_hash.hash_functions;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.prime_numbers.IPrimeGenerator;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;

import java.util.*;

public class UniversalLinearHashFunction implements IUniversalHashFunction {
    private Long a;
    private Long b;
    private Long p;
    private Long m;

    public UniversalLinearHashFunction(List<Integer> allPossibleKeys, Integer hashTableSize) throws TooBigNumberException {
        int maxPossibleKey = allPossibleKeys.stream().max(Comparator.naturalOrder()).get();

        p = IPrimeGenerator.findNextPrime((long) maxPossibleKey);
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
        a = RandomSiaod.nextInt() % p;
        if (a == 0){
            a = 1L;
        }
        if (a < 0){
            throw new TooBigNumberException("a number is negative.");
        }
        b = RandomSiaod.nextInt() % p;
        if (b < 0){
            throw new TooBigNumberException("b number is negative.");
        }
    }

}
