package com.itmo.siaod.perfect_hash.exceptions;

public class CollisionException extends Exception {
    public CollisionException(){
        super();
    }

    public CollisionException(String s) {
        super(s);
    }
}
