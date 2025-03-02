package com.itmo.siaod.min_hash.hash_tables.bands;

import java.security.MessageDigest;
import java.util.List;

public interface IBandToHashKeyMapper {
    List<List<Integer>> mapBandsToHashKeys(List<IBand> bands);
}
