package com.itmo.siaod.perfect_hash.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRandomSiaod {

    @Test
    public void testNextInt(){
        for (int i = 0; i < 100; i++){
            assertTrue(RandomSiaod.nextInt() > 0);
        }
    }
}
