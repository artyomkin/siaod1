package com.itmo.siaod.perfect_hash.exceptions;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCollisionException {
    @Test
    public void testException(){
        assertThrows(CollisionException.class, ()-> { throw new CollisionException(); });
    }

}
