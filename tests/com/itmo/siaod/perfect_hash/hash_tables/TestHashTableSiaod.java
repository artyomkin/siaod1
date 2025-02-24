package com.itmo.siaod.perfect_hash.hash_tables;

import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.buckets.HashTableBucket;
import com.itmo.siaod.perfect_hash.hash_tables.buckets.SimpleBucket;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestHashTableSiaod {

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
    public void testGet() throws TooBigNumberException, CollisionException {
        assertNull(this.hashTableSiaod.get(this.possibleKeys.getFirst()));
        this.hashTableSiaod.put(this.possibleKeys.getFirst(), 10);
        assertEquals((int) this.hashTableSiaod.get(this.possibleKeys.getFirst()), 10);
    }

    @Test
    public void testPut() throws TooBigNumberException, CollisionException {
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
        int possibleKeysSize = RandomSiaod.nextInt() % 100_000 + 1;
        for (int i = 0; i < possibleKeysSize; i++) {
            possibleKeys.add(RandomSiaod.nextInt() % 100_000);
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

    @Test
    public void testToString() throws TooBigNumberException {
        HashTableSiaod table = new HashTableSiaod(List.of(1,2,3,4));
        table.put(1,2);
        assertTrue(table.toString().contains("simple") || table.toString().contains("null"));
    }

    @Test
    public void testResetBuckets() {
        this.hashTableSiaod.resetBuckets();
        for (int i = 0; i < this.hashTableSiaod.buckets.size(); i++){
            if (this.hashTableSiaod.buckets.get(i) == null) continue;
            if(this.hashTableSiaod.buckets.get(i).isSimple()){
                assertTrue(this.hashTableSiaod.buckets.get(i).getSize() == 0);
            } else {
                HashTableBucket table = (HashTableBucket) this.hashTableSiaod.buckets.get(i);
                for (Integer elem : table.getHashTable()){
                    assertNull(elem);
                }
            }
        }
    }

    @Test
    public void testSimpleToHashTableBucket() throws TooBigNumberException {
        SimpleBucket sb = new SimpleBucket();
        HashTableBucket hb = this.hashTableSiaod.simpleToHashTableBucket(sb);
        assertNotNull(hb);
    }

    @Test
    public void testInitializeSecondLayer() throws TooBigNumberException {
        this.hashTableSiaod.initializeSecondLayer();
        boolean hasHashTableBuckets = false;
        for (IBucket bucket : this.hashTableSiaod.buckets){
            if (!bucket.isSimple()) {
                hasHashTableBuckets = true;
                break;
            }
        }
        assertTrue(hasHashTableBuckets);
    }
}
