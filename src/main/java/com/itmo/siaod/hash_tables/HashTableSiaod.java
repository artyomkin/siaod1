package com.itmo.siaod.hash_tables;

import com.itmo.siaod.IHashTableSiaod;
import com.itmo.siaod.exceptions.TooBigNumberException;
import com.itmo.siaod.hash_functions.UniversalLinearHashFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashTableSiaod implements IHashTableSiaod {

    private IUniversalHashFunction hashFunction;
    private Random rnd;
    private Integer hashTableSize;
    private ArrayList<IBucket> hashTable;

    public HashTableSiaod(List<Integer> allPossibleKeys) throws TooBigNumberException {
        int allUniquePossibleKeysCount = init(allPossibleKeys);
        this.hashTableSize = (12 + (rnd.nextInt() & Integer.MAX_VALUE) % 9) / 10 * allUniquePossibleKeysCount;
    }

    public HashTableSiaod(List<Integer> allPossibleKeys, int hashTableSize) throws TooBigNumberException {
        init(allPossibleKeys);
        this.hashTableSize = hashTableSize;
    }

    protected int init(List<Integer> allPossibleKeys) throws TooBigNumberException {
        // hashTableSize from 1.2n to 2n
        List<Integer> allUniquePossibleKeys = allPossibleKeys.stream().distinct().toList();
        int allUniquePossibleKeysCount = allUniquePossibleKeys.size();

        this.rnd = new Random();
        this.hashFunction = new UniversalLinearHashFunction(allPossibleKeys, this.hashTableSize);
        return allUniquePossibleKeysCount;
    }

    protected void initializeFirstLayer(List<Integer> allPossibleKeys){
        this.hashTable = new ArrayList<>();
        for (int i = 0; i < allPossibleKeys.size(); i++){
            hashTable.add(null);
        }
        for (Integer key : allPossibleKeys) {
            addKey(key);
        }
    }

    protected void addKey(Integer key) {
        if (key == null) return;
        Integer index = this.hashFunction.hash(key);
        if (this.hashTable.get(index) == null) {
            IBucket simpleBucket = new SimpleBucket(key);
            this.hashTable.set(index, simpleBucket);
        } else {
            this.hashTable.get(index).putKey(key);
        }
    }

    protected void initializeSecondLayer() {
    }

    @Override
    public void put(Integer key, Integer val) {

    }

    @Override
    public void get(Integer key) {

    }

    @Override
    public void delete(Integer key) {

    }
}
