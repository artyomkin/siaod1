package com.itmo.siaod.perfect_hash;

import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

public interface IHashTableSiaod {
    boolean put(int key, int val) throws CollisionException, TooBigNumberException;
    int get(int key) throws CollisionException, TooBigNumberException;
    boolean delete(int key) throws TooBigNumberException;
    String toString();
}
