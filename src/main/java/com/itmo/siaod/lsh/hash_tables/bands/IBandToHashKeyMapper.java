package com.itmo.siaod.lsh.hash_tables.bands;

import java.util.List;

public interface IBandToHashKeyMapper {

    static List<List<Integer>> mapBandsToHashKeys(List<IBand> bands) {
        return bands.stream().map(IBandToHashKeyMapper::mapBandToHashKey).toList();
    };

    static List<Integer> mapBandToHashKey(IBand band) {
        return band.getBand().stream().map(IBandToHashKeyMapper::hashList).toList();
    }

    static Integer hashList(List<Boolean> l) {
        if (l == null || l.isEmpty()) return null;

        int result = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i)) {
                result |= (1 << i);
            }
        }
        return result;
    }
}
