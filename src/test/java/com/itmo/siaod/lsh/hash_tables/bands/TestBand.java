package com.itmo.siaod.lsh.hash_tables.bands;

import com.itmo.siaod.lsh.exceptions.IncorrectBandException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestBand {
    @Test
    void testConstructorSuccess() throws IncorrectBandException {
        List<List<Boolean>> signatures = List.of(
                List.of(true, false, true, false, true),
                List.of(false, true, false, true, false),
                List.of(true, true, false, false, true)
        );
        int offset = 1;
        int bandSize = 3;
        Band band = new Band(signatures, offset, bandSize);
        assertNotNull(band);
        assertEquals(3, band.getBand().size());
        List<List<Boolean>> bandData = band.getBand();
        assertEquals(List.of(false, true, false), bandData.get(0));
        assertEquals(List.of(true, false, true), bandData.get(1));
        assertEquals(List.of(true, false, false), bandData.get(2));
    }

    @Test
    void testConstructorWithNullSignatures() {
        Exception exception = assertThrows(IncorrectBandException.class, () -> {
            new Band(null, 0, 3);
        });
        assertEquals("Signatures are null.", exception.getMessage());
    }

    @Test
    void testConstructorWithOffsetOutOfBounds() {
        List<List<Boolean>> signatures = List.of(
                List.of(true, false, true),
                List.of(false, true, false)
        );
        Exception exception = assertThrows(IncorrectBandException.class, () -> {
            new Band(signatures, 2, 2);
        });
        assertEquals("Offset + band size is out of bound.", exception.getMessage());
    }

    @Test
    void testGetBand() throws IncorrectBandException {
        List<List<Boolean>> signatures = List.of(
                List.of(true, false, true),
                List.of(false, true, false)
        );
        Band band = new Band(signatures, 0, 2);
        List<List<Boolean>> bandData = band.getBand();
        assertEquals(2, bandData.size());
        assertEquals(List.of(true, false), bandData.get(0));
        assertEquals(List.of(false, true), bandData.get(1));
    }
}
