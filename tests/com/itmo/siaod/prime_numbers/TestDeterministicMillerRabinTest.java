package com.itmo.siaod.prime_numbers;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestDeterministicMillerRabinTest {
    DeterministicMillerRabinTest millerRabinTest;

    @Before
    public void setup() {
        this.millerRabinTest = new DeterministicMillerRabinTest();
    }

    @Test
    public void testFastPowModulo(){
        assertEquals((long)millerRabinTest.fastPowModulo(1L, 1L, 3L), 1);
        assertEquals((long)millerRabinTest.fastPowModulo(1L, 0L, 3L), 1);
        assertEquals((long)millerRabinTest.fastPowModulo(3L, 0L, 3L), 1);
        assertEquals((long)millerRabinTest.fastPowModulo(21L, 8L, 126L), 63);
    }

    @Test
    public void testEdgeCasesFastPowModulo(){
        assertEquals((long)millerRabinTest.fastPowModulo(1L, 1L, 1L), 0);
        assertEquals((long)millerRabinTest.fastPowModulo(0L, 31L, 321L), 0);
        assertNull(millerRabinTest.fastPowModulo(1L, 1L, 0L));
        assertNull(millerRabinTest.fastPowModulo(1L, -2L, 1L));
        assertNull(millerRabinTest.fastPowModulo(-1L, 3L, 1L));
        assertNull(millerRabinTest.fastPowModulo(null, 3L, 1L));
        assertNull(millerRabinTest.fastPowModulo(1L, null, 1L));
        assertNull(millerRabinTest.fastPowModulo(1L, 3L, null));
    }

    @Test
    public void testTestBase(){
        assertTrue(millerRabinTest.testBase(2, 1L, 2, 5L));
        assertTrue(millerRabinTest.testBase(13, 1L, 4, 17L));
        assertFalse(millerRabinTest.testBase(5, 1L, 2, 5L));
        assertFalse(millerRabinTest.testBase(17, 1L, 2, 17L));
    }

    @Test
    public void testEdgeCasesTestBase(){
        assertNull(millerRabinTest.testBase(null, 1L, 1, 1L));
        assertNull(millerRabinTest.testBase(1, null, 1, 1L));
        assertNull(millerRabinTest.testBase(1, 1L, null, 1L));
        assertNull(millerRabinTest.testBase(1, 1L, 1, null));
    }
}
