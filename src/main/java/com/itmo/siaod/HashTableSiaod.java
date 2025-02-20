package com.itmo.siaod;

import com.itmo.siaod.exceptions.CannotFindNextPrimeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HashTableSiaod {

    private IUniversalHashFunction hashFunction;
    private Random rnd;
    private Integer hashTableSize;
    private ArrayList<ArrayList<Integer>> hashTable;

    public HashTableSiaod(List<Integer> allPossibleKeys) throws CannotFindNextPrimeException {
        // hashTableSize from 1.2n to 2n
        List<Integer> allUniquePossibleKeys = allPossibleKeys.stream().distinct().toList();
        Integer allUniquePossibleKeysCount = allUniquePossibleKeys.size();

        this.hashTableSize = (12 + (rnd.nextInt() & Integer.MAX_VALUE) % 9) / 10 * allUniquePossibleKeysCount;
        this.hashFunction = new UniversalLinearHashFunction(allPossibleKeys, this.hashTableSize);
        this.hashTable = new ArrayList<>();
    }
}
