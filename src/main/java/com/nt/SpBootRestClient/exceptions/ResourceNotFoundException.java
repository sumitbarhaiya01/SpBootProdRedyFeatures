package com.nt.SpBootRestClient.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message ){
        super(message);
    }
}
