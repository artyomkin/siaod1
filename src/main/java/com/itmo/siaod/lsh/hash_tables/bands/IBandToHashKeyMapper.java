package com.itmo.siaod.lsh.hash_tables.bands;

import org.eclipse.collections.api.list.primitive.IntList;
import org.eclipse.collections.api.list.primitive.MutableIntList;
import org.eclipse.collections.impl.factory.primitive.IntLists;

import java.util.ArrayList;
import java.util.List;

public interface IBandToHashKeyMapper {

    static List<MutableIntList> mapBandsToHashKeys(List<IBand> bands) {
        List<MutableIntList> res = new ArrayList<>();
        for (int i = 0; i < bands.size(); i++){
            res.add(IBandToHashKeyMapper.mapBandToHashKey(bands.get(i)));
        }
        return res;
    };

    static MutableIntList mapBandToHashKey(IBand band) {
        MutableIntList res = IntLists.mutable.empty();
        for (int i = 0; i < band.getBand().size(); i++){
            res.add(IBandToHashKeyMapper.hashList(band.getBand().get(i)));
        }
        return res;
    }

    static int hashList(List<Boolean> l) {
        int result = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i)) {
                result |= (1 << i);
            }
        }
        return result;
    }
}
