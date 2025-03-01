package com.itmo.siaod.min_hash.hash_tables.bands;

import java.util.List;

public interface IBandToHashKeyMapper {

    static List<List<Integer>> mapBandsToHashKeys(List<IBand> bands) {
        return bands.stream().map(IBandToHashKeyMapper::mapBandToHashKey).toList();
    };

    private static List<Integer> mapBandToHashKey(IBand band) {
        return band.getBand().stream().map(IBandToHashKeyMapper::concatList).toList();
    }

    private static Integer concatList(List<Integer> l) {
        if (l == null || l.isEmpty()) return null;

        int res = l.getFirst();
        for (int i = 1; i < l.size(); i++){
            res = res * 10 + l.get(i);
        }
        return res;
    }
}
