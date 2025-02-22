package com.itmo.siaod;

import com.itmo.siaod.exceptions.CollisionException;
import com.itmo.siaod.hash_tables.HashTableSiaod;
import com.itmo.siaod.hash_tables.IBucket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws Exception {
        while(true) {

            List<Integer> keys = List.of(1, 32, 24, 1233, 4);
            IHashTableSiaod hashTableSiaod = new HashTableSiaod(keys);
        }

    }
}