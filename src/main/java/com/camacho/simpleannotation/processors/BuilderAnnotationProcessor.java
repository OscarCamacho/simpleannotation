package com.camacho.simpleannotation.processors;

import com.camacho.simpleannotation.model.descriptors.AttributeDescriptor;
import com.camacho.simpleannotation.model.descriptors.ClassDescriptor;
import com.camacho.simpleannotation.model.descriptors.MethodDescriptor;
import com.camacho.simpleannotation.model.descriptors.TypeMapper;
import com.camacho.simpleannotation.writter.BuilderClassWriter;
import com.camacho.simpleannotation.annotations.Builder;
import com.camacho.simpleannotation.exceptions.BadAnnotationUsageException;
import com.camacho.simpleannotation.model.BuilderAnnotatedClass;

import com.google.auto.service.AutoService;
import javax.annotation.processing.Processor;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import java.util.*;

import static com.camacho.simpleannotation.utils.ElementUtils.isClass;

@SupportedAnnotationTypes("com.camacho.simpleannotation.annotations.Builder")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public final class BuilderAnnotationProcessor
        extends AbstractAnnotationProcessor<Builder, BuilderAnnotatedClass> {
    private static final String ANNOTATION_NAME = "@Builder";

    public BuilderAnnotationProcessor() {
        super(Builder.class);
    }

    @Override
    protected void validateElement(Element annotatedElement) throws BadAnnotationUsageException {
        if (!isClass(annotatedElement)) {
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
    protected void validateAnnotation(Builder annotation) throws BadAnnotationUsageException {
        Optional.ofNullable(annotation)
                .map(Builder::fields)
                .map(Arrays::asList)
                .filter(List::isEmpty);
        Optional.ofNullable(annotation)
                .map(Builder::fieldsToIgnore)
                .map(Arrays::asList)
                .filter(List::isEmpty);
    }

    @Override
    protected BuilderAnnotatedClass transformElementToModel(Element annotatedElement, Builder annotation) {
        ClassDescriptor classDescriptor = annotatedElement.accept(new TypeMapper(), null).getClassDescriptor();
        Map<AttributeDescriptor, Optional<MethodDescriptor>> attributeSetterMapping = new HashMap<>();
        for (AttributeDescriptor attribute : classDescriptor.getAttributes()) {
            Optional<MethodDescriptor> setter = classDescriptor.getMethods().stream()
                    .filter(method ->
                    method.getName().toLowerCase().equals(String.format("set%s",attribute.getName().toLowerCase())))
                    .findFirst();
            attributeSetterMapping.put(attribute, setter);
        }
        return new BuilderAnnotatedClass()
                .setClassToBuild(classDescriptor.getClassName())
                .setClassToBuildPackageName(classDescriptor.getPackageName())
                .setPackageElement((PackageElement) annotatedElement.getEnclosingElement())
                .setConstructors(classDescriptor.getConstructors())
                .setUseFluentBuilder(annotation.useFluentBuilder())
                .setUseSingletonBuilder(annotation.useSingletonBuilder())
                .setAttributeSetterMapping(attributeSetterMapping);
    }

    @Override
    protected void finalizeElementProcessing(BuilderAnnotatedClass model) {
        BuilderClassWriter writer = new BuilderClassWriter(this.processingEnv.getFiler());
        writer.generateBuilderClass(model);
    }
}
