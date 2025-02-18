import java.util.List;
import java.util.Random;

public class IUniversalLinearHashFunction {
    private Integer a;
    private Integer b;
    private Integer p;
    private Integer m;
    private Random rnd;

    public IUniversalLinearHashFunction(List<Integer> allPossibleKeys) {
        this.rnd = new Random();

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

    private Integer findNextPrime(Integer n){
        // Miller-Rabin test
        return 0;
    }
}
