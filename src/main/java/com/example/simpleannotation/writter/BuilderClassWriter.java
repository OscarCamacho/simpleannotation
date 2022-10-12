package com.example.simpleannotation.writter;

import com.example.simpleannotation.exceptions.ClassGenerationException;
import com.example.simpleannotation.model.BuilderAnnotatedClass;
import com.example.simpleannotation.model.BuilderGeneratedClass;

import javax.annotation.processing.Filer;

public final class BuilderClassWriter extends JavaClassWriter<BuilderGeneratedClass> {
    public BuilderClassWriter(Filer filer) {
        super(filer);
    }

    public void generateBuilderClass (BuilderAnnotatedClass builderAnnotatedClass)
            throws ClassGenerationException {
        this.writeFile(new BuilderGeneratedClass(builderAnnotatedClass));
    }

}
