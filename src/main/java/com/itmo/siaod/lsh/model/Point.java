package com.itmo.siaod.lsh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Point {
    public double x;
    public double y;
    public static Random rnd = new Random();

    public Point(){};

    public Point(double distance){
        double minX = -1 * distance;
        double minY = -1 * distance;
        double maxX = distance;
        double maxY = distance;

        this.x = generateRandomX(minX, maxX);
        this.y = generateRandomY(minY, maxY);
    };

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static List<Point> generateRandomPoints(int pointsNumber, double distance) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < pointsNumber; i++){
            points.add(new Point(distance));
        }
        return points;
    }

    private Double generateRandomY(double minY, double maxY){
        return minY + (maxY - minY) * rnd.nextDouble();
    }

    private Double generateRandomX(double minX, double maxX){
        return minX + (maxX - minX) * rnd.nextDouble();
    }

    @Override
    public String toString(){
        return "(" + String.valueOf(this.x) + "; " + String.valueOf(this.y) + ")";
    }

    @Override
    public boolean equals(Object o){
        if (!this.getClass().isAssignableFrom(o.getClass())){
            return false;
        }
        Point p = (Point) o;
        return p.x == this.x && p.y == this.y;
    }
}
