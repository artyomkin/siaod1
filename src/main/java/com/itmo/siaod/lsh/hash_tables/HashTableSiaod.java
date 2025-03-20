package com.itmo.siaod.lsh.hash_tables;

import com.itmo.siaod.lsh.hash_functions.UniversalLinearLimitedHashFunction;
import com.itmo.siaod.lsh.hash_tables.buckets.SetBucket;
import com.itmo.siaod.lsh.hash_tables.buckets.IBucket;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.IUniversalHashFunction;
import org.apache.maven.surefire.shared.lang3.mutable.Mutable;
import org.apache.maven.surefire.shared.lang3.mutable.MutableInt;
import org.eclipse.collections.api.list.primitive.MutableIntList;

import java.util.ArrayList;
import java.util.List;

public class HashTableSiaod implements IHashTableSiaod {

    private final List<IBucket> buckets;
    protected final IUniversalHashFunction hashFunction;

    public HashTableSiaod(int bucketsNumber) {
        this.hashFunction = new UniversalLinearLimitedHashFunction(bucketsNumber);
        this.buckets = new ArrayList<>(bucketsNumber);
        for (int i = 0; i < bucketsNumber; i++){
            this.buckets.add(null);
        }
    }

    @Override
    public boolean put(int key, int value) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        ensureBucketExists(index);
        return this.buckets.get(index).put(key, value);
    }

    @Override
    public boolean delete(int key) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        ensureBucketExists(index);
        return this.buckets.get(index).delete(key);
    }

    @Override
    public int get(int key) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        ensureBucketExists(index);
        return this.buckets.get(index).get(key);
    }

    public MutableIntList getWholeBucket(int key) throws TooBigNumberException {
        int index = Math.toIntExact(this.hashFunction.hash(key));
        ensureBucketExists(index);
        return ((SetBucket)this.buckets.get(index)).getVals();
    }

    //@Override
    //public List<MutableIntList> getAllEntries() {
    //    List<MutableIntList> entries = new ArrayList<>();
    //    for (int i = 0; i < buckets.size(); i++){
    //        if (this.buckets.get(i) != null){
    //            entries.add(this.buckets.get(i).getAll());
    //        }
    //    }
    //    return entries;
    //}

    protected void ensureBucketExists(int index){
        if (this.buckets.get(index) == null){
            this.buckets.set(index, new SetBucket());
        }
    }
}
