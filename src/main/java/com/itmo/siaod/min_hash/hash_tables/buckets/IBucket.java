package com.itmo.siaod.min_hash.hash_tables.buckets;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;

import java.util.List;

public interface IBucket {
    boolean put(Integer key, Integer val);
    boolean delete(Integer key);
    Integer get(Integer key);
    List<IEntry> getAll();
}
