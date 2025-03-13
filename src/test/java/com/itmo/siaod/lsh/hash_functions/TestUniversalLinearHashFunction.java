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

public class TestUniversalLinearHashFunction {
    UniversalLinearHashFunction function;

    @BeforeEach
    public void setup() throws TooBigNumberException {
        this.function = new UniversalLinearHashFunction();
    }

    @Test
    public void testIdempotency() throws TooBigNumberException {
        int key = RandomSiaod.nextInt();
        int hash = Math.toIntExact(this.function.hash(key));
        for (int i = 0; i < 10; i++){
            assertEquals((long) this.function.hash(key), hash);
        }
    }
}
