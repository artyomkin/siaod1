import com.itmo.siaod.extendible_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.min_hash.similarity_identifier.ISimilarityIdentifier;
import com.itmo.siaod.min_hash.similarity_identifier.SimilarityIdentifier;
import com.itmo.siaod.perfect_hash.IHashTableSiaod;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws TooBigNumberException, CollisionException {
        Set<Integer> a = Set.of(1, 3, 49, 29, 28);
        Set<Integer> b = Set.of(2, 31, 9, 21, 312);
        List<Set<Integer>> sets = List.of(a,b);
        ISimilarityIdentifier similarityIdentifier = new SimilarityIdentifier(sets);
        System.out.println(similarityIdentifier.toString());
    }
}