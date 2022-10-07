package com.example.simpleannotation.model.descriptors;

import java.util.ArrayList;
import java.util.List;

public final class ClassDescriptor {

    private static final String TO_STRING_FORMAT =
            "class %s: constructors: %s, attributes: %s, methods: %s, debug: %s";

    private String className;
    private final List<ConstructorDescriptor> constructors;
    private final List<AttributeDescriptor> attributes;
    private final List<MethodDescriptor> methods;
    // TODO: Delete this
    private final List<String> debug;

    public ClassDescriptor() {
        this.constructors = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
        this.debug = new ArrayList<>();
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void addConstructor(ConstructorDescriptor constructor) {
        this.constructors.add(constructor);
    }

    public void addAttribute(AttributeDescriptor attribute) {
        this.attributes.add(attribute);
    }

    public void addMethod(MethodDescriptor method) {
        this.methods.add(method);
    }

    public void addDebug(String s) {
        this.debug.add(s);
    }

    public String getClassName() {
        return className;
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
                this.className,
                this.constructors,
                this.attributes,
                this.methods,
                this.debug);
    }
}
