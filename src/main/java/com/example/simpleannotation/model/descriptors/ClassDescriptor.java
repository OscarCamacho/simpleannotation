package com.example.simpleannotation.model.descriptors;

import java.util.ArrayList;
import java.util.List;

public final class ClassDescriptor {

    private static final String TO_STRING_FORMAT =
            "class %s: constructors: %s, attributes: %s, methods: %s";

    private String className;
    private List<String> constructors;
    private List<String> attributes;
    private List<String> methods;

    public void setClassName(String className) {
        this.className = className;
    }

    public void addConstructor(String constructor) {
        if (this.constructors == null) {
            this.constructors = new ArrayList<>();
        }
        this.constructors.add(constructor);
    }

    public void addAttribute(String attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }
        this.attributes.add(attribute);
    }

    public void addMethod(String method) {
        if (this.methods == null) {
            this.methods = new ArrayList<>();
        }
        this.methods.add(method);
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT,
                this.className,
                this.constructors,
                this.attributes,
                this.methods);
    }
}
