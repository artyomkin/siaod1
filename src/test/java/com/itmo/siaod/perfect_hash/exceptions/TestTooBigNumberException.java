package com.itmo.siaod.perfect_hash.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTooBigNumberException {

    @Test
    public void testException(){
        assertThrows(TooBigNumberException.class, () -> { throw new TooBigNumberException(); } );
    }

}
