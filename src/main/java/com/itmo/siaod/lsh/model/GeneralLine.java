package com.itmo.siaod.lsh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneralLine implements ILine {
    public double A;
    public double B;
    public double C;

    public GeneralLine(Double distance) {
        do {
            Point p1 = new Point(distance);
            Point p2 = new Point(distance);
            this.A = p2.y - p1.y;
            this.B = p1.x - p2.x;
            this.C = -1 * this.A * p2.x - this.B * p2.y;
        } while (this.B == 0d);
    }


    public double f(double x, double y){
        return A * x + B * y + C;
    }

    @Override
    public ILine.Location getLocation(double x, double y) {
        double f0 = f(x, y);
        if (f0 == 0) {
            return ILine.Location.COLLINEAR;
        }

        boolean isUpper = this.B > 0;
        if (f0 < 0){
            isUpper = !isUpper;
        }

        if (isUpper){
            return ILine.Location.UPPER;
        } else {
            return ILine.Location.LOWER;
        }

    }

    public static List<ILine> generateLines(int numberOfLines, double distance) {
        List<ILine> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++){
            lines.add(new GeneralLine(distance));
        }
        return lines;
    }

}
