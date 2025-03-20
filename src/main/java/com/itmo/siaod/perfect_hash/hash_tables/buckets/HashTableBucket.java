package com.itmo.siaod.perfect_hash.hash_tables.buckets;

import com.itmo.siaod.perfect_hash.hash_tables.ISimpleBucket;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_functions.UniversalLinearTableHashFunction;
import com.itmo.siaod.perfect_hash.hash_tables.IBucket;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.ArrayList;

public class HashTableBucket implements IBucket {

    private MutableIntList hashTable;
    private IUniversalHashFunction universalHashFunction;

    public HashTableBucket(ISimpleBucket simpleBucket) throws TooBigNumberException {
        initHashTable(simpleBucket.getVals());
    }

    @Override
    public boolean putKey(int key, int val) throws TooBigNumberException {
        return put(key, val);
    }

    @Override
    public boolean put(int key, int val) throws TooBigNumberException {
        if (this.hashTable == null || this.hashTable.size() <= 1 || this.universalHashFunction == null) return false;

        int index = Math.toIntExact(this.universalHashFunction.hash(key));
        this.hashTable.set(index, val);
        return true;
    }

    @Override
    public int get(int key) throws TooBigNumberException {
        int index = Math.toIntExact(this.universalHashFunction.hash(key));
        return this.hashTable.get(index);
    }

    @Override
    public int getSize() {
        return this.hashTable.size();
    }

    @Override
    public boolean delete(int key) throws TooBigNumberException {
        if (this.hashTable == null || this.hashTable.size() <= 1 || this.universalHashFunction == null) return false;

        int index = Math.toIntExact(this.universalHashFunction.hash(key));
        this.hashTable.set(index, Integer.MIN_VALUE);
        return true;
    }

    @Override
    public void resetValues() {
        for (int i = 0; i < this.hashTable.size(); i++){
            this.hashTable.set(i, Integer.MIN_VALUE);
        }
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public String toString() {
        return this.hashTable.toString();
    }

    protected int genHashTableSize(int keysCount) {
        double antiCollisionFactor = (12d + (RandomSiaod.nextInt() % 9d)) / 10d;
        return (int) (antiCollisionFactor * Math.pow(keysCount, 2));
    }

    public void initHashTable(MutableIntList keysArr) throws TooBigNumberException {
        if (keysArr == null || keysArr.size() <= 1) return;

        int hashTableSize = genHashTableSize(keysArr.size());
        this.universalHashFunction = chooseHashFunction(hashTableSize, keysArr);
        this.hashTable = IntLists.mutable.empty();
        for (int i = 0; i < hashTableSize; i++){
            this.hashTable.add(Integer.MIN_VALUE);
        }
    }

    protected IUniversalHashFunction chooseHashFunction(int hashTableSize, MutableIntList keysArr) throws TooBigNumberException {
        final int MAX_ATTEMPTS = 100;
        int attempts = 0;
        IUniversalHashFunction function = null;
        while (doesFunctionMakeCollisions(keysArr, function)){
            if (attempts > MAX_ATTEMPTS){
                throw new RuntimeException("Cannot choose hash function.");
            }
            function = shuffleFunction(keysArr, hashTableSize);
            attempts++;
        }
        return function;
    }

    protected UniversalLinearTableHashFunction shuffleFunction(MutableIntList keysArr, int hashTableSize) throws TooBigNumberException {
        return new UniversalLinearTableHashFunction(keysArr, hashTableSize);
    }

    protected boolean doesFunctionMakeCollisions(MutableIntList keysArr, IUniversalHashFunction function) throws TooBigNumberException {
        if (keysArr == null || function == null) return true;
        MutableIntList hashes = IntLists.mutable.empty();
        for (int i = 0; i < keysArr.size(); i++) {
            int hash = Math.toIntExact(function.hash(keysArr.get(i)));
            // collision
            if (hashes.contains(hash)) return true;

            hashes.add(hash);
        }
        return false;
    }

    protected IUniversalHashFunction getHashFunction(){
        return this.universalHashFunction;
    }

    public MutableIntList getHashTable(){
        return this.hashTable;
    }

}
