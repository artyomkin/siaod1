package com.itmo.siaod.lsh.signatures;

import com.itmo.siaod.lsh.model.GeneralLine;
import com.itmo.siaod.lsh.model.ILine;
import com.itmo.siaod.lsh.model.Point;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LSH {

    private List<ILine> lines;
    private List<List<Boolean>> signatures;
    private int NUMBER_OF_LINES = 3000;

    public LSH(List<Point> points){
        double maxX = points.stream().map(p -> Math.abs(p.x)).max(Comparator.naturalOrder()).get();
        double maxY = points.stream().map(p -> Math.abs(p.y)).max(Comparator.naturalOrder()).get();
        double maxDistance = Math.max(maxX, maxY);
        this.lines = GeneralLine.generateLines(NUMBER_OF_LINES, maxDistance);
        this.signatures = new ArrayList<>();
        for (int i = 0; i < points.size(); i++){
            this.signatures.add(new ArrayList<>());
            Point p = points.get(i);
            for (int j = 0; j < lines.size(); j++){
                this.signatures.get(i).add(lines.get(j).getLocation(p.x, p.y).equals(ILine.Location.UPPER));
            }
        }
    }

    public List<List<Boolean>> getSignatures(){
        return this.signatures;
    }


}
