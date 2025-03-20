package com.itmo.siaod.lsh.similarity_identifier;

import com.itmo.siaod.lsh.hash_tables.IHashTableSiaod;
import com.itmo.siaod.lsh.model.Point;
import org.apache.maven.surefire.shared.lang3.mutable.Mutable;
import org.eclipse.collections.api.list.primitive.MutableDoubleList;
import org.eclipse.collections.impl.factory.primitive.DoubleLists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestSimilarityIdentifier {
    @Test
    void testConstructor() {
        List<Point> points = List.of(
                new Point(1.0, 2.0),
                new Point(3.0, 4.0),
                new Point(5.0, 6.0)
        );
        //List<Double> timings = new ArrayList<>();
        //SimilarityIdentifier identifier = new SimilarityIdentifier(points, timings);
        //assertNotNull(identifier.getPreliminarySimilarSetsIndices());
        //assertNotNull(identifier.toString());
    }

    @Test
    void testCalculatePreliminarySimilarIndices() {
        List<Point> points = List.of(
                new Point(1.0, 2.0),
                new Point(3.0, 4.0),
                new Point(5.0, 6.0)
        );
        //List<Double> timings = new ArrayList<>();
        //SimilarityIdentifier identifier = new SimilarityIdentifier(points, timings);
        ////List<List<Integer>> similarIndices = identifier.getPreliminarySimilarSetsIndices();
        //assertNotNull(similarIndices);
    }

    @Test
    void testGetPreliminarySimilarSetsIndices() {
        List<Point> points = List.of(
                new Point(1.0, 2.0),
                new Point(3.0, 4.0),
                new Point(5.0, 6.0)
        );
        //List<Double> timings = new ArrayList<>();
        //SimilarityIdentifier identifier = new SimilarityIdentifier(points, timings);
        //List<List<Integer>> similarIndices = identifier.getPreliminarySimilarSetsIndices();
        //assertNotNull(similarIndices);
    }

    @Test
    void testToString() {
        // Создаем список точек
        List<Point> points = List.of(
                new Point(1.0, 2.0),
                new Point(3.0, 4.0),
                new Point(5.0, 6.0)
        );
        //List<Double> timings = new ArrayList<>();
        //SimilarityIdentifier identifier = new SimilarityIdentifier(points, timings);
        //String result = identifier.toString();
        //assertNotNull(result);
        //assertFalse(result.isEmpty());
    }

    @Test
    void testDistributeSignatures() {
        List<List<Boolean>> signatures = new ArrayList<>();
        Random rnd = new Random();
        List<Boolean> signature = new ArrayList<>();
        for (int i = 0; i < 30 * 100; i++){
            signature.add(rnd.nextBoolean());
        }
        signatures.add(signature);
        //MutableDoubleList timings = DoubleLists.mutable.empty();
        //IHashTableSiaod hashTable = SimilarityIdentifier.distributeSignatures(signatures, timings);
        //assertNotNull(hashTable);
        //assertFalse(hashTable.getAllEntries().isEmpty());
    }
}
