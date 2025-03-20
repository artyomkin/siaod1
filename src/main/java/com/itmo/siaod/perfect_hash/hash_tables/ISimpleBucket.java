package com.itmo.siaod.perfect_hash.hash_tables;

import org.eclipse.collections.api.list.primitive.MutableIntList;

import java.util.ArrayList;

public interface ISimpleBucket extends IBucket {
    MutableIntList getVals();
}
