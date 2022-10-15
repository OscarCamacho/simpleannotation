package com.camacho.simpleannotation.model.descriptors;

import java.util.ArrayList;
import java.util.List;

public final class ClassDescriptor {

    private static final String TO_STRING_FORMAT =
            "class %s%s: constructors: %s, attributes: %s, methods: %s";

    private String className;
    private String packageName;
    private final List<ConstructorDescriptor> constructors;
    private final List<AttributeDescriptor> attributes;
    private final List<MethodDescriptor> methods;

    public ClassDescriptor() {
        this.constructors = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public ClassDescriptor setClassName(String className) {
        this.className = className;
        return this;
    }

    public ClassDescriptor setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public ClassDescriptor addConstructor(ConstructorDescriptor constructor) {
        this.constructors.add(constructor);
        return this;
    }

    public ClassDescriptor addAttribute(AttributeDescriptor attribute) {
        this.attributes.add(attribute);
        return this;
    }

    public ClassDescriptor addMethod(MethodDescriptor method) {
        this.methods.add(method);
        return this;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<ConstructorDescriptor> getConstructors() {
        return constructors;
    }

    public List<AttributeDescriptor> getAttributes() {
        return attributes;
    }

    public List<MethodDescriptor> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT,
                this.packageName,
                this.className,
                this.constructors,
                this.attributes,
                this.methods);
    }
}
