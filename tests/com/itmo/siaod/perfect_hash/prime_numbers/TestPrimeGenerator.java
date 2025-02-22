package com.itmo.siaod.perfect_hash.prime_numbers;

import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPrimeGenerator {

    @Test
    public void testIsPrime(){
        assertTrue(IPrimeGenerator.isPrime(2L));
        assertTrue(IPrimeGenerator.isPrime(3L));
        assertTrue(IPrimeGenerator.isPrime(5L));
        assertTrue(IPrimeGenerator.isPrime(113L));
        assertFalse(IPrimeGenerator.isPrime(1007L));
        assertFalse(IPrimeGenerator.isPrime(1000L));
        assertFalse(IPrimeGenerator.isPrime(4L));
    }

    @Test
    public void testEdgeCasesIsPrime(){
        assertNull(IPrimeGenerator.isPrime(null));
        assertFalse(IPrimeGenerator.isPrime(0L));
        assertFalse(IPrimeGenerator.isPrime(-3L));
    }

    @Test
    public void testFindNextPrime(){
        assertEquals((long)IPrimeGenerator.findNextPrime(0L), 2);
        assertEquals((long)IPrimeGenerator.findNextPrime(1L), 2);
        assertEquals((long)IPrimeGenerator.findNextPrime(2L), 3);
        assertEquals((long)IPrimeGenerator.findNextPrime(3L), 5);
        assertEquals((long)IPrimeGenerator.findNextPrime(1000L), 1009);
        assertEquals((long)IPrimeGenerator.findNextPrime((long) Integer.MAX_VALUE), (long) 2147483659L);
    }

    @Test
    public void testEdgeCasesFindNextPrime(){
        assertNull(IPrimeGenerator.findNextPrime(null));
    }

    @Test
    public void testFindNextPrimePerformance() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++){
            IPrimeGenerator.findNextPrime(RandomSiaod.nextInt() % 1_000_000L + 1_990_000_000L);
        }
        Long end = System.currentTimeMillis();
        Long IPrimeGeneratorEstimatedTime = end - start;

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++){
            findNextPrimeSqrt(RandomSiaod.nextInt() % 1_000_000L + 1_990_000_000L);
        }
        end = System.currentTimeMillis();
        Long sqrtPrimeGeneratorEstimatedTime = end - start;

        System.out.println(String.format("Searching for prime numbers with Miller-Rabin test estimated: %d millis", IPrimeGeneratorEstimatedTime));
        System.out.println(String.format("Searching for prime numbers with square root test estimated: %d millis", sqrtPrimeGeneratorEstimatedTime));
        assertTrue(IPrimeGeneratorEstimatedTime < sqrtPrimeGeneratorEstimatedTime);
    }

    private Long findNextPrimeSqrt(Long n){
        Long num = n;
        while (!isPrimeSqrt(num)){
            num++;
        }
        return num;
    }

    private boolean isPrimeSqrt(Long n){
        for (int i = 2; i < Math.sqrt(n); i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

}
