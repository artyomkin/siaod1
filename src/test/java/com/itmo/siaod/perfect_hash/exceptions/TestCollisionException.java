package com.itmo.siaod.perfect_hash.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCollisionException {
    @Test
    public void testException(){
        assertThrows(CollisionException.class, ()-> { throw new CollisionException(); });
    }

}
