package com.camacho.simpleannotation.writter;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.camacho.simpleannotation.model.BuilderGeneratedClass;
import com.camacho.simpleannotation.model.BuilderAnnotatedClass;

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
