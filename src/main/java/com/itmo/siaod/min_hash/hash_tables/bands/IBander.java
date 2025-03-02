package com.itmo.siaod.min_hash.hash_tables.bands;

import com.itmo.siaod.min_hash.exceptions.IncorrectBandException;
import com.itmo.siaod.min_hash.signatures.ISignature;

import java.util.ArrayList;
import java.util.List;

public interface IBander {

    Integer BAND_SIZE = 5;
    Integer BAND_NUMBER = 20;

    static List<IBand> splitIntoBands(List<ISignature> signatures) {
        List<IBand> bands = new ArrayList<>();
        for (int i = 0; i < BAND_NUMBER; i++) {
            try {
                bands.add(new Band(signatures, BAND_SIZE * i, BAND_SIZE));
            } catch (IncorrectBandException e) {
                return null;
            }
        }
        return bands;
    }

}
