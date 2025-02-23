package com.itmo.siaod.extendible_hash.hash_tables;

public interface IGlobalBucket {
    boolean put(Integer key, Integer value);
    Integer get(Integer key);
    boolean delete(Integer key);
}
