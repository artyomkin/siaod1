package com.itmo.siaod.lsh.hash_tables.bands;

import com.itmo.siaod.lsh.exceptions.IncorrectBandException;

import java.util.ArrayList;
import java.util.List;

public interface IBander {

    int BAND_SIZE = 30;
    int BAND_NUMBER = 100;

    static List<IBand> splitIntoBands(List<List<Boolean>> signatures) {
        if (signatures == null){
            return null;
        }
        if (signatures.isEmpty()){
            return new ArrayList<>();
        }
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
