package com.itmo.siaod.min_hash.signatures;

import java.util.ArrayList;
import java.util.List;

public class Signature implements ISignature {
    private List<Integer> minHashes;

    public Signature(List<Integer> minHashes){
        this.minHashes = minHashes;
    }

    public Signature(){
        this.minHashes = new ArrayList<>();
    }

    @Override
    public List<Integer> getMinHashes() {
        return this.minHashes;
    }
}
