package com.itmo.siaod.lsh.hash_tables.buckets;

import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.ArrayList;
import java.util.List;

public class SetBucket implements IBucket {

    private MutableIntList keys;
    private MutableIntList values;

    public SetBucket() {
        this.keys = IntLists.mutable.empty();
        this.values = IntLists.mutable.empty();
    }

    @Override
    public boolean put(int key, int val) {
        this.keys.add(key);
        this.values.add(val);
        return true;
    }

    @Override
    public boolean delete(int key) {
        for (int i = 0; i < keys.size(); i++){
            if (this.keys.get(i) == key){
                this.keys.removeAtIndex(i);
                this.values.removeAtIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public int get(int key) {
        for (int i = 0; i < this.keys.size(); i++){
            if (this.keys.get(i) == key){
                return this.values.get(i);
            }
        }
        return Integer.MIN_VALUE;
    }

    public MutableIntList getVals(){
        return this.values;
    }

    //@Override
    //public List<MutableIntList> getAll() {
    //    return List.of(keys, values);
    //}
}
