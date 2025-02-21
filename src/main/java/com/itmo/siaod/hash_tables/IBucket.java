package com.itmo.siaod.hash_tables;

import com.itmo.siaod.exceptions.TooBigNumberException;

public interface IBucket {
    void putKey(Integer key);
    void initHashTable() throws TooBigNumberException;
}
