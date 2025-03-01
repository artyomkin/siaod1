package com.itmo.siaod.min_hash.hash_tables.bands;

import com.itmo.siaod.exceptions.IncorrectBandException;
import com.itmo.siaod.min_hash.signatures.ISignature;

import java.util.ArrayList;
import java.util.List;

public class Band implements IBand {

    private final List<List<Integer>> band;

    public Band(List<ISignature> signatures, int offset, int bandSize) throws IncorrectBandException {
        if (signatures == null) throw new IncorrectBandException("Signatures are null.");
        if (offset + bandSize > signatures.size()) throw new IncorrectBandException("Offset + band size is out of bound.");

        this.band = signatures.stream().map(signature -> signature.getMinHashes().subList(offset, offset + bandSize)).toList();
    }

    @Override
    public List<List<Integer>> getBand() {
        return this.band;
    }
}
