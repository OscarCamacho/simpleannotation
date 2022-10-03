package com.example.simpleannotation.processors;

import com.example.simpleannotation.annotations.Builder;
import com.example.simpleannotation.exceptions.BadAnnotationUsageException;
import com.example.simpleannotation.model.BuilderAnnotatedClass;
import com.example.simpleannotation.model.descriptors.TypeMapper;
import com.google.auto.service.AutoService;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;


@SupportedAnnotationTypes("com.example.simpleannotation.annotations.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public final class BuilderAnnotationProcessor
        extends AbstractAnnotationProcessor<Builder, BuilderAnnotatedClass> {
    private static final String ANNOTATION_NAME = "@Builder";

    public BuilderAnnotationProcessor() {
        super(Builder.class);
    }

    @Override
    void validateElement(Element annotatedElement) throws BadAnnotationUsageException {
        if (!annotatedElement.getKind().isClass()) {
            throw new BadAnnotationUsageException(annotatedElement.getSimpleName().toString(),
                    ANNOTATION_NAME,
                    "Only classes may use this annotation");
        } else {
            if (annotatedElement.getModifiers().contains(Modifier.ABSTRACT)) {
                throw new BadAnnotationUsageException(annotatedElement.getSimpleName().toString(),
                        ANNOTATION_NAME,
                        "Only non-abstract classes are supported at this time");
            }
            throw new BadAnnotationUsageException(annotatedElement.getSimpleName().toString(),
                    ANNOTATION_NAME,
                    annotatedElement.accept(new TypeMapper(), null).getClassDescriptor().toString());
        }
    }

    @Override
    BuilderAnnotatedClass transformElementToModel(Element annotatedElement, Builder annotation) {
        return null;
    }

    @Override
    void finalizeElementProcessing(BuilderAnnotatedClass model) {

    }
}
