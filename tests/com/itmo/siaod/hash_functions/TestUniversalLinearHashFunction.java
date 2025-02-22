package com.itmo.siaod.hash_functions;

import com.itmo.siaod.exceptions.TooBigNumberException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUniversalLinearHashFunction {
    UniversalLinearHashFunction function;
    Random rnd = new Random();
    List<Integer> possibleKeys;
    int hashTableSize;

    @Before
    public void setup() throws TooBigNumberException {
        this.possibleKeys = new ArrayList<>();
        int possibleKeysSize = (rnd.nextInt() & Integer.MAX_VALUE) % 1000;
        for (int i = 0; i < possibleKeysSize; i++){
            this.possibleKeys.add((rnd.nextInt() & Integer.MAX_VALUE) % 1_000_000);
        }
        this.hashTableSize = possibleKeysSize * 2;
        this.function = new UniversalLinearHashFunction(possibleKeys, this.hashTableSize);
    }

    @Test
    public void testIdempotency() throws TooBigNumberException {
        int key = this.possibleKeys.get(0);
        int hash = Math.toIntExact(this.function.hash(key));
        for (int i = 0; i < 10; i++){
            assertEquals((long) this.function.hash(key), hash);
        }
    }

    @Test
    public void testBoundaries() throws TooBigNumberException {
        for (Integer key : possibleKeys){
            assertTrue(this.function.hash(key) < this.hashTableSize);
        }
    }
}
