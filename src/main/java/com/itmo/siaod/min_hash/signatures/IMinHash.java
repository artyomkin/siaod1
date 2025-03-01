package com.itmo.siaod.min_hash.signatures;

import com.itmo.siaod.perfect_hash.exceptions.TooBigNumberException;

import java.util.LinkedHashSet;

public interface IMinHash {
    ISignature getSignatureOf(LinkedHashSet<Integer> x) throws TooBigNumberException;
}
