package com.itmo.siaod.lsh.model;

import java.util.ArrayList;
import java.util.List;

public class PointCenterer {

    List<Point> centeredPoints;
    double avgX;
    double avgY;

    public PointCenterer(List<Point> points){
        this.centeredPoints = centerPoints(points);
    }

    private List<Point> centerPoints(List<Point> points){
        if (points == null || points.isEmpty()){
            return points;
        }
        this.avgX = points.stream().mapToDouble(p -> p.x).average().getAsDouble();
        this.avgY = points.stream().mapToDouble(p -> p.y).average().getAsDouble();
        List<Point> resPoints = new ArrayList<>();
        for (int i = 0; i < points.size(); i++){
            Point newPoint = new Point(points.get(i).x-avgX, points.get(i).y-avgY);
            resPoints.add(newPoint);
        }
        return resPoints;
    }

    public List<Point> getCenteredPoints(){
        return this.centeredPoints;
    }

    public Point uncenterPoint(Point p){
        return new Point(p.x + avgX, p.y + avgY);
    }

}
