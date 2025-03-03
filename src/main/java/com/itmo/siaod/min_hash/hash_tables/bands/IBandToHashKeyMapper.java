package com.itmo.siaod.min_hash.hash_tables.bands;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    //private static Integer hashList(List<Integer> l) {
    //    if (l == null || l.isEmpty()) return null;

    //    try {
    //        byte[] byteArray = l.stream()
    //                .mapToInt(Integer::intValue)
    //                .collect(
    //                        () -> new ByteArrayOutputStream(),
    //                        (baos, value) -> baos.write((byte) value),
    //                        (baos1, baos2) -> baos1.write(baos2.toByteArray(), 0, baos2.size())
    //                )
    //                .toByteArray();
    //        return Integer.MessageDigest.getInstance("MD5").digest(byteArray);
    //    } catch (NoSuchAlgorithmException e) {
    //        throw new RuntimeException(e);
    //    }
    //}
}
