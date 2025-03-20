package com.itmo.siaod.lsh.hash_functions;

import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.prime_numbers.IPrimeGenerator;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;

public class UniversalLinearHashFunction implements IUniversalHashFunction {
    private long a;
    private long b;
    private long p;

    public UniversalLinearHashFunction() {
        p = IPrimeGenerator.findNextPrime(RandomSiaod.nextInt() % 1_000 + 1_000_000);
        b = RandomSiaod.nextInt() % p;
        a = RandomSiaod.nextInt() % p;
        if (a == 0){
            a = 1L;
        }
    }

    @Override
    public long hash(int key) {
        return (a * key + b) % p;
    }

}
