package com.example.simpleannotation.model.descriptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ConstructorDescriptor {

    private static final String TO_STRING_FORMAT = "%s %s(%s)%s";

    private final String className;
    private final List<String> modifiers;
    private final Map<String, String> arguments;
    private final CodeBlockDescriptor codeDescriptor;

    public ConstructorDescriptor(String className) {
        this.className = className;
        this.arguments = new HashMap<>();
        this.modifiers = new ArrayList<>();
        this.codeDescriptor = new CodeBlockDescriptor();
    }

    public ConstructorDescriptor addArgument(String argName, String argType) {
        this.arguments.put(argName, argType);
        return this;
    }

    public ConstructorDescriptor addModifier(String modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public String getClassName() {
        return className;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public CodeBlockDescriptor getCodeDescriptor() {
        return codeDescriptor;
    }

    @Override
    public String toString() {
        StringBuilder argList = new StringBuilder();
        if (!this.arguments.isEmpty()) {
            for (Map.Entry<String , String> entry : this.arguments.entrySet()) {
                argList.append(entry.getValue()).append(" ").append(entry.getKey()).append(", ");
            }
            argList.delete(argList.length() - 2, argList.length()); // Deletes last coma
        }
        StringBuilder codeBlock = new StringBuilder();
        if (this.codeDescriptor.isNotEmpty()) {
            codeBlock.append("{\n").append(this.codeDescriptor).append("\n}");
        }
        return String.format(TO_STRING_FORMAT,
                String.join(" ", modifiers),
                className,
                argList,
                codeBlock);
    }
}
