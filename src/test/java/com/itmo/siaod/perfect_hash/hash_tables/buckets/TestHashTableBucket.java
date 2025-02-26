package com.itmo.siaod.perfect_hash.hash_tables.buckets;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_functions.UniversalLinearHashFunction;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TestHashTableBucket {

    ArrayList<Integer> possibleKeys;
    HashTableBucket bucket;
    SimpleBucket simpleBucket;

    @BeforeEach
    public void setup() throws TooBigNumberException {
        this.possibleKeys = new ArrayList<>();
        int possibleKeysSize = RandomSiaod.nextInt() % 1000;
        for (int i = 0; i < possibleKeysSize; i++){
            int key = RandomSiaod.nextInt() % 1_000_000;
            while (possibleKeys.contains(key)){
                key = RandomSiaod.nextInt() % 1_000_000;
            }
            this.possibleKeys.add(key);
        }
        this.simpleBucket = new SimpleBucket();
        for (Integer elem : this.possibleKeys) {
            simpleBucket.putKey(elem, elem);
        }

        this.bucket = new HashTableBucket(simpleBucket);
    }

    @Test
    public void testIsSimple(){
        assertFalse(this.bucket.isSimple());
    }

    @Test
    public void testGenHashTableSize() throws TooBigNumberException {
        int hashTableSize = bucket.genHashTableSize(possibleKeys.size());
        assertTrue(hashTableSize <= 2 * Math.pow(possibleKeys.size(), 2));
    }

    @Test
    public void testDoesFunctionMakeCollisions() throws TooBigNumberException {
        UniversalLinearHashFunction function = new UniversalLinearHashFunction(possibleKeys, possibleKeys.size() - 1);
        assertTrue(bucket.doesFunctionMakeCollisions(possibleKeys, function));
    }

    @Test
    public void testChooseFunction() throws TooBigNumberException {
        IUniversalHashFunction function = bucket.chooseHashFunction(bucket.genHashTableSize(possibleKeys.size()), possibleKeys);
        assertFalse(bucket.doesFunctionMakeCollisions(possibleKeys, function));
    }

    @Test
    public void testInitHashTable() throws TooBigNumberException {
        bucket.initHashTable(possibleKeys);
        assertFalse(bucket.doesFunctionMakeCollisions(possibleKeys, bucket.getHashFunction()));
    }

    @Test
    public void testResetValues() throws TooBigNumberException {
        this.bucket.put(possibleKeys.getFirst(), 13);
        this.bucket.put(possibleKeys.getLast(), 14);
        assertNotNull(bucket.get(possibleKeys.getFirst()));
        assertNotNull(bucket.get(possibleKeys.getLast()));
        bucket.resetValues();
        assertNull(bucket.get(possibleKeys.getFirst()));
        assertNull(bucket.get(possibleKeys.getLast()));
    }

    @Test
    public void testPutAndGet() throws TooBigNumberException {
        this.bucket.put(possibleKeys.getFirst(), 10);
        assertEquals((int)this.bucket.get(possibleKeys.getFirst()), 10);
    }

    @Test
    public void testDelete() throws TooBigNumberException {
        this.bucket.put(possibleKeys.getFirst(), 10);
        assertEquals((int)this.bucket.get(possibleKeys.getFirst()), 10);
        this.bucket.delete(possibleKeys.getFirst());
        assertNull(this.bucket.get(possibleKeys.getFirst()));
    }
}
