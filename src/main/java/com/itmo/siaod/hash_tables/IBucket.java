package com.itmo.siaod.hash_tables;

import com.itmo.siaod.exceptions.CollisionException;

public interface IBucket {
    boolean put(Integer key, Integer val);
    Integer get(Integer key) throws CollisionException;
    int getSize();
    boolean delete(Integer key);
    void resetValues();
    boolean isSimple();
}
