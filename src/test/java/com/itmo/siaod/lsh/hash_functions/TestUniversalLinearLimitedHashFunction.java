package com.itmo.siaod.lsh.hash_functions;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_functions.UniversalLinearTableHashFunction;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUniversalLinearLimitedHashFunction {
    UniversalLinearHashFunction function;
    int limit;

    @BeforeEach
    public void setup() {
        this.limit = RandomSiaod.nextInt() % 10_000;
        this.function = new UniversalLinearLimitedHashFunction(this.limit);
    }

    @Test
    public void testIdempotency() {
        int key = RandomSiaod.nextInt();
        int hash = Math.toIntExact(this.function.hash(key));
        for (int i = 0; i < 10; i++){
            assertEquals((long) this.function.hash(key), hash);
        }
    }

    @Test
    public void testBoundaries() {
        for (int i = 0; i < 2 * limit; i++){
            assertTrue(this.function.hash(i) < this.limit);
        }
    }
}
