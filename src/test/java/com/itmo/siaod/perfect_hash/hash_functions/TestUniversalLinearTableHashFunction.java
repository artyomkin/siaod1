package com.itmo.siaod.perfect_hash.hash_functions;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TestUniversalLinearTableHashFunction {
    UniversalLinearTableHashFunction function;
    MutableIntList possibleKeys;
    int hashTableSize;

    @BeforeEach
    public void setup() throws TooBigNumberException {
        this.possibleKeys = IntLists.mutable.empty();
        int possibleKeysSize = RandomSiaod.nextInt() % 1000;
        for (int i = 0; i < possibleKeysSize; i++){
            this.possibleKeys.add(RandomSiaod.nextInt() % 1_000_000);
        }
        this.hashTableSize = possibleKeysSize * 2;
        this.function = new UniversalLinearTableHashFunction(possibleKeys, this.hashTableSize);
    }

    @Test
    public void testIdempotency() throws TooBigNumberException {
        int key = this.possibleKeys.get(0);
        int hash = Math.toIntExact(this.function.hash(key));
        for (int i = 0; i < 10; i++){
            assertEquals((long) this.function.hash(key), hash);
        }
    }

    //@Test
    //public void testBoundaries() throws TooBigNumberException {
    //    for (Integer key : possibleKeys){
    //        assertTrue(this.function.hash(key) < this.hashTableSize);
    //    }
    //}

    //@Test
    //public void testShuffle() throws TooBigNumberException {
    //    int MAX_COLLISIONS_CNT = 2;
    //    int collisionsCnt = 0;
    //    int attempts = 5;
    //    int key = 10;
    //    for (int i = 0; i < attempts; i++){
    //        long oldval = function.hash(key);
    //        function.shuffleCoefficients();
    //        long newVal = function.hash(key);
    //        if (oldval == newVal){
    //            collisionsCnt++;
    //        }
    //    }
    //    assertTrue(collisionsCnt <= MAX_COLLISIONS_CNT);
    //}
}
