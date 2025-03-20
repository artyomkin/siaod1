package com.itmo.siaod.extendible_hash.buckets;

import com.itmo.siaod.extendible_hash.hash_tables.IGlobalBucket;
import org.eclipse.collections.api.list.primitive.MutableIntList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GlobalBucket implements IGlobalBucket  {

    protected ILocalBucket[] localBuckets;
    protected short depth;
    protected int size;

    public GlobalBucket(){
        this.depth = 2;
        this.size = (int) Math.pow(2, depth);
        this.localBuckets = new LocalBucket[size];
    }

    protected void extend() {
        incrementDepth();
        ILocalBucket[] newLocalBuckets = new LocalBucket[size];
        System.arraycopy(this.localBuckets, 0, newLocalBuckets, 0, this.localBuckets.length);
        this.localBuckets = newLocalBuckets;
    }

    protected void incrementDepth() {
        depth++;
        this.size = (int) Math.pow(2, depth);
    }

    protected int hash(int n){
        return this.getLastBits(n, this.depth);
    }

    protected int getLastBits(int n, short bitsCnt){
        return n & ((int)Math.pow(2, bitsCnt) - 1);
    }

    protected boolean tryRedistributeBucket(int index) {
        ILocalBucket oldBucket = this.localBuckets[index];
        if (oldBucket.depth() >= this.depth) return false;

        this.localBuckets[index] = new LocalBucket((short) (oldBucket.depth()+1));
        List<MutableIntList> oldBucketEntries = oldBucket.getAll();
        for (int i = 0; i < oldBucketEntries.size(); i++) {
            this.put(oldBucketEntries.get(0).get(i), oldBucketEntries.get(1).get(i));
        }
        return true;
    }

    protected void initLocalBucket(int index) {
        this.localBuckets[index] = new LocalBucket(depth);
    }

    @Override
    public boolean put(int key, int value) {
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
    public int get(int key) {
        int index = this.hash(key);
        ILocalBucket localBucket = this.localBuckets[index];
        return localBucket != null ? localBucket.get(key) : null;
    }

    @Override
    public boolean delete(int key) {
        int index = this.hash(key);
        ILocalBucket localBucket = this.localBuckets[index];
        return localBucket != null && localBucket.delete(key);
    }

    //@Override
    //public List<MutableIntList> getEntries() {
    //    List<MutableIntList> entries = new ArrayList<>();
    //    for (ILocalBucket b : localBuckets){
    //        if (b == null) {
    //            continue;
    //        }
    //        entries.add(b.getAll());
    //    }
    //    return entries;
    //}

}
