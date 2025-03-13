package com.itmo.siaod.lsh.hash_tables;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestHashTableSiaod {

    HashTableSiaod ht;

    @BeforeEach
    public void setup(){
        this.ht = new HashTableSiaod(100_000);
    }

    @Test
    public void testPutAndGet() throws TooBigNumberException {
        this.ht.put(10, 15);
        assertEquals(this.ht.get(10), 15);
    }

    @Test
    public void testDelete() throws TooBigNumberException {
        this.ht.put(10, 20);
        assertEquals(this.ht.get(10), 20);
        this.ht.delete(10);
        assertNull(this.ht.get(10));
    }

    @Test
    public void testGetAllEntries() {
        assertEquals(this.ht.getAllEntries().getClass(), ArrayList.class);
    }

}
