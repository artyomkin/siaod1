package com.itmo.siaod.extendible_hash.buckets;

import com.itmo.siaod.extendible_hash.buckets.entries.Entry;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;

import java.util.ArrayList;

public class LocalBucket implements ILocalBucket {

    public static final Integer CAPACITY = 10;
    private ArrayList<IEntry> entries;
    private Short depth;

    public LocalBucket(Short depth){
        this.entries = new ArrayList<>(CAPACITY);
        this.depth = depth;
    }

    @Override
    public boolean put(Integer key, Integer value) {
        if (this.entries.size() >= CAPACITY) {
            return false;
        }
        this.entries.add(new Entry(key, value));
        return true;
    }

    @Override
    public boolean delete(Integer key) {
        for (int i = 0; i < this.entries.size(); i++){
            if (this.entries.get(i).getKey().equals(key)){
                this.entries.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(Integer key) {
        for (IEntry entry : this.entries){
            if (entry.getKey().equals(key)){
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Short depth() {
        return this.depth;
    }

    @Override
    public ArrayList<IEntry> getAll() {
        return this.entries;
    }
}
