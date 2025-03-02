package com.itmo.siaod.extendible_hash;

import com.itmo.siaod.extendible_hash.buckets.ILocalBucket;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;

import java.util.List;

public interface IHashTableSiaod {
    boolean put(Integer key, Integer value);
    Integer get(Integer key);
    boolean delete(Integer key);
    List<List<IEntry>> getAllEntries();
}
