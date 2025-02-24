package com.itmo.siaod.perfect_hash.exceptions;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTooBigNumberException {

    @Test
    public void testException(){
        assertThrows(TooBigNumberException.class, () -> { throw new TooBigNumberException(); } );
    }

}
