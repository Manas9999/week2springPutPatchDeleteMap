package com.week2.spirngmvc.SpringMVC.services;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
