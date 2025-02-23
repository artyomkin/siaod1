package com.itmo.siaod.extendible_hash.buckets;

import com.itmo.siaod.extendible_hash.buckets.entries.Entry;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.extendible_hash.hash_tables.IGlobalBucket;

import java.util.ArrayList;

public class GlobalBucket implements IGlobalBucket  {

    private ILocalBucket[] localBuckets;
    private Short depth;
    private Integer size;

    public GlobalBucket(){
        this.depth = 2;
        this.size = (int) Math.pow(2, depth);
        this.localBuckets = new LocalBucket[size];
    }

    private void extend() {
        incrementDepth();
        ILocalBucket[] newLocalBuckets = new LocalBucket[size];
        for (int i = 0; i < this.localBuckets.length; i++) {
            newLocalBuckets[i] = this.localBuckets[i];
        }
    }

    private void incrementDepth() {
        depth++;
        this.size = (int) Math.pow(2, depth);
    }

    private Integer hash(Integer n){
        return this.getLastBits(n, this.depth);
    }

    private Integer getLastBits(Integer n, Short bitsCnt){
        return n & ((int)Math.pow(2, bitsCnt) - 1);
    }

    private boolean tryRedistributeBucket(Integer index) {
        ILocalBucket oldBucket = this.localBuckets[index];
        if (oldBucket.depth() >= this.depth) return false;

        this.localBuckets[index] = new LocalBucket((short) (oldBucket.depth()+1));
        for (IEntry entry : oldBucket.getAll()) {
            this.put(entry.getKey(), entry.getValue());
        }
        return true;
    }

    private void initLocalBucket(Integer index) {
        this.localBuckets[index] = new LocalBucket(depth);
    }

    @Override
    public boolean put(Integer key, Integer value) {
        if (key == null) return false;
        int index = this.hash(key);
        if (this.localBuckets[index] == null) this.initLocalBucket(index);

        if (!this.localBuckets[index].put(key, value)){
            if (!this.tryRedistributeBucket(index)){
                this.extend();
            }
            return this.put(key, value);
        }
        return true;
    }

    @Override
    public Integer get(Integer key) {
        int index = this.hash(key);
        ILocalBucket localBucket = this.localBuckets[index];
        return localBucket != null ? localBucket.get(key) : null;
    }

    @Override
    public boolean delete(Integer key) {
        int index = this.hash(key);
        ILocalBucket localBucket = this.localBuckets[index];
        return localBucket != null && localBucket.delete(key);
    }
}
