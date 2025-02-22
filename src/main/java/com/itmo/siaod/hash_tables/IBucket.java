package com.itmo.siaod.hash_tables;

import com.itmo.siaod.exceptions.CollisionException;
import com.itmo.siaod.exceptions.TooBigNumberException;

import java.util.ArrayList;

public interface IBucket {
    boolean putKey(Integer key, Integer val) throws TooBigNumberException;
    boolean put(Integer key, Integer val) throws TooBigNumberException;
    Integer get(Integer key) throws CollisionException, TooBigNumberException;
    int getSize();
    boolean delete(Integer key) throws TooBigNumberException;
    void resetValues();
    boolean isSimple();
    String toString();
}
