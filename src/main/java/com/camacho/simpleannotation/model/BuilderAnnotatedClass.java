package com.camacho.simpleannotation.model;

import com.camacho.simpleannotation.model.descriptors.AttributeDescriptor;
import com.camacho.simpleannotation.model.descriptors.ConstructorDescriptor;
import com.camacho.simpleannotation.model.descriptors.MethodDescriptor;

import javax.lang.model.element.PackageElement;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class BuilderAnnotatedClass {
    private String classToBuild;
    private String classToBuildPackageName;
    private ConstructorDescriptor noArgsConstructor;
    private boolean useFluentBuilder;
    private boolean useSingletonBuilder;
    private PackageElement packageElement;

    private Map<AttributeDescriptor, Optional<MethodDescriptor>> attributeSetterMapping;
    private Set<ConstructorDescriptor> constructors;

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
        return Optional.ofNullable(this.noArgsConstructor);
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

    public Set<ConstructorDescriptor> getConstructors() {
        return constructors;
    }

    public BuilderAnnotatedClass setConstructors(Set<ConstructorDescriptor> constructors) {
        this.constructors = constructors;
        this.constructors.stream().filter(constructor ->
                constructor.getArguments().isEmpty())
                .findFirst()
                .ifPresent(noArgsConstructorFound ->
                        this.noArgsConstructor = noArgsConstructorFound);
        return this;
    }

    public BuilderAnnotatedClass setAttributeSetterMapping(
            Map<AttributeDescriptor, Optional<MethodDescriptor>> attributeSetterMapping) {
        this.attributeSetterMapping = attributeSetterMapping;
        return this;
    }

    public PackageElement getPackageElement() {
        return packageElement;
    }

    public BuilderAnnotatedClass setPackageElement(PackageElement packageElement) {
        this.packageElement = packageElement;
        return this;
    }
}
