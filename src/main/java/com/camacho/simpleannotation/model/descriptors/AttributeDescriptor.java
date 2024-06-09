package com.camacho.simpleannotation.model.descriptors;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.camacho.simpleannotation.utils.ElementUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public String getAccessModifier() {
        return this.accessModifier;
    }

    @Override
    public String toString() {
        return String.join(" ", this.accessModifier, this.type, this.name);
    }

    public static <E extends Element> AttributeDescriptor from(E element) {
        return Optional.ofNullable(element)
                .filter(ElementUtils::isAttribute)
                .map(e -> (VariableElement)e)
                .map(e -> new AttributeDescriptor(e.getSimpleName().toString(),
                        e.asType().toString(),
                        e.getModifiers().stream()
                                .map(Objects::toString)
                                .collect(Collectors.joining(" "))))
                .orElseThrow(() -> new ClassGenerationException("Cannot parse element to attribute"));
    }
}
