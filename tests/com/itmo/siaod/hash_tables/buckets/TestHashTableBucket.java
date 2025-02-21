package com.itmo.siaod.hash_tables.buckets;

import com.itmo.siaod.exceptions.TooBigNumberException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

public class TestHashTableBucket {

    ArrayList<Integer> possibleKeys;
    Random rnd = new Random();
    HashTableBucket bucket;

    @Before
    public void setup() throws TooBigNumberException {
        this.possibleKeys = new ArrayList<>();
        int possibleKeysSize = (rnd.nextInt() & Integer.MAX_VALUE) % 1000;
        for (int i = 0; i < possibleKeysSize; i++){
            this.possibleKeys.add((rnd.nextInt() & Integer.MAX_VALUE) % 1_000_000);
        }
        SimpleBucket simpleBucket = new SimpleBucket();
        for (Integer elem : this.possibleKeys) {
            simpleBucket.put(elem, elem);
        }

        this.bucket = new HashTableBucket(simpleBucket);
    }

    @Test
    public void testIsSimple(){
        assertFalse(this.bucket.isSimple());
    }

}
