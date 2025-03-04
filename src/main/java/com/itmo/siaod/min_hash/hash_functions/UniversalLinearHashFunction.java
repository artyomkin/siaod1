package com.itmo.siaod.min_hash.hash_functions;

import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.prime_numbers.IPrimeGenerator;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;

public class UniversalLinearHashFunction implements IUniversalHashFunction {
    private Long a;
    private Long b;
    private Long p;

    public UniversalLinearHashFunction() {
        p = IPrimeGenerator.findNextPrime((long) (RandomSiaod.nextInt() % 1_000 + 1_000_000));
        b = RandomSiaod.nextInt() % p;
        a = RandomSiaod.nextInt() % p;
        if (a == 0){
            a = 1L;
        }
    }

    @Override
    public Long hash(Integer key) {
        return (a * key + b) % p;
    }

}
