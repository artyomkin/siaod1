package com.itmo.siaod.extendible_hash.hash_tables;

import com.itmo.siaod.extendible_hash.buckets.ILocalBucket;

import java.util.List;

public interface IGlobalBucket {
    boolean put(int key, int value);
    int get(int key);
    boolean delete(int key);
    //List<List<IEntry>> getEntries();
}
