package com.itmo.siaod.lsh.hash_tables.buckets;

import org.eclipse.collections.api.list.primitive.MutableIntList;

import java.util.List;

public interface IBucket {
    boolean put(int key, int val);
    boolean delete(int key);
    int get(int key);
    //List<MutableIntList> getAll();
}
