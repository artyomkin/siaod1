package com.itmo.siaod.lsh.model;

import java.util.List;

public interface ILine {

    enum Location {
        UPPER,
        LOWER,
        COLLINEAR
    }

    ILine.Location getLocation(double x, double y);
}
