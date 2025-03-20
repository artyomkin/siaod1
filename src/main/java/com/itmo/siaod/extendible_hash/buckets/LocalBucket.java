package com.itmo.siaod.extendible_hash.buckets;

import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.ArrayList;
import java.util.List;

public class LocalBucket implements ILocalBucket {

    public static final int CAPACITY = 10;
    private MutableIntList keys;
    private MutableIntList values;
    private short depth;

    public LocalBucket(short depth){
        this.keys = IntLists.mutable.empty();
        this.values = IntLists.mutable.empty();
        this.depth = depth;
    }

    @Override
    public boolean put(int key, int value) {
        if (this.keys.size() >= CAPACITY) {
            return false;
        }
        this.keys.add(key);
        this.values.add(value);
        return true;
    }

    @Override
    public boolean delete(int key) {
        for (int i = 0; i < this.keys.size(); i++){
            if (this.keys.get(i) == key){
                this.keys.removeAtIndex(i);
                this.values.removeAtIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int get(int key) {
        for (int i = 0; i < this.keys.size(); i++) {
            if (keys.get(i) == key){
                return this.values.get(i);
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public short depth() {
        return this.depth;
    }

    @Override
    public List<MutableIntList> getAll() {
        return List.of(this.keys, this.values);
    }
}
