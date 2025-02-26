package com.itmo.siaod.extendible_hash.buckets;

import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGlobalBucket {

    public GlobalBucket globalBucket;

    @BeforeEach
    public void setup(){
        this.globalBucket = new GlobalBucket();
    }

    @Test
    public void testExtend() {
        assertEquals((int)this.globalBucket.size, 4);
        for (int i = 0; i < 41; i++){
            this.globalBucket.put(i, i+1);
        }
        assertEquals((int)this.globalBucket.size, 8);
    }

    @Test
    public void testIncrementDepth() {
        assertEquals((int)this.globalBucket.depth, 2);
        for (int i = 0; i < 41; i++){
            this.globalBucket.put(i, i+1);
        }
        assertEquals((int)this.globalBucket.depth, 3);
    }

    @Test
    public void testHash(){
        assertEquals(this.globalBucket.hash(7), 3);
    }

    @Test
    public void testGetLastBits(){
        assertEquals(this.globalBucket.getLastBits(7, (short) 2), 3);
    }

    @Test
    public void testInitLocalBucket() {
        assertNull(this.globalBucket.localBuckets[0]);
        this.globalBucket.initLocalBucket(0);
        assertInstanceOf(LocalBucket.class, this.globalBucket.localBuckets[0]);
    }

    @Test
    public void testPut() {
        this.globalBucket.put(100, 200);
        assertEquals(this.globalBucket.get(100), 200);
    }

    @Test
    public void testGet() {
        this.globalBucket.put(100, 200);
        assertEquals(this.globalBucket.get(100), 200);
    }

    @Test
    public void testDelete() {
        this.globalBucket.put(100, 200);
        assertEquals(this.globalBucket.get(100), 200);
        this.globalBucket.delete(100);
        assertNull(this.globalBucket.get(100));
    }
}
