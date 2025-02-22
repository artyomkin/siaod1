package com.itmo.siaod.perfect_hash.hash_tables.buckets;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_functions.UniversalLinearHashFunction;
import com.itmo.siaod.perfect_hash.hash_tables.IBucket;
import com.itmo.siaod.perfect_hash.hash_tables.ISimpleBucket;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;

import java.util.ArrayList;

public class HashTableBucket implements IBucket {

    private ArrayList<Integer> hashTable;
    private IUniversalHashFunction universalHashFunction;

    public HashTableBucket(ISimpleBucket simpleBucket) throws TooBigNumberException {
        initHashTable(simpleBucket.getVals());
    }

    @Override
    public boolean putKey(Integer key, Integer val) throws TooBigNumberException {
        return put(key, val);
    }

    @Override
    public boolean put(Integer key, Integer val) throws TooBigNumberException {
        if (this.hashTable == null || this.hashTable.size() <= 1 || this.universalHashFunction == null) return false;

        int index = Math.toIntExact(this.universalHashFunction.hash(key));
        this.hashTable.set(index, val);
        return true;
    }

    @Override
    public Integer get(Integer key) throws TooBigNumberException {
        if (this.hashTable == null || this.hashTable.size() <= 1 || this.universalHashFunction == null) return null;

        int index = Math.toIntExact(this.universalHashFunction.hash(key));
        return this.hashTable.get(index);
    }

    @Override
    public int getSize() {
        return this.hashTable.size();
    }

    @Override
    public boolean delete(Integer key) throws TooBigNumberException {
        if (this.hashTable == null || this.hashTable.size() <= 1 || this.universalHashFunction == null) return false;

        int index = Math.toIntExact(this.universalHashFunction.hash(key));
        this.hashTable.set(index, null);
        return true;
    }

    @Override
    public void resetValues() {
        this.hashTable.replaceAll(ignored -> null);
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public String toString() {
        return this.hashTable.toString();
    }

    protected int genHashTableSize(Integer keysCount) {
        double antiCollisionFactor = (12d + (RandomSiaod.nextInt() % 9d)) / 10d;
        return (int) (antiCollisionFactor * Math.pow(keysCount, 2));
    }

    public void initHashTable(ArrayList<Integer> keysArr) throws TooBigNumberException {
        if (keysArr == null || keysArr.size() <= 1) return;

        int hashTableSize = genHashTableSize(keysArr.size());
        this.universalHashFunction = chooseHashFunction(hashTableSize, keysArr);
        this.hashTable = new ArrayList<>(hashTableSize);
        for (int i = 0; i < hashTableSize; i++){
            this.hashTable.add(null);
        }
    }

    protected IUniversalHashFunction chooseHashFunction(Integer hashTableSize, ArrayList<Integer> keysArr) throws TooBigNumberException {
        IUniversalHashFunction function = null;
        while (doesFunctionMakeCollisions(keysArr, function)){
            function = shuffleFunction(keysArr, hashTableSize);
        }
        return function;
    }

    protected UniversalLinearHashFunction shuffleFunction(ArrayList<Integer> keysArr, int hashTableSize) throws TooBigNumberException {
        return new UniversalLinearHashFunction(keysArr, hashTableSize);
    }

    protected boolean doesFunctionMakeCollisions(ArrayList<Integer> keysArr, IUniversalHashFunction function) throws TooBigNumberException {
        if (keysArr == null || function == null) return true;
        ArrayList<Integer> hashes = new ArrayList<>();
        for (Integer key : keysArr) {
            Integer hash = Math.toIntExact(function.hash(key));
            // collision
            if (hashes.contains(hash)) return true;

            hashes.add(hash);
        }
        return false;
    }

    protected IUniversalHashFunction getHashFunction(){
        return this.universalHashFunction;
    }

}
