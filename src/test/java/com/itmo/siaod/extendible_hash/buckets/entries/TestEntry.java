package com.itmo.siaod.extendible_hash.buckets.entries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEntry {

    private Entry entry;

    @BeforeEach
    public void setup(){
        this.entry = new Entry(31, 10);
    }

    @Test
    public void testGetKey(){
        assertEquals(this.entry.getKey(), 31);
    }
    @Test
    public void testGetValue(){
        assertEquals(this.entry.getValue(), 10);
    }
    @Test
    public void testSetKey(){
        this.entry.setKey(100);
        assertEquals(this.entry.getKey(), 100);
    }
    @Test
    public void testSetValue(){
        this.entry.setValue(321);
        assertEquals(this.entry.getValue(), 321);
    }

}
