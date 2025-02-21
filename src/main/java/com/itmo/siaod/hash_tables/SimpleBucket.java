package com.itmo.siaod.hash_tables;

import com.itmo.siaod.exceptions.TooBigNumberException;
import com.itmo.siaod.hash_functions.UniversalLinearHashFunction;

import java.util.ArrayList;
import java.util.Random;

public class SimpleBucket implements IBucket {

    private ArrayList<Integer> hashTable;
    private Integer hashTableSize;
    private Random rnd;
    private IUniversalHashFunction universalHashFunction;

    public SimpleBucket() {
        initCommon();
    }

    public SimpleBucket(Integer initialKey) {
        initCommon();
        putKey(initialKey);
    }

    protected void initCommon(){
        this.hashTable = new ArrayList<>();
        this.rnd = new Random();
        this.hashTableSize = genHashTableSize(this.hashTable);
    }

    @Override
    public void putKey(Integer key) {
        this.hashTable.add(key);
    }

    @Override
    public void initHashTable() throws TooBigNumberException {
        if (this.hashTable == null || this.hashTable.size() <= 1) return;
        chooseHashFunction();
    }

    protected UniversalLinearHashFunction shuffleFunction(ArrayList<Integer> hashTable, int hashTableSize) throws TooBigNumberException {
        return new UniversalLinearHashFunction(hashTable, hashTableSize);
    }

    protected int genHashTableSize(ArrayList<Integer> hashTable) {
        int antiCollisionFactor = (12 + (rnd.nextInt() & Integer.MAX_VALUE) % 9) / 10;
        return (int) (antiCollisionFactor * Math.pow(hashTable.size(), 2));
    }

    protected void chooseHashFunction() throws TooBigNumberException {
        while (!testFunction(this.hashTable, this.universalHashFunction)){
            this.universalHashFunction = shuffleFunction(this.hashTable, this.hashTableSize);
        }
    }

    protected boolean testFunction(ArrayList<Integer> keysArr, IUniversalHashFunction function) {
        ArrayList<Integer> hashes = new ArrayList<>();
        for (Integer key : keysArr) {
            Integer hash = function.hash(key);
            // collision
            if (hashes.contains(hash)) return false;

            hashes.add(hash);
        }
        return true;
    }

}
