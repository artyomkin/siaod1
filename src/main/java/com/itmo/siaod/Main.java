package com.itmo.siaod;

import com.itmo.siaod.extendible_hash.hash_tables.HashTableSiaod;
import com.itmo.siaod.extendible_hash.IHashTableSiaod;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        IHashTableSiaod ht = new HashTableSiaod();
        ht.put(1, 13);
        ht.put(3, 113);
        ht.put(7, 23);
        ht.put(15, 33);
        System.out.println(ht.get(1));
        System.out.println(ht.get(3));
        System.out.println(ht.get(7));
        System.out.println(ht.get(15));
    }
}