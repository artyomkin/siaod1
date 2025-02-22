package com.itmo.siaod;

import com.itmo.siaod.exceptions.CollisionException;
import com.itmo.siaod.exceptions.TooBigNumberException;
import com.itmo.siaod.hash_tables.HashTableSiaod;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TestHashTableSIaod {

    @Test
    public void testHashTable() throws TooBigNumberException, CollisionException, InterruptedException {
            Random rnd = new Random();
            ArrayList<Integer> possibleKeys = new ArrayList<>();
            int possibleKeysSize = (rnd.nextInt() & Integer.MAX_VALUE) % 10_000_000 + 1;
            for (int i = 0; i < possibleKeysSize; i++) {
                possibleKeys.add((rnd.nextInt() & Integer.MAX_VALUE) % 10_000_000);
            }
            List<Integer> uniqPossibleKeys = possibleKeys.stream().distinct().toList();
            HashTableSiaod hashTableSiaod = new HashTableSiaod(uniqPossibleKeys);

            for (int i = 0; i < 100; i++) {
                int rndIndex = (rnd.nextInt() & Integer.MAX_VALUE) % uniqPossibleKeys.size();
                int key = uniqPossibleKeys.get(rndIndex);
                int val = (rnd.nextInt() & Integer.MAX_VALUE) % 1000 + 1000;

                assertTrue(hashTableSiaod.put(key, val));
                assertEquals((int) hashTableSiaod.get(key), val);
                assertTrue(hashTableSiaod.delete(key));
            }
    }
}
