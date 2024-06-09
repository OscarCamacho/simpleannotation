package com.camacho.simpleannotation.model.descriptors;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.camacho.simpleannotation.utils.ElementUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static com.camacho.simpleannotation.utils.ElementUtils.getArgumentsFrom;

public final class ConstructorDescriptor {

    private static final String TO_STRING_FORMAT = "%s %s(%s)%s";

    private final String className;
    private final Set<String> modifiers;
    private final Map<String, String> arguments;
    private final CodeBlockDescriptor codeDescriptor;

    public ConstructorDescriptor(String className) {
        this.className = className;
        this.arguments = new LinkedHashMap<>();
        this.modifiers = new HashSet<>();
        this.codeDescriptor = new CodeBlockDescriptor().setCodeIndentationLevel(2);
    }

    public ConstructorDescriptor addArgument(String argName, String argType) {
        this.arguments.put(argName, argType);
        return this;
    }

    public ConstructorDescriptor addArguments(Map<String, String> arguments) {
        this.arguments.putAll(arguments);
        return this;
    }

    public ConstructorDescriptor addModifier(String modifier) {
        this.modifiers.add(modifier);
        return this;
    }

    public ConstructorDescriptor addModifiers(Set<Modifier> modifiers) {
        this.modifiers.addAll(modifiers.stream().map(Object::toString).collect(Collectors.toList()));
        return this;
    }

    public String getClassName() {
        return className;
    }

    public Set<String> getModifiers() {
        return modifiers;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public CodeBlockDescriptor getCodeDescriptor() {
        return codeDescriptor;
    }

    public ConstructorDescriptor addCodeLine(String line) {
        this.codeDescriptor.addLine(line);
        return this;
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
            codeBlock.append(this.codeDescriptor);
        } else {
            codeBlock.append("{}");
        }
        return String.format(TO_STRING_FORMAT,
                String.join(" ", modifiers),
                className,
                argList,
                codeBlock);
    }

    public static <E extends Element> ConstructorDescriptor from(E element) {
        return Optional.ofNullable(element)
                .filter(ElementUtils::isConstructor)
                .map(e -> new ConstructorDescriptor(e.getEnclosingElement().getSimpleName().toString())
                        .addArguments(getArgumentsFrom(e).stream()
                                .collect(Collectors.toMap(ElementUtils::getArgumentName,
                                        ElementUtils::getArgumentType)))
                        .addModifiers(e.getModifiers())
                )
                .orElseThrow(() -> new ClassGenerationException("Could not parse element to ConstructorDescriptor"));
    }
}
