package com.itmo.siaod.extendible_hash.buckets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.collections.api.list.primitive.MutableIntList;

public interface ILocalBucket {
    boolean put(int key, int value);
    boolean delete(int key);
    int get(int key);
    List<MutableIntList> getAll();
    short depth();
}
