package com.itmo.siaod.hash_tables;

import com.itmo.siaod.exceptions.TooBigNumberException;

public interface IUniversalHashFunction {
    Long hash(Integer key) throws TooBigNumberException;
}
