package com.itmo.siaod.extendible_hash;

public interface IHashTableSiaod {
    boolean put(Integer key, Integer value);
    Integer get(Integer key);
    boolean delete(Integer key);
}
