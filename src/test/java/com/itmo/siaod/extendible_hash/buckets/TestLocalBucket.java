package com.itmo.siaod.extendible_hash.buckets;

import com.itmo.siaod.extendible_hash.buckets.entries.Entry;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestLocalBucket {

    private LocalBucket localBucket;

    @BeforeEach
    public void setup(){
        this.localBucket = new LocalBucket((short) 3);
    }

    @Test
    public void testPut(){
        this.localBucket.put(32, 13);
        assertEquals(this.localBucket.get(32), 13);
    }

    @Test
    public void testDelete() {
        this.localBucket.put(32, 13);
        assertEquals(this.localBucket.get(32), 13);
        this.localBucket.delete(32);
        assertNull(this.localBucket.get(32));
    }

    @Test
    public void testGet() {
        this.localBucket.put(32, 13);
        assertEquals(this.localBucket.get(32), 13);
    }

    @Test
    public void testDepth() {
        assertEquals((int) this.localBucket.depth(), 3);
    }

    @Test
    public void testGetAll() {
        this.localBucket.put(32, 13);
        this.localBucket.put(33, 131);
        ArrayList<IEntry> entries = this.localBucket.getAll();
        assertEquals(entries.get(0).getKey(), 32);
        assertEquals(entries.get(1).getKey(), 33);
        assertEquals(entries.get(0).getValue(), 13);
        assertEquals(entries.get(1).getValue(), 131);
    }

}

