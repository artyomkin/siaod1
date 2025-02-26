package com.itmo.siaod.extendible_hash.buckets.entries;

public class Entry implements IEntry {

    private Integer key;
    private Integer value;

    public Entry(){};

    public Entry(Integer key, Integer value){
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public void setKey(Integer key) {
        this.key = key;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}
