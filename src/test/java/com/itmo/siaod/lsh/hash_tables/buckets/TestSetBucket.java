package com.itmo.siaod.lsh.hash_tables.buckets;

import com.itmo.siaod.extendible_hash.buckets.entries.Entry;
import com.itmo.siaod.extendible_hash.buckets.entries.IEntry;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_functions.UniversalLinearTableHashFunction;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.hash_tables.buckets.HashTableBucket;
import com.itmo.siaod.perfect_hash.hash_tables.buckets.SimpleBucket;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSetBucket {

    ArrayList<Integer> elements;
    SetBucket bucket;

    @BeforeEach
    public void setup() throws TooBigNumberException {
        this.elements = new ArrayList<>();
        int possibleKeysSize = RandomSiaod.nextInt() % 1000;
        for (int i = 0; i < possibleKeysSize; i++){
            int key = RandomSiaod.nextInt() % 1_000_000;
            while (elements.contains(key)){
                key = RandomSiaod.nextInt() % 1_000_000;
            }
            this.elements.add(key);
        }
        this.bucket = new SetBucket();
        for (Integer elem : this.elements) {
            bucket.put(elem, elem);
        }
    }

    @Test
    public void testPutAndGet() {
        this.bucket.put(20, 10);
        assertEquals(this.bucket.get(20), 10);
    }

    @Test
    public void testDelete() throws TooBigNumberException {
        this.bucket.put(20, 10);
        assertEquals(this.bucket.get(20), 10);
        this.bucket.delete(20);
        assertNull(this.bucket.get(20));
    }

    @Test
    public void testGetAll(){
        List<Integer> keys = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        //List<IEntry> entries = this.bucket.getAll();
        //for (int i = 0; i < entries.size(); i++){
        //    keys.add(entries.get(i).getKey());
        //    vals.add(entries.get(i).getValue());
        //}
        //assertEquals(keys, this.elements);
        //assertEquals(vals, this.elements);
    }
}
