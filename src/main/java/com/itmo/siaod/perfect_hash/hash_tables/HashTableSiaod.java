package com.itmo.siaod.perfect_hash.hash_tables;

import com.itmo.siaod.perfect_hash.IHashTableSiaod;
import com.itmo.siaod.perfect_hash.exceptions.CollisionException;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_functions.UniversalLinearTableHashFunction;
import com.itmo.siaod.perfect_hash.hash_tables.buckets.HashTableBucket;
import com.itmo.siaod.perfect_hash.hash_tables.buckets.SimpleBucket;
import com.itmo.siaod.perfect_hash.utils.RandomSiaod;
import org.eclipse.collections.api.list.primitive.MutableIntList;

import java.util.ArrayList;
import java.util.List;

public class HashTableSiaod implements IHashTableSiaod {

    private IUniversalHashFunction hashFunction;
    private int hashTableSize;
    public ArrayList<IBucket> buckets;
    public MutableIntList possibleKeys;

    public HashTableSiaod(MutableIntList allPossibleKeys) throws TooBigNumberException {
        if (allPossibleKeys.isEmpty())
            throw new RuntimeException("No possible keys passed");

        MutableIntList allUniqPossibleKeys = allPossibleKeys.distinct();
        int allUniqPossibleKeysCount = allUniqPossibleKeys.size();
        this.possibleKeys = allUniqPossibleKeys;

        this.hashTableSize = (12 + RandomSiaod.nextInt() % 9) / 10 * allUniqPossibleKeysCount;
        this.hashFunction = new UniversalLinearTableHashFunction(allUniqPossibleKeys, this.hashTableSize);

        initializeFirstLayer(allUniqPossibleKeys);
        initializeSecondLayer();
        resetBuckets();
    }

    protected void initializeFirstLayer(MutableIntList allPossibleKeys) throws TooBigNumberException {
        this.buckets = new ArrayList<>(this.hashTableSize);
        for (int i = 0; i < this.hashTableSize; i++){
            this.buckets.add(null);
        }
        for (int i = 0; i < allPossibleKeys.size(); i++) {
            addKey(allPossibleKeys.get(i));
        }
    }

    protected void addKey(int key) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        if (this.buckets.get(index) == null) {
            IBucket simpleBucket = new SimpleBucket(key);
            this.buckets.set(index, simpleBucket);
        } else {
            this.buckets.get(index).putKey(key, key);
        }
    }

    protected void initializeSecondLayer() throws TooBigNumberException {
        for (int i = 0; i < this.buckets.size(); i++){
            if (this.buckets.get(i) != null && this.buckets.get(i).isSimple() && this.buckets.get(i).getSize() > 1) {
                this.buckets.set(i, simpleToHashTableBucket((ISimpleBucket) this.buckets.get(i)));
            }
        }
    }

    protected HashTableBucket simpleToHashTableBucket(ISimpleBucket simpleBucket) throws TooBigNumberException {
        return new HashTableBucket(simpleBucket);
    }

    protected void resetBuckets(){
        for (IBucket bucket : buckets) {
            if (bucket != null) {
                bucket.resetValues();
            }
        }
    }

    @Override
    public boolean put(int key, int val) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        return this.buckets.get(index).put(key, val);
    }

    @Override
    public int get(int key) throws CollisionException, TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        return this.buckets.get(index).get(key);
    }

    @Override
    public boolean delete(int key) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        return this.buckets.get(index).delete(key);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.buckets.size(); i++) {
            if (this.buckets.get(i) == null) {
                res.append(" ").append(i).append(": null").append("\n ");
            } else {
                res.append(" ").append(this.buckets.get(i).isSimple() ? "simple" : "hashTable").append(" ").append(i).append(": ").append(this.buckets.get(i).toString()).append("\n ");
            }
        }
        return res.toString();
    }
}
