package com.itmo.siaod.perfect_hash.hash_tables;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

public interface IUniversalHashFunction {
    long hash(int key) throws TooBigNumberException;
}
