package com.itmo.siaod.lsh.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPointCenterer {
    @Test
    void testCenterPoints() {
        List<Point> points = List.of(
                new Point(1.0, 2.0),
                new Point(3.0, 4.0),
                new Point(5.0, 6.0)
        );
        PointCenterer centerer = new PointCenterer(points);
        List<Point> centeredPoints = centerer.getCenteredPoints();
        assertEquals(-2.0, centeredPoints.get(0).x, 0.0001);
        assertEquals(-2.0, centeredPoints.get(0).y, 0.0001);
        assertEquals(0.0, centeredPoints.get(1).x, 0.0001);
        assertEquals(0.0, centeredPoints.get(1).y, 0.0001);
        assertEquals(2.0, centeredPoints.get(2).x, 0.0001);
        assertEquals(2.0, centeredPoints.get(2).y, 0.0001);
    }

    @Test
    void testCenterPointsWithEmptyList() {
        List<Point> points = List.of();
        PointCenterer centerer = new PointCenterer(points);
        assertTrue(centerer.getCenteredPoints().isEmpty());
    }

    @Test
    void testCenterPointsWithSinglePoint() {
        List<Point> points = List.of(new Point(10.0, 20.0));
        PointCenterer centerer = new PointCenterer(points);
        List<Point> centeredPoints = centerer.getCenteredPoints();
        assertEquals(0.0, centeredPoints.get(0).x, 0.0001);
        assertEquals(0.0, centeredPoints.get(0).y, 0.0001);
    }

    @Test
    void testUncenterPoint() {
        List<Point> points = List.of(
                new Point(1.0, 2.0),
                new Point(3.0, 4.0),
                new Point(5.0, 6.0)
        );
        PointCenterer centerer = new PointCenterer(points);
        Point centeredPoint = centerer.getCenteredPoints().get(0);
        Point originalPoint = centerer.uncenterPoint(centeredPoint);
        assertEquals(1.0, originalPoint.x, 0.0001);
        assertEquals(2.0, originalPoint.y, 0.0001);
    }

    @Test
    void testUncenterPointWithSinglePoint() {
        List<Point> points = List.of(new Point(10.0, 20.0));
        PointCenterer centerer = new PointCenterer(points);
        Point centeredPoint = new Point(0.0, 0.0);
        Point originalPoint = centerer.uncenterPoint(centeredPoint);
        assertEquals(10.0, originalPoint.x, 0.0001);
        assertEquals(20.0, originalPoint.y, 0.0001);
    }
}
