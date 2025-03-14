package com.itmo.siaod.lsh.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGeneralLine {
    @Test
    void testGeneralLineConstructor() {
        double distance = 10.0;
        GeneralLine line = new GeneralLine(distance);
        assertNotEquals(0.0, line.B);
        assertNotNull(line.A);
        assertNotNull(line.C);
    }

    @Test
    void testF() {
        GeneralLine line = new GeneralLine(10.0);
        line.A = 2.0;
        line.B = 3.0;
        line.C = -6.0;
    }

    @Test
    void testGetLocationCollinear() {
        GeneralLine line = new GeneralLine(10.0);
        line.A = 1.0;
        line.B = -1.0;
        line.C = 0.0;
        ILine.Location location = line.getLocation(2.0, 2.0);
        assertEquals(ILine.Location.COLLINEAR, location);
    }

    @Test
    void testGetLocationUpper() {
        GeneralLine line = new GeneralLine(10.0);
        line.A = 1.0;
        line.B = -1.0;
        line.C = 0.0;
        ILine.Location location = line.getLocation(3.0, 20.0);
        assertEquals(ILine.Location.UPPER, location);
    }

    @Test
    void testGetLocationLower() {
        GeneralLine line = new GeneralLine(10.0);
        line.A = 1.0;
        line.B = -1.0;
        line.C = 0.0;
        ILine.Location location = line.getLocation(2.0, -30.0);
        assertEquals(ILine.Location.LOWER, location);
    }

    @Test
    void testGenerateLines() {
        int numberOfLines = 5;
        double distance = 10.0;
        List<ILine> lines = GeneralLine.generateLines(numberOfLines, distance);
        assertEquals(numberOfLines, lines.size());
        for (ILine line : lines) {
            assertNotNull(line);
            assertTrue(line instanceof GeneralLine);
        }
    }
}
