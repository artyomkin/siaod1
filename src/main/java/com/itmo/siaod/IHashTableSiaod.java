package com.itmo.siaod;

import com.itmo.siaod.exceptions.CollisionException;
import com.itmo.siaod.exceptions.TooBigNumberException;

public interface IHashTableSiaod {
    boolean put(Integer key, Integer val) throws CollisionException, TooBigNumberException;
    Integer get(Integer key) throws CollisionException, TooBigNumberException;
    boolean delete(Integer key) throws TooBigNumberException;
    String toString();
}
