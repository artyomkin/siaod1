package com.itmo.siaod.perfect_hash.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestRandomSiaod {

    @Test
    public void testNextInt(){
        for (int i = 0; i < 100; i++){
            assertTrue(RandomSiaod.nextInt() > 0);
        }
    }
}
