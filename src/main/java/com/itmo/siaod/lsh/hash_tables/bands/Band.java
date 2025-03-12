package com.itmo.siaod.lsh.hash_tables.bands;

import com.itmo.siaod.lsh.exceptions.IncorrectBandException;

import java.util.List;

public class Band implements IBand {

    private final List<List<Boolean>> band;

    public Band(List<List<Boolean>> signatures, int offset, int bandSize) throws IncorrectBandException {
        if (signatures == null) throw new IncorrectBandException("Signatures are null.");
        if (offset + bandSize > signatures.getFirst().size()) throw new IncorrectBandException("Offset + band size is out of bound.");

        this.band = signatures.stream().map(signature -> signature.subList(offset, offset + bandSize)).toList();
    }

    @Override
    public List<List<Boolean>> getBand() {
        return this.band;
    }
}
