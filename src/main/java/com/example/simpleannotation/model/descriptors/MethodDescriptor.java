package com.example.simpleannotation.model.descriptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MethodDescriptor {

    private static final String TO_STRING_FORMAT = "%s %s %s(%s)";

    private final String name;
    private final List<String> modifiers;
    private final Map<String, String> arguments;
    private final String returnType;

    public MethodDescriptor(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
        this.arguments = new HashMap<>();
        this.modifiers = new ArrayList<>();
    }

    public MethodDescriptor addArgument(String argName, String argType) {
        this.arguments.put(argName, argType);
        return this;
    }

    public MethodDescriptor addModifier(String modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public String getName() {
        return name;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        StringBuilder argList = new StringBuilder();
        if (!arguments.isEmpty()) {
            for (Map.Entry<String, String> entry : this.arguments.entrySet()) {
                argList.append(entry.getValue()).append(" ").append(entry.getKey()).append(", ");
            }
            argList.delete(argList.length() - 2, argList.length());
        }
        return String.format(TO_STRING_FORMAT,
                String.join(" ", modifiers),
                returnType,
                name,
                argList);
    }
}
