package com.itmo.siaod.min_hash.hash_tables;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

public interface IHashTableSiaod {
    boolean put(Integer key, Integer value) throws TooBigNumberException;
    boolean delete(Integer key) throws TooBigNumberException;
    Integer get(Integer key) throws TooBigNumberException;
}
