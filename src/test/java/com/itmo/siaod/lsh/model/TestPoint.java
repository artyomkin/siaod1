package com.itmo.siaod.lsh.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPoint {

    @Test
    void testPointConstructorWithCoordinates() {
        Point point = new Point(3.0, 4.0);
        assertEquals(3.0, point.x);
        assertEquals(4.0, point.y);
    }

    @Test
    void testPointConstructorWithDistance() {
        double distance = 10.0;
        Point point = new Point(distance);
        assertTrue(point.x >= -distance && point.x <= distance);
        assertTrue(point.y >= -distance && point.y <= distance);
    }

    @Test
    void testGenerateRandomPoints() {
        int pointsNumber = 5;
        double distance = 10.0;
        List<Point> points = Point.generateRandomPoints(pointsNumber, distance);

        assertEquals(pointsNumber, points.size());
        for (Point point : points) {
            assertTrue(point.x >= -distance && point.x <= distance);
            assertTrue(point.y >= -distance && point.y <= distance);
        }
    }

    @Test
    void testToString() {
        Point point = new Point(3.0, 4.0);
        assertEquals("(3.0; 4.0)", point.toString());
    }

}
