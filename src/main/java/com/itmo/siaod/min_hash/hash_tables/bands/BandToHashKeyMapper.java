package com.itmo.siaod.min_hash.hash_tables.bands;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class BandToHashKeyMapper implements IBandToHashKeyMapper {

    MessageDigest md;

    public BandToHashKeyMapper(){
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<List<Integer>> mapBandsToHashKeys(List<IBand> bands) {
        return bands.stream().map(this::mapBandToHashKey).toList();
    };

    private List<Integer> mapBandToHashKey(IBand band) {
        return band.getBand().stream().map(this::hashList).toList();
    }

    private Integer hashList(List<Integer> l){
        //TODO
    };
}
