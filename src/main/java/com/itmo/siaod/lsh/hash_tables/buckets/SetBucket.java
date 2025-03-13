package com.itmo.siaod.lsh.hash_tables.buckets;

import com.itmo.siaod.extendible_hash.buckets.entries.Entry;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;

import java.util.ArrayList;
import java.util.List;

public class SetBucket implements IBucket {

    private List<IEntry> entries;

    public SetBucket() {
        this.entries = new ArrayList<>();
    }

    @Override
    public boolean put(Integer key, Integer val) {
        //boolean entriesContainKeyVal = this.entries.stream()
        //        .anyMatch(entry -> entry.getKey().equals(key) && entry.getValue().equals(val));
        //if (entriesContainKeyVal) return false;

        this.entries.add(new Entry(key, val));
        return true;
    }

    @Override
    public boolean delete(Integer key) {
        for (int i = 0; i < entries.size(); i++){
            if (this.entries.get(i).getKey().equals(key)){
                this.entries.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer get(Integer key) {
        for (int i = 0; i < entries.size(); i++){
            if (this.entries.get(i).getKey().equals(key)){
                return this.entries.get(i).getValue();
            }
        }
        return null;
    }

    @Override
    public List<IEntry> getAll() {
        return this.entries;
    }
}
