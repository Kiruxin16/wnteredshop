package ru.geekbrains.wnteredshop.api;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){super(message);}
}
