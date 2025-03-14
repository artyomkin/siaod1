package com.itmo.siaod.lsh.lsh;

import com.itmo.siaod.lsh.model.Point;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class TestLSH {

    LSH lsh;

    @BeforeEach
    public void setup(){
        List<Point> points = List.of(new Point(0,0), new Point(1, 1), new Point(1000,1000));
        this.lsh = new LSH(points);
    }
}
