package com.itmo.siaod.extendible_hash.hash_tables;

import com.itmo.siaod.extendible_hash.buckets.ILocalBucket;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;

import java.util.List;

public interface IGlobalBucket {
    boolean put(Integer key, Integer value);
    Integer get(Integer key);
    boolean delete(Integer key);
    List<List<IEntry>> getEntries();
}
