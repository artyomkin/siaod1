package com.itmo.siaod.perfect_hash.utils;

import java.util.Random;

public class RandomSiaod {

    private static Random rnd = new Random();

    public static int nextInt(){
        return rnd.nextInt() & Integer.MAX_VALUE;
    }
}
