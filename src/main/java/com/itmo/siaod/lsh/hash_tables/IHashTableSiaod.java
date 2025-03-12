package com.itmo.siaod.lsh.hash_tables;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.List;

public interface IHashTableSiaod {
    boolean put(Integer key, Integer value) throws TooBigNumberException;
    boolean delete(Integer key) throws TooBigNumberException;
    Integer get(Integer key) throws TooBigNumberException;
    List<List<IEntry>> getAllEntries();
}
