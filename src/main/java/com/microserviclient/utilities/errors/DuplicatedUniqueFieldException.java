package com.microserviclient.utilities.errors;

public class DuplicatedUniqueFieldException extends IllegalArgumentException{
    public DuplicatedUniqueFieldException(String s){
        super(s);
    }
}
