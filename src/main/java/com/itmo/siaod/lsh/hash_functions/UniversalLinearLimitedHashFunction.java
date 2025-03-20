package com.itmo.siaod.lsh.hash_functions;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

public class UniversalLinearLimitedHashFunction extends UniversalLinearHashFunction{

    private int limit;

    public UniversalLinearLimitedHashFunction(int limit){
        super();
        this.limit = limit;
    }

    @Override
    public long hash(int key) {
        return super.hash(key) % limit;
    }

}
