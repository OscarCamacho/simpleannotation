package com.camacho.simpleannotation.model.descriptors;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.camacho.simpleannotation.utils.ElementUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public final class MethodDescriptor {

    private static final String TO_STRING_FORMAT = "%s %s %s(%s)%s";

    private final String name;
    private final List<String> modifiers;
    private final Map<String, String> arguments;
    private final String returnType;

    private final CodeBlockDescriptor codeDescriptor;

    public MethodDescriptor(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
        this.arguments = new HashMap<>();
        this.modifiers = new ArrayList<>();
        this.codeDescriptor = new CodeBlockDescriptor().setCodeIndentationLevel(2);
    }

    public MethodDescriptor addArgument(String argName, String argType) {
        this.arguments.put(argName, argType);
        return this;
    }

    public MethodDescriptor addArguments(Map<String, String> arguments) {
        Optional.ofNullable(arguments).filter(Map::isEmpty).ifPresent(this.arguments::putAll);
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

    public CodeBlockDescriptor getCodeDescriptor() {
        return this.codeDescriptor;
    }

    public MethodDescriptor addCodeLine(String line) {
        this.codeDescriptor.addLine(line);
        return this;
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
        StringBuilder codeBlock = new StringBuilder();
        if (this.codeDescriptor.isNotEmpty()) {
            codeBlock.append(this.codeDescriptor);
        } else {
            codeBlock.append("{}");
        }
        return String.format(TO_STRING_FORMAT,
                String.join(" ", modifiers),
                returnType,
                name,
                argList,
                codeBlock);
    }

    public static <E extends Element> MethodDescriptor from(E element) {
        return Optional.ofNullable(element)
                .filter(ElementUtils::isMethod)
                .map(e -> (ExecutableElement)e)
                .map(e -> new MethodDescriptor(e.getSimpleName().toString(), e.getReturnType().toString())
                        .addArguments(ElementUtils.getArgumentsFrom(e).stream().collect(toMap(
                                ElementUtils::getArgumentName,
                                ElementUtils::getArgumentType))))
                .orElseThrow(() -> new ClassGenerationException("Cannot parse Element to MethodDescriptor"));
    }
}
