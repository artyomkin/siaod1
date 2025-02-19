public class DeterministicMillerRabinTest implements IDeterministicMillerRabinTest {
    // a bases for all numbers < 2^64
    private static final Integer[] A_BASES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

    @Override
    public Boolean testMillerRabinDeterministic(Integer n) {
        if (n == null) return null;
        // n here is always odd
        Integer d = n - 1;
        Integer r = 0;
        while (d % 2 == 0){
            d /= 2;
            r++;
        }

        for (Integer a : A_BASES){
            if (!testBase(a, d, r, n)) {
                return false;
            }
        }
        return true;
    }

    private Boolean testBase(Integer a, Integer d, Integer r, Integer n){
        Integer x = fastPowModulo(a, d, n);

        if (x == 1 || x == n - 1) {
            return true;
        }
        for (int i = 0; i < r - 1; i++) {
            x = fastPowModulo(x, 2, n);
            if (x == n - 1) {
                return true;
            }
        }

        return false;
    }

    private Integer fastPowModulo(Integer base, Integer exponent, Integer modulo){
        Integer res = 1;
        Integer d = exponent;
        while (d > 0) {
            if (d % 2 == 1){
                res = (res * base) % modulo;
            }
            base = (base * base) % modulo;
            d /= 2;
        }
        return res;
    }

}
