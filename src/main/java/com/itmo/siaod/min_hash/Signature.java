package com.itmo.siaod.min_hash;

import java.util.HashSet;
import java.util.Set;

public class Signature implements ISignature {
    private Set<Integer> minHashes;

    public Signature(Set<Integer> minHashes){
        this.minHashes = minHashes;
    }

    public Signature(){
        this.minHashes = new HashSet<>();
    }

    @Override
    public Set<Integer> getMinHashes() {
        return this.minHashes;
    }
}
