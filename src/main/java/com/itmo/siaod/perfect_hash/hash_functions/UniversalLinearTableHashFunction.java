package com.itmo.siaod.perfect_hash.hash_functions;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.prime_numbers.IPrimeGenerator;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.eclipse.collections.api.list.primitive.MutableIntList;

import java.util.*;

public class UniversalLinearTableHashFunction implements IUniversalHashFunction {
    private long a;
    private long b;
    private long p;
    private long m;

    public UniversalLinearTableHashFunction(MutableIntList allPossibleKeys, int hashTableSize) throws TooBigNumberException {
        int maxPossibleKey = allPossibleKeys.max();

        p = IPrimeGenerator.findNextPrime(maxPossibleKey);
        if (p < 0){
            throw new TooBigNumberException("p number is negative.");
        }
        m = (long) hashTableSize;

        shuffleCoefficients();
    }

    @Override
    public long hash(int key) throws TooBigNumberException {
        long res = ((a * key + b) % p) % m;
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
