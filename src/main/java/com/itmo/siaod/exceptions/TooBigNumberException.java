package com.itmo.siaod.exceptions;

public class TooBigNumberException extends Exception{
    public TooBigNumberException(){
        super();
    }
    public TooBigNumberException(String msg){
        super(msg);
    }
}
