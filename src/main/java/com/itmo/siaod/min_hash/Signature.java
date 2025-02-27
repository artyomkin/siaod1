package com.itmo.siaod.min_hash;

import java.util.ArrayList;

public class Signature implements ISignature {
    private ArrayList<Integer> minHashes;

    public Signature(ArrayList<Integer> minHashes){
        this.minHashes = minHashes;
    }

    public Signature(){
        this.minHashes = new ArrayList<>();
    }

    @Override
    public ArrayList<Integer> getMinHashes() {
        return this.minHashes;
    }
}
