package com.itmo.siaod.hash_tables.buckets;

import com.itmo.siaod.exceptions.CollisionException;
import com.itmo.siaod.hash_tables.ISimpleBucket;

import java.util.ArrayList;

public class SimpleBucket implements ISimpleBucket {

    private ArrayList<Integer> values;

    public SimpleBucket() {
        this.values = new ArrayList<>();
    }

    public SimpleBucket(Integer initialKey){
        this.values = new ArrayList<>();
        this.values.add(initialKey);
    }

    @Override
    public ArrayList<Integer> getVals() {
        return this.values;
    }

    @Override
    public boolean putKey(Integer key, Integer val) {
        this.values.add(val);
        return true;
    }

    @Override
    public boolean put(Integer key, Integer val) {
        if (this.values.isEmpty()) {
            this.values.add(val);
        } else {
            this.values.set(0, val);
        }
        return true;
    }

    @Override
    public Integer get(Integer key) throws CollisionException {
        if (this.values.isEmpty()) return null;
        if (this.values.size() > 1) {
            throw new CollisionException("The bucket has collisions.");
        }
        return this.values.getFirst();
    }

    @Override
    public int getSize() {
        return this.values.size();
    }

    @Override
    public boolean delete(Integer key) {
        if (this.values.isEmpty()) return false;
        this.values.clear();
        return true;
    }

    @Override
    public void resetValues() {
        this.values.clear();
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public String toString(){
        return this.values.toString();
    }
}
