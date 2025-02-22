package com.itmo.siaod.perfect_hash.hash_tables;

import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestHashTableSIaod {

    private HashTableSiaod hashTableSiaod;
    private List<Integer> possibleKeys;

    @Before
    public void setup() throws TooBigNumberException {
        this.possibleKeys = new ArrayList<>();
        int possibleKeysSize = RandomSiaod.nextInt() % 50_000 + 1;
        for (int i = 0; i < possibleKeysSize; i++) {
            possibleKeys.add(RandomSiaod.nextInt() % 50_000);
        }
        List<Integer> uniqPossibleKeys = possibleKeys.stream().distinct().toList();
        this.hashTableSiaod = new HashTableSiaod(uniqPossibleKeys);
    }

    @Test
    public void testGetAndPut() throws TooBigNumberException, CollisionException {
        assertNull(this.hashTableSiaod.get(this.possibleKeys.getFirst()));
        this.hashTableSiaod.put(this.possibleKeys.getFirst(), 10);
        assertEquals((int) this.hashTableSiaod.get(this.possibleKeys.getFirst()), 10);
    }

    @Test
    public void testDelete() throws TooBigNumberException, CollisionException {
        this.hashTableSiaod.put(this.possibleKeys.getFirst(), 10);
        assertNotNull(this.hashTableSiaod.get(this.possibleKeys.getFirst()));
        this.hashTableSiaod.delete(this.possibleKeys.getFirst());
        assertNull(this.hashTableSiaod.get(this.possibleKeys.getFirst()));
    }

    @Test
    public void testHashTable() throws TooBigNumberException, CollisionException, InterruptedException {
        ArrayList<Integer> possibleKeys = new ArrayList<>();
        int possibleKeysSize = RandomSiaod.nextInt() % 10_000_000 + 1;
        for (int i = 0; i < possibleKeysSize; i++) {
            possibleKeys.add(RandomSiaod.nextInt() % 10_000_000);
        }
        List<Integer> uniqPossibleKeys = possibleKeys.stream().distinct().toList();
        HashTableSiaod hashTableSiaod = new HashTableSiaod(uniqPossibleKeys);

        for (int i = 0; i < 100; i++) {
            int rndIndex = RandomSiaod.nextInt() % uniqPossibleKeys.size();
            int key = uniqPossibleKeys.get(rndIndex);
            int val = RandomSiaod.nextInt() % 1000 + 1000;

            assertTrue(hashTableSiaod.put(key, val));
            assertEquals((int) hashTableSiaod.get(key), val);
            assertTrue(hashTableSiaod.delete(key));
        }
    }
}
