package com.example.simpleannotation.model;

import com.example.simpleannotation.model.descriptors.AttributeDescriptor;
import com.example.simpleannotation.model.descriptors.ConstructorDescriptor;
import com.example.simpleannotation.model.descriptors.MethodDescriptor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class BuilderAnnotatedClass {
    private String classToBuild;
    private String classToBuildPackageName;
    private Optional<ConstructorDescriptor> noArgsConstructor;
    private boolean useFluentBuilder;
    private boolean useSingletonBuilder;
    private Map<AttributeDescriptor, Optional<MethodDescriptor>> attributeSetterMapping;
    private List<ConstructorDescriptor> constructors;

    public String getClassToBuild() {
        return classToBuild;
    }

    public BuilderAnnotatedClass setClassToBuild(String classToBuild) {
        this.classToBuild = classToBuild;
        return this;
    }

    public String getClassToBuildPackageName() {
        return classToBuildPackageName;
    }

    public BuilderAnnotatedClass setClassToBuildPackageName(String classToBuildPackageName) {
        this.classToBuildPackageName = classToBuildPackageName;
        return this;
    }

    public Optional<ConstructorDescriptor> getNoArgsConstructor() {
        return noArgsConstructor;
    }

    public BuilderAnnotatedClass setNoArgsConstructor(ConstructorDescriptor noArgsConstructor) {
        this.noArgsConstructor = Optional.ofNullable(noArgsConstructor);
        return this;
    }

    public boolean useFluentBuilder() {
        return useFluentBuilder;
    }

    public BuilderAnnotatedClass setUseFluentBuilder(boolean useFluentBuilder) {
        this.useFluentBuilder = useFluentBuilder;
        return this;
    }

    public boolean useSingletonBuilder() {
        return useSingletonBuilder;
    }

    public BuilderAnnotatedClass setUseSingletonBuilder(boolean useSingletonBuilder) {
        this.useSingletonBuilder = useSingletonBuilder;
        return this;
    }

    public Map<AttributeDescriptor, Optional<MethodDescriptor>> getAttributeSetterMapping() {
        return attributeSetterMapping;
    }

    public List<ConstructorDescriptor> getConstructors() {
        return constructors;
    }

    public BuilderAnnotatedClass setConstructors(List<ConstructorDescriptor> constructors) {
        this.constructors = constructors;
        this.noArgsConstructor = this.constructors.stream().filter(constructor ->
                constructor.getArguments().isEmpty()).findFirst();
        return this;
    }

    public BuilderAnnotatedClass setAttributeSetterMapping(
            Map<AttributeDescriptor, Optional<MethodDescriptor>> attributeSetterMapping) {
        this.attributeSetterMapping = attributeSetterMapping;
        return this;
    }
}
