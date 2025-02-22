package com.itmo.siaod.perfect_hash.exceptions;

public class TooBigNumberException extends Exception{
    public TooBigNumberException(){
        super();
    }
    public TooBigNumberException(String msg){
        super(msg);
    }
}
