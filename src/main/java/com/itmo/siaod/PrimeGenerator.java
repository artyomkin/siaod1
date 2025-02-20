package com.itmo.siaod;

public class PrimeGenerator implements IPrimeGenerator {

    private IDeterministicMillerRabinTest millerRabinTest;

    public PrimeGenerator() {
        this.millerRabinTest = new DeterministicMillerRabinTest();
    }

    @Override
    public Long findNextPrime(Long n){
        if (n == null) return null;
        Integer cnt = 1;
        while (!isPrime((n + cnt))){
            cnt++;
        }
        return n + cnt;
    }

    protected Boolean isPrime(Long n){
        if (n == null) return null;
        if (n <= 1) {
            return false;
        }
        if (n == 2 || n == 3){
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        return this.millerRabinTest.testMillerRabinDeterministic(n);
    }
}
