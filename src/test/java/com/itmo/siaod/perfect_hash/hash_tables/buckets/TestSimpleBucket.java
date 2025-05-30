package com.itmo.siaod.perfect_hash.hash_tables.buckets;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSimpleBucket {

    @Test
    public void testEmptyConstructor() throws CollisionException {
        SimpleBucket simpleBucket = new SimpleBucket();
        assertNotNull(simpleBucket.getVals());
        assertEquals(simpleBucket.getSize(), 0);
        assertNull(simpleBucket.get(0));
        assertFalse(simpleBucket.delete(0));
    }

    @Test
    public void test1ArgConstructor() throws CollisionException {
        SimpleBucket simpleBucket = new SimpleBucket(1);
        assertTrue(simpleBucket.isSimple());
        assertEquals((int)simpleBucket.get(1), 1);
        assertEquals(simpleBucket.getSize(), 1);
    }

    @Test
    public void testMultipleValues(){
        SimpleBucket simpleBucket = new SimpleBucket();
        simpleBucket.putKey(1, 2);
        simpleBucket.putKey(2, 3);
        simpleBucket.putKey(3, 4);
        assertThrows(CollisionException.class, () -> simpleBucket.get(1));
        assertEquals(simpleBucket.getSize(), 3);
        assertEquals(simpleBucket.getVals().size(), 3);

        simpleBucket.delete(1);
        assertEquals(simpleBucket.getSize(), 0);

        simpleBucket.put(1, 2);
        simpleBucket.put(2, 3);
        simpleBucket.put(3, 4);
        simpleBucket.resetValues();
        assertEquals(simpleBucket.getSize(), 0);
    }

}
