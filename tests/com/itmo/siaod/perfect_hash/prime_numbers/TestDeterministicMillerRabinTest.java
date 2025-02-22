package com.itmo.siaod.perfect_hash.prime_numbers;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeterministicMillerRabinTest {
    @Test
    public void testFastPowModulo(){
        assertEquals((long)IDeterministicMillerRabinTest.fastPowModulo(1L, 1L, 3L), 1);
        assertEquals((long)IDeterministicMillerRabinTest.fastPowModulo(1L, 0L, 3L), 1);
        assertEquals((long)IDeterministicMillerRabinTest.fastPowModulo(3L, 0L, 3L), 1);
        assertEquals((long)IDeterministicMillerRabinTest.fastPowModulo(21L, 8L, 126L), 63);
    }

    @Test
    public void testEdgeCasesFastPowModulo(){
        assertEquals((long)IDeterministicMillerRabinTest.fastPowModulo(1L, 1L, 1L), 0);
        assertEquals((long)IDeterministicMillerRabinTest.fastPowModulo(0L, 31L, 321L), 0);
        assertNull(IDeterministicMillerRabinTest.fastPowModulo(1L, 1L, 0L));
        assertNull(IDeterministicMillerRabinTest.fastPowModulo(1L, -2L, 1L));
        assertNull(IDeterministicMillerRabinTest.fastPowModulo(-1L, 3L, 1L));
        assertNull(IDeterministicMillerRabinTest.fastPowModulo(null, 3L, 1L));
        assertNull(IDeterministicMillerRabinTest.fastPowModulo(1L, null, 1L));
        assertNull(IDeterministicMillerRabinTest.fastPowModulo(1L, 3L, null));
    }

    @Test
    public void testTestBase(){
        assertTrue(IDeterministicMillerRabinTest.testBase(2, 1L, 2, 5L));
        assertTrue(IDeterministicMillerRabinTest.testBase(13, 1L, 4, 17L));
        assertFalse(IDeterministicMillerRabinTest.testBase(5, 1L, 2, 5L));
        assertFalse(IDeterministicMillerRabinTest.testBase(17, 1L, 2, 17L));
    }

    @Test
    public void testEdgeCasesTestBase(){
        assertNull(IDeterministicMillerRabinTest.testBase(null, 1L, 1, 1L));
        assertNull(IDeterministicMillerRabinTest.testBase(1, null, 1, 1L));
        assertNull(IDeterministicMillerRabinTest.testBase(1, 1L, null, 1L));
        assertNull(IDeterministicMillerRabinTest.testBase(1, 1L, 1, null));
    }
}
