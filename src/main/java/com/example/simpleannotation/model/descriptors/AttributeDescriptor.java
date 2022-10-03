package com.example.simpleannotation.model.descriptors;

public final class AttributeDescriptor {

    private final String name;
    private final String type;

    public AttributeDescriptor(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.join(" ", this.type, this.name);
    }
}
