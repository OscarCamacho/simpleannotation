package com.camacho.simpleannotation.exceptions;

/** Thrown during class generation in the outcome of an error. */
public final class ClassGenerationException extends RuntimeException{
    public ClassGenerationException (String message) {
        super(message);
    }
    public ClassGenerationException (Throwable t) {
        super(t);
    }

}
