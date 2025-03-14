package com.itmo.siaod.lsh.hash_tables.bands;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestIBander {
    @Test
    void testSplitIntoBandsSuccess() {
        List<List<Boolean>> signatures = new ArrayList<>();
        Random rnd = new Random();
        List<Boolean> signature = new ArrayList<>();
        for (int i = 0; i < 30 * 100; i++){
            signature.add(rnd.nextBoolean());
        }
        signatures.add(signature);
        List<IBand> bands = IBander.splitIntoBands(signatures);
        assertNotNull(bands);
        assertEquals(IBander.BAND_NUMBER, bands.size());
        for (IBand band : bands) {
            assertNotNull(band);
        }
    }

    @Test
    void testSplitIntoBandsWithIncorrectBandException() {
        List<List<Boolean>> signatures = List.of(
                List.of(true, false),
                List.of(false, true),
                List.of(true, true)
        );
        List<IBand> bands = IBander.splitIntoBands(signatures);
        assertNull(bands);
    }

    @Test
    void testSplitIntoBandsWithEmptySignatures() {
        List<List<Boolean>> signatures = List.of();
        List<IBand> bands = IBander.splitIntoBands(signatures);
        assertTrue(bands.isEmpty());
    }
}
