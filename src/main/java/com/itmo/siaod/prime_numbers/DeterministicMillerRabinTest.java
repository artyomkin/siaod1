package com.itmo.siaod.prime_numbers;

public class DeterministicMillerRabinTest implements IDeterministicMillerRabinTest {
    // a bases for all numbers < 10^18 < 2^64
    private static final Integer[] A_BASES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

    @Override
    public Boolean testMillerRabinDeterministic(Long n) {
        if (n == null) return null;
        // n here is always odd
        Long d = n - 1;
        Integer r = 0;
        while (d % 2 == 0){
            d /= 2;
            r++;
        }

        for (Integer a : A_BASES){
            if (a >= n - 1) break;
            if (!testBase(a, d, r, n)) {
                return false;
            }
        }
        return true;
    }

    protected Boolean testBase(Integer a, Long d, Integer r, Long n){
        if (a == null || d == null || r == null || n == null) return null;
        Long x = fastPowModulo(Long.valueOf(a), d, n);
        if (x == null) throw new RuntimeException("x is null");

        if (x == 1 || x == n - 1) {
            return true;
        }
        for (int i = 0; i < r - 1; i++) {
            x = fastPowModulo(x, 2L, n);
            if (x == null) throw new RuntimeException("x is null");
            if (x == n - 1) {
                return true;
            }
        }

        return false;
    }

    protected Long fastPowModulo(Long base, Long exponent, Long modulo){
        if (base == null || exponent == null || modulo == null || base < 0 || exponent < 0 || modulo < 1) return null;
        if (modulo == 1 || base == 0) return 0L;
        Long res = 1L;
        Long d = exponent;
        Long a = base;
        while (d > 0) {
            if (d % 2 == 1){
                res = (res * a) % modulo;
            }
            a = ((a % modulo) * (a % modulo)) % modulo;
            d /= 2;
        }
        return res;
    }

}
