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
        //while(true) {

            List<Integer> keys = List.of(1, 32, 24, 1233, 4);
            IHashTableSiaod hashTableSiaod = new HashTableSiaod(keys);
            hashTableSiaod.put(1, 40);
            hashTableSiaod.put(24, 400);
            hashTableSiaod.put(4, 100);

            System.out.println(hashTableSiaod.get(1));
            System.out.println(hashTableSiaod.get(24));
            System.out.println(hashTableSiaod.get(4));

            hashTableSiaod.delete(1);
            hashTableSiaod.delete(24);
            hashTableSiaod.delete(4);
        //}

    }
}