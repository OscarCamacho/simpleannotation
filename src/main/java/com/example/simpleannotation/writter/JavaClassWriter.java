package com.example.simpleannotation.writter;

import com.example.simpleannotation.exceptions.ClassGenerationException;
import com.example.simpleannotation.model.GeneratedClass;

import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class contains common logic to write a Java Class File usimg {@link Filer} API.
 * @param <T> - Class that extends GeneratedClass.
 */
public abstract class JavaClassWriter<T extends GeneratedClass> {

    protected final Filer filer;

    protected JavaClassWriter(Filer filer) {
        this.filer = filer;
    }

    protected void writeFile(T t) throws ClassGenerationException {
        try {
            JavaFileObject generatedFile = filer.createSourceFile(t.getClassName()
                    .orElseThrow(ClassGenerationException::new));
            try (PrintWriter writer = new PrintWriter(generatedFile.openWriter())) {
                writer.print(t);
            }
        } catch (IOException e) {
            throw new ClassGenerationException(e);
        }
    }

}
