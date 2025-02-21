package com.itmo.siaod;

import com.itmo.siaod.exceptions.CollisionException;

public interface IHashTableSiaod {
    boolean put(Integer key, Integer val);
    Integer get(Integer key) throws CollisionException;
    boolean delete(Integer key);
}
