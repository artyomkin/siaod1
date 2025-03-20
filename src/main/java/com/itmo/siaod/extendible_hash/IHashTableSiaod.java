package com.itmo.siaod.extendible_hash;

import com.itmo.siaod.extendible_hash.buckets.ILocalBucket;

import java.util.List;

public interface IHashTableSiaod {
    boolean put(int key, int value);
    int get(int key);
    boolean delete(int key);
    //List<List<IEntry>> getAllEntries();
}
