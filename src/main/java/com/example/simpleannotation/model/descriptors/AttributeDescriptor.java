package com.example.simpleannotation.model.descriptors;

public final class AttributeDescriptor {

    private final String accessModifier;
    private final String name;
    private final String type;

    public AttributeDescriptor(String name, String type, String accessModifier) {
        this.name = name;
        this.type = type;
        this.accessModifier = accessModifier;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAccessModifier () {
        return this.accessModifier;
    }

    @Override
    public String toString() {
        return String.join(" ", this.accessModifier, this.type, this.name);
    }
}
