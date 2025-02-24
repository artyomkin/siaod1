package com.itmo.siaod;

import com.itmo.siaod.extendible_hash.IHashTableSiaod;
import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;
import com.itmo.siaod.perfect_hash.hash_tables.HashTableSiaod;

import java.util.List;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws TooBigNumberException {
        //IHashTableSiaod ht = new HashTableSiaod();
        //ht.put(1, 13);
        //ht.put(3, 113);
        //ht.put(7, 23);
        //ht.put(15, 33);
        //ht.put(103, 133);
        //ht.put(13, 323);
        //ht.put(128, 343);
        //ht.put(127, 3123);
        //ht.put(129, 11);
        //ht.put(125, 432);
        //ht.put(10025, 543);
        //System.out.println(ht.get(1));
        //System.out.println(ht.get(3));
        //System.out.println(ht.get(7));
        //System.out.println(ht.get(15));
        //System.out.println(ht.get(103));
        //System.out.println(ht.get(13));
        //System.out.println(ht.get(128));
        //System.out.println(ht.get(127));
        //System.out.println(ht.get(129));
        //System.out.println(ht.get(125));
        //System.out.println(ht.get(10025));

        HashTableSiaod table = new HashTableSiaod(List.of(1,2,3,4));
        table.put(1,2);
        System.out.println(table.toString());
    }
}