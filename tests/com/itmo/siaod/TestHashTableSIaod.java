package com.itmo.siaod;

import com.itmo.siaod.exceptions.CollisionException;
import com.itmo.siaod.exceptions.TooBigNumberException;
import com.itmo.siaod.hash_tables.HashTableSiaod;
import com.itmo.siaod.utils.RandomSiaod;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestHashTableSIaod {

    @Test
    public void testHashTable() throws TooBigNumberException, CollisionException, InterruptedException {
            ArrayList<Integer> possibleKeys = new ArrayList<>();
            int possibleKeysSize = RandomSiaod.nextInt() % 50_000_000 + 1;
            for (int i = 0; i < possibleKeysSize; i++) {
                possibleKeys.add(RandomSiaod.nextInt() % 50_000_000);
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
