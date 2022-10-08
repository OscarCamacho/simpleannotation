package com.example.simpleannotation.processors;

import com.example.simpleannotation.annotations.Builder;
import com.example.simpleannotation.exceptions.BadAnnotationUsageException;
import com.example.simpleannotation.model.BuilderAnnotatedClass;
import com.example.simpleannotation.model.descriptors.*;
import com.example.simpleannotation.writter.JavaClassWriter;
import com.google.auto.service.AutoService;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


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
        }
    }

    @Override
    BuilderAnnotatedClass transformElementToModel(Element annotatedElement, Builder annotation) {
        ClassDescriptor classDescriptor = annotatedElement.accept(new TypeMapper(), null).getClassDescriptor();
        Map<AttributeDescriptor, Optional<MethodDescriptor>> attributeSetterMapping = new HashMap<>();
        for (AttributeDescriptor attribute : classDescriptor.getAttributes()) {
            Optional<MethodDescriptor> setter = classDescriptor.getMethods().stream()
                    .filter(method ->
                    method.getName().toLowerCase().equals(String.format("get%s",attribute.getName()))
            && method.getReturnType().equals(attribute.getType()))
                    .findFirst();
            attributeSetterMapping.put(attribute, setter);
        }
        Optional<ConstructorDescriptor> noArgsConstructor = classDescriptor.getConstructors().stream()
                .filter(constructor -> constructor.getArguments().isEmpty()).findAny();
        return new BuilderAnnotatedClass()
                .setClassToBuild(classDescriptor.getClassName())
                .setConstructors(classDescriptor.getConstructors())
                .setNoArgsConstructor(noArgsConstructor.orElse(null))
                .setUseFluentBuilder(annotation.useFluentBuilder())
                .setAttributeSetterMapping(attributeSetterMapping);
    }

    @Override
    void finalizeElementProcessing(BuilderAnnotatedClass model) {
        JavaClassWriter writer = new JavaClassWriter(this.processingEnv.getFiler());
    }
}
