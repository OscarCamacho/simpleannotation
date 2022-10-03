package com.example.simpleannotation.model.descriptors;

import java.util.HashMap;
import java.util.Map;

public final class MethodDescriptor {

    private final String name;
    private final Map<String, String> arguments;

    public MethodDescriptor(String name) {
        this.name = name;
        this.arguments = new HashMap<>();
    }

    public void addArgument(String argName, String argType) {
        this.arguments.put(argName, argType);
    }

    @Override
    public String toString() {
        return "";
    }
}
