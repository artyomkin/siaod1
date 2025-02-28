package com.itmo.siaod.min_hash;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public interface IMinHash {
    ArrayList<Integer> minHash(LinkedHashSet<Integer> x) throws TooBigNumberException;
}
