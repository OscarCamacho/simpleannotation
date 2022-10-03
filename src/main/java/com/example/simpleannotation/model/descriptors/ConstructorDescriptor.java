package com.example.simpleannotation.model.descriptors;


import java.util.HashMap;
import java.util.Map;

public final class ConstructorDescriptor {

    private static final String TO_STRING_FORMAT = "%s(%s)";

    private final String className;
    private final Map<String, String> arguments;

    public ConstructorDescriptor(String className) {
        this.className = className;
        this.arguments = new HashMap<>();
    }

    public void addArgument(String argName, String argType) {
        this.arguments.put(argName, argType);
    }

    @Override
    public String toString() {
        StringBuilder argList = new StringBuilder();
        for (String argName : this.arguments.keySet()) {
            argList.append(this.arguments.get(argName)).append(" ").append(argName).append(", ");
        }
        argList.deleteCharAt(argList.length());
        return String.format(TO_STRING_FORMAT, className, argList);
    }
}
