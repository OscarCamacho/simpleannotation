package com.example.simpleannotation.writter;

import javax.annotation.processing.Filer;

/**
* This class contains common logic to write a Java Class File usimg {@link Filer} API.
* @param <T> - Represents the class type to be writen.
*/
public abstract class JavaClassWriter<T> {

    protected final Filer filer;

    protected JavaClassWriter(Filer filer) {
        this.filer = filer;
    }

    public void writeFile(T t) {
        
    }

}
