package ru.geekbrains.spring.wnteredshop.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){super(message);}
}
