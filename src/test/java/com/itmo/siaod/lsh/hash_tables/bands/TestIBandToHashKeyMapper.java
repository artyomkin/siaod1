package com.itmo.siaod.lsh.hash_tables.bands;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestIBandToHashKeyMapper {
    @Test
    void testMapBandsToHashKeys() {
        IBand band1 = new IBand() {
            @Override
            public List<List<Boolean>> getBand() {
                return List.of(
                        List.of(true, false, true),
                        List.of(false, true, false)
                );
            }
        };
        IBand band2 = new IBand() {
            @Override
            public List<List<Boolean>> getBand() {
                return List.of(
                        List.of(true, true, false),
                        List.of(false, false, true)
                );
            }
        };
        List<IBand> bands = List.of(band1, band2);
        //List<List<Integer>> hashKeys = IBandToHashKeyMapper.mapBandsToHashKeys(bands);
        //assertNotNull(hashKeys);
        //assertEquals(2, hashKeys.size());
        //List<Integer> band1HashKeys = hashKeys.get(0);
        //assertEquals(2, band1HashKeys.size());
        //assertEquals(5, band1HashKeys.get(0));
        //assertEquals(2, band1HashKeys.get(1));
        //List<Integer> band2HashKeys = hashKeys.get(1);
        //assertEquals(2, band2HashKeys.size());
        //assertEquals(3, band2HashKeys.get(0));
        //assertEquals(4, band2HashKeys.get(1));
    }

    @Test
    void testMapBandToHashKey() {
        IBand band = new IBand() {
            @Override
            public List<List<Boolean>> getBand() {
                return List.of(
                        List.of(true, false, true),
                        List.of(false, true, false)
                );
            }
        };
        //List<Integer> hashKeys = IBandToHashKeyMapper.mapBandToHashKey(band);
        //assertNotNull(hashKeys);
        //assertEquals(2, hashKeys.size());
        //assertEquals(5, hashKeys.get(0));
        //assertEquals(2, hashKeys.get(1));
    }

    @Test
    void testHashList() {
        assertEquals(5, IBandToHashKeyMapper.hashList(List.of(true, false, true)));
        assertEquals(2, IBandToHashKeyMapper.hashList(List.of(false, true, false)));
        assertEquals(3, IBandToHashKeyMapper.hashList(List.of(true, true, false)));
        assertEquals(4, IBandToHashKeyMapper.hashList(List.of(false, false, true)));
    }

    @Test
    void testHashListWithEmptyList() {
        assertNull(IBandToHashKeyMapper.hashList(List.of()));
    }

    @Test
    void testHashListWithNullList() {
        assertNull(IBandToHashKeyMapper.hashList(null));
    }
}
