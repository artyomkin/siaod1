package com.itmo.siaod.min_hash.hash_tables;

import com.itmo.siaod.min_hash.hash_functions.UniversalLinearLimitedHashFunction;
import com.itmo.siaod.min_hash.hash_tables.buckets.Bucket;
import com.itmo.siaod.min_hash.hash_tables.buckets.IBucket;
import com.itmo.siaod.min_hash.hash_functions.UniversalLinearHashFunction;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;

import java.util.ArrayList;
import java.util.List;

public class HashTableSiaod implements IHashTableSiaod {

    private final List<IBucket> buckets;
    private final IUniversalHashFunction hashFunction;

    public HashTableSiaod(Integer bucketsNumber) {
        this.hashFunction = new UniversalLinearLimitedHashFunction(bucketsNumber);
        this.buckets = new ArrayList<>(bucketsNumber);
        for (int i = 0; i < bucketsNumber; i++){
            this.buckets.add(null);
        }
    }

    @Override
    public boolean put(Integer key, Integer value) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        ensureBucketExists(index);
        return this.buckets.get(index).put(key, value);
    }

    @Override
    public boolean delete(Integer key) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        ensureBucketExists(index);
        return this.buckets.get(index).delete(key);
    }

    @Override
    public Integer get(Integer key) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        ensureBucketExists(index);
        return this.buckets.get(index).get(key);
    }

    private void ensureBucketExists(Integer index){
        if (this.buckets.get(index) == null){
            this.buckets.set(index, new Bucket());
        }
    }
}
