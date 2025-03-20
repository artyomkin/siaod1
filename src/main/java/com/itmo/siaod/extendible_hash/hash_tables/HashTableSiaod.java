package com.itmo.siaod.extendible_hash.hash_tables;

import com.itmo.siaod.extendible_hash.IHashTableSiaod;
import com.itmo.siaod.extendible_hash.buckets.GlobalBucket;
import com.itmo.siaod.extendible_hash.buckets.ILocalBucket;

import java.util.List;

public class HashTableSiaod implements IHashTableSiaod {

    private IGlobalBucket globalBucket;

    public HashTableSiaod(){
        this.globalBucket = new GlobalBucket();
    }

    @Override
    public boolean put(int key, int value) {
        return this.globalBucket.put(key, value);
    }

    @Override
    public int get(int key) {
        return this.globalBucket.get(key);
    }

    @Override
    public boolean delete(int key) {
        return this.globalBucket.delete(key);
    }

    //@Override
    //public List<List<IEntry>> getAllEntries() {
    //    return this.globalBucket.getEntries();
    //}
}
