package com.itmo.siaod;

import com.itmo.siaod.exceptions.CannotFindNextPrimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashTableSiaod implements IHashTableSiaod {

    private IUniversalHashFunction hashFunction;
    private Random rnd;
    private Integer hashTableSize;
    private ArrayList<ArrayList<Integer>> hashTable;

    public HashTableSiaod(List<Integer> allPossibleKeys) throws CannotFindNextPrimeException {
        // hashTableSize from 1.2n to 2n
        List<Integer> allUniquePossibleKeys = allPossibleKeys.stream().distinct().toList();
        Integer allUniquePossibleKeysCount = allUniquePossibleKeys.size();

        this.rnd = new Random();
        this.hashTableSize = (12 + (rnd.nextInt() & Integer.MAX_VALUE) % 9) / 10 * allUniquePossibleKeysCount;
        this.hashFunction = new UniversalLinearHashFunction(allPossibleKeys, this.hashTableSize);
    }

    protected void initializeHashTable(List<Integer> allPossibleKeys){
        this.hashTable = new ArrayList<>();
        for (int i = 0; i < allPossibleKeys.size(); i++){
            hashTable.add(null);
        }
        for (Integer key : allPossibleKeys) {
        }
    }

    protected void addKey(Integer key) {
        Integer firstLayerIndex = this.hashFunction.hash(key);
        if (this.hashTable.get(firstLayerIndex) != null) {

        } else {

        }
    }

    protected void addFirstLevelKey(Integer key){
        if (this.hashTable.get(key) != null) return;

        ArrayList<Integer> secondLevelHashTable = new ArrayList<>();
    }

    protected void initializeSecondLevelHashTable(Integer index, Integer key) {
        ArrayList<Integer> secondLevelHashTable = new ArrayList<>();
        // TODO
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
