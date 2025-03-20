package com.itmo.siaod.perfect_hash.hash_tables;

import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

public interface IBucket {
    boolean putKey(int key, int val) throws TooBigNumberException;
    boolean put(int key, int val) throws TooBigNumberException;
    int get(int key) throws CollisionException, TooBigNumberException;
    int getSize();
    boolean delete(int key) throws TooBigNumberException;
    void resetValues();
    boolean isSimple();
    String toString();
}
