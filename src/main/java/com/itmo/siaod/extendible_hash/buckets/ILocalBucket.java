package com.itmo.siaod.extendible_hash.buckets;

import java.util.ArrayList;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;

public interface ILocalBucket {
    boolean put(Integer key, Integer value);
    boolean delete(Integer key);
    Integer get(Integer key);
    ArrayList<IEntry> getAll();
    Short depth();
}
