package com.itmo.siaod.extendible_hash.buckets.entries;

public interface IEntry {
    Integer getKey();
    Integer getValue();
    void setKey(Integer key);
    void setValue(Integer value);
}
