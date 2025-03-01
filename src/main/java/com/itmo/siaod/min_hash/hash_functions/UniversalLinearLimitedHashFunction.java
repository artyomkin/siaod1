package com.itmo.siaod.min_hash.hash_functions;

public class UniversalLinearLimitedHashFunction extends UniversalLinearHashFunction{

    private Integer limit;

    public UniversalLinearLimitedHashFunction(Integer limit){
        super();
        this.limit = limit;
    }

    @Override
    public Long hash(Integer key) {
        return super.hash(key) % limit;
    }

}
