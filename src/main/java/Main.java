import com.itmo.siaod.extendible_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.min_hash.signatures.Signature;
import com.itmo.siaod.min_hash.similarity_identifier.IJaccardCoef;
import com.itmo.siaod.min_hash.similarity_identifier.ISimilarityIdentifier;
import com.itmo.siaod.min_hash.similarity_identifier.SimilarityIdentifier;
import com.itmo.siaod.perfect_hash.IHashTableSiaod;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws TooBigNumberException, CollisionException {
        int numbers = 100;
        int setNumber = 3000;
        List<Set<Integer>> sets = new ArrayList<>();
        for (int s = 0; s < setNumber; s++){
            Set<Integer> temp = new HashSet<>();
            for (int i = 0; i < numbers; i++){
                temp.add(RandomSiaod.nextInt() % 10000);
            }
            sets.add(temp);
        }
        double start = System.currentTimeMillis();
        ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(sets);
        System.out.println(similarityIdentifier.toString());
        double end = System.currentTimeMillis();
        System.out.println("elapsed: " + (end - start) + " milliseconds");
    }
}