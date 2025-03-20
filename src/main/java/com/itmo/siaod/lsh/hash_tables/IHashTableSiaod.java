package com.itmo.siaod.lsh.hash_tables;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.List;

public interface IHashTableSiaod {
    boolean put(int key, int value) throws TooBigNumberException;
    boolean delete(int key) throws TooBigNumberException;
    int get(int key) throws TooBigNumberException;
    //List<List<IEntry>> getAllEntries();
}
