package com.itmo.siaod.perfect_hash.hash_tables.buckets;

import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.hash_tables.ISimpleBucket;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.ArrayList;

public class SimpleBucket implements ISimpleBucket {

    private MutableIntList values;

    public SimpleBucket() {
        this.values = IntLists.mutable.empty();
    }

    public SimpleBucket(int initialKey){
        this.values = IntLists.mutable.empty();
        this.values.add(initialKey);
    }

    @Override
    public MutableIntList getVals() {
        return this.values;
    }

    @Override
    public boolean putKey(int key, int val) {
        this.values.add(val);
        return true;
    }

    @Override
    public boolean put(int key, int val) {
        if (this.values.isEmpty()) {
            this.values.add(val);
        } else {
            this.values.set(0, val);
        }
        return true;
    }

    @Override
    public int get(int key) throws CollisionException {
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
    public boolean delete(int key) {
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
