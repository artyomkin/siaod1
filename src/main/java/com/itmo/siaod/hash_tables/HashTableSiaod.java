package com.itmo.siaod.hash_tables;

import com.itmo.siaod.IHashTableSiaod;
import com.itmo.siaod.exceptions.CollisionException;
import com.itmo.siaod.exceptions.TooBigNumberException;
import com.itmo.siaod.hash_functions.UniversalLinearHashFunction;
import com.itmo.siaod.hash_tables.buckets.HashTableBucket;
import com.itmo.siaod.hash_tables.buckets.SimpleBucket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashTableSiaod implements IHashTableSiaod {

    private IUniversalHashFunction hashFunction;
    private Random rnd;
    private Integer hashTableSize;
    private ArrayList<IBucket> buckets;

    public HashTableSiaod(List<Integer> allPossibleKeys) throws TooBigNumberException {
        int allPossibleKeysCount = allPossibleKeys.size();

        this.rnd = new Random();
        this.hashFunction = new UniversalLinearHashFunction(allPossibleKeys, this.hashTableSize);
        this.hashTableSize = (12 + (rnd.nextInt() & Integer.MAX_VALUE) % 9) / 10 * allPossibleKeysCount;

        initializeFirstLayer(allPossibleKeys);
        initializeSecondLayer();
        resetBuckets();
    }

    protected void initializeFirstLayer(List<Integer> allPossibleKeys){
        this.buckets = new ArrayList<>(this.hashTableSize);
        for (Integer key : allPossibleKeys) {
            addKey(key);
        }
    }

    protected void addKey(Integer key) {
        if (key == null) return;

        Integer index = this.hashFunction.hash(key);
        if (this.buckets.get(index) == null) {
            IBucket simpleBucket = new SimpleBucket(key);
            this.buckets.set(index, simpleBucket);
        } else {
            this.buckets.get(index).put(key, key);
        }
    }

    protected void initializeSecondLayer() throws TooBigNumberException {
        for (int i = 0; i < this.buckets.size(); i++){
            if (this.buckets.get(i).isSimple() && this.buckets.get(i).getSize() > 1) {
                this.buckets.set(i, simpleToHashTableBucket((ISimpleBucket) this.buckets.get(i)));
            }
        }
    }

    protected IBucket simpleToHashTableBucket(ISimpleBucket simpleBucket) throws TooBigNumberException {
        return new HashTableBucket(simpleBucket);
    }

    protected void resetBuckets(){
        for (IBucket bucket : buckets) {
            bucket.resetValues();
        }
    }

    @Override
    public boolean put(Integer key, Integer val) {
        return this.buckets.get(key).put(key, val);
    }

    @Override
    public Integer get(Integer key) throws CollisionException {
        return this.buckets.get(key).get(key);
    }

    @Override
    public boolean delete(Integer key) {
        return this.buckets.get(key).delete(key);
    }
}
