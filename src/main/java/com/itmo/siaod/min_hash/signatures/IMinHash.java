package com.itmo.siaod.min_hash.signatures;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.LinkedHashSet;
import java.util.Set;

public interface IMinHash {
    ISignature getSignatureOf(Set<Integer> x) throws TooBigNumberException;
}
