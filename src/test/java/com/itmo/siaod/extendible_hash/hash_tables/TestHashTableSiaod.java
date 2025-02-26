package com.itmo.siaod.extendible_hash.hash_tables;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestHashTableSiaod {

    private HashTableSiaod hashTableSiaod;

    @BeforeEach
    public void setup(){
        this.hashTableSiaod = new HashTableSiaod();
    }

    @Test
    public void testPut(){
        this.hashTableSiaod.put(1, 13);
        assertEquals(this.hashTableSiaod.get(1), 13);
    }

    @Test
    public void testGet(){
        this.hashTableSiaod.put(1, 13);
        assertEquals(this.hashTableSiaod.get(1), 13);
    }

    @Test
    public void testDelete(){
        this.hashTableSiaod.put(1, 13);
        assertEquals(this.hashTableSiaod.get(1), 13);
        this.hashTableSiaod.delete(1);
        assertNull(this.hashTableSiaod.get(1));
    }

}
