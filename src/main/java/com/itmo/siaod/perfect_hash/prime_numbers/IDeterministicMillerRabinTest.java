package com.itmo.siaod.perfect_hash.prime_numbers;

public interface IDeterministicMillerRabinTest {
    // a bases for all numbers < 10^18 < 2^64
    int[] A_BASES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

    static boolean testMillerRabinDeterministic(long n) {
        // n here is always odd
        long d = n - 1;
        int r = 0;
        while (d % 2 == 0){
            d /= 2;
            r++;
        }

        for (int a : A_BASES){
            if (a >= n - 1) break;
            if (!testBase(a, d, r, n)) {
                return false;
            }
        }
        return true;
    }

    static boolean testBase(int a, long d, int r, long n){
        long x = fastPowModulo((long) a, d, n);

        if (x == 1 || x == n - 1) {
            return true;
        }
        for (int i = 0; i < r - 1; i++) {
            x = fastPowModulo(x, 2L, n);
            if (x == n - 1) {
                return true;
            }
        }

        return false;
    }

    static long fastPowModulo(long base, long exponent, long modulo){
        if (modulo == 1 || base == 0) return 0L;
        long res = 1L;
        long d = exponent;
        long a = base;
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
