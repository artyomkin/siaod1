package com.itmo.siaod;

import com.itmo.siaod.prime_numbers.PrimeGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TestPrimeGenerator {

    private PrimeGenerator primeGenerator;

    @Before
    public void setup(){
        this.primeGenerator = new PrimeGenerator();
    }

    @Test
    public void testIsPrime(){
        assertTrue(primeGenerator.isPrime(2L));
        assertTrue(primeGenerator.isPrime(3L));
        assertTrue(primeGenerator.isPrime(5L));
        assertTrue(primeGenerator.isPrime(113L));
        assertFalse(primeGenerator.isPrime(1007L));
        assertFalse(primeGenerator.isPrime(1000L));
        assertFalse(primeGenerator.isPrime(4L));
    }

    @Test
    public void testEdgeCasesIsPrime(){
        assertNull(primeGenerator.isPrime(null));
        assertFalse(primeGenerator.isPrime(0L));
        assertFalse(primeGenerator.isPrime(-3L));
    }

    @Test
    public void testFindNextPrime(){
        assertEquals((long)primeGenerator.findNextPrime(0L), 2);
        assertEquals((long)primeGenerator.findNextPrime(1L), 2);
        assertEquals((long)primeGenerator.findNextPrime(2L), 3);
        assertEquals((long)primeGenerator.findNextPrime(3L), 5);
        assertEquals((long)primeGenerator.findNextPrime(1000L), 1009);
        assertEquals((long)primeGenerator.findNextPrime(Long.MAX_VALUE), 922337203687L);
    }

    @Test
    public void testEdgeCasesFindNextPrime(){
        assertNull(primeGenerator.findNextPrime(null));
        assertNull(primeGenerator.findNextPrime((long) Integer.MAX_VALUE));
    }

    @Test
    public void testFindNextPrimePerformance() {
        Random rnd = new Random();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++){
            primeGenerator.findNextPrime((rnd.nextLong() & Long.MAX_VALUE) % 1_000_000L + 1_990_000_000L);
        }
        Long end = System.currentTimeMillis();
        Long primeGeneratorEstimatedTime = end - start;

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++){
            findNextPrimeSqrt((rnd.nextLong() & Long.MAX_VALUE) % 1_000_000L + 1_990_000_000L);
        }
        end = System.currentTimeMillis();
        Long sqrtPrimeGeneratorEstimatedTime = end - start;

        System.out.println(String.format("Searching for prime numbers with Miller-Rabin test estimated: %d millis", primeGeneratorEstimatedTime));
        System.out.println(String.format("Searching for prime numbers with square root test estimated: %d millis", sqrtPrimeGeneratorEstimatedTime));
        assertTrue(primeGeneratorEstimatedTime < sqrtPrimeGeneratorEstimatedTime);
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
