package com.itmo.siaod.extendible_hash.buckets;

import java.util.ArrayList;
import java.util.List;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;

public interface ILocalBucket {
    boolean put(Integer key, Integer value);
    boolean delete(Integer key);
    Integer get(Integer key);
    List<IEntry> getAll();
    Short depth();
}
