package com.example.simpleannotation.exceptions;

public class ClassGenerationException extends RuntimeException{
    public ClassGenerationException () {
        super();
    }
    public ClassGenerationException (Throwable t) {
        super(t);
    }

}
