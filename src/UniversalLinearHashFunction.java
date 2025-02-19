import java.util.List;
import java.util.Random;

public class UniversalLinearHashFunction implements IUniversalHashFunction {
    private Integer a;
    private Integer b;
    private Integer p;
    private Integer m;
    private Random rnd;
    private IDeterministicMillerRabinTest millerRabinTest;

    public UniversalLinearHashFunction(List<Integer> allPossibleKeys) {
        this.rnd = new Random();
        this.millerRabinTest = new DeterministicMillerRabinTest();

        Integer allPossibleKeysCount = allPossibleKeys.size();
        Integer p = findNextPrime(allPossibleKeysCount);
        Integer a = rnd.nextInt() % p;
        if (a == 0){
            a = 1;
        }
        Integer b = rnd.nextInt() % p;
        // m from 1.2n to 2n
        Integer m = (12 + rnd.nextInt() % 9) / 20 * allPossibleKeysCount;
    }

    @Override
    public Integer hash(Integer key) {
        return 0;
    }

    private Integer findNextPrime(Integer n){
        if (n == null) return null;
        Integer cnt = 1;
        while (!isPrime(n + cnt)){
            cnt++;
        }
        return n + cnt;
    }

    private Boolean isPrime(Integer n){
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
