package com.camacho.simpleannotation.model.descriptors;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.camacho.simpleannotation.utils.ElementUtils;

import javax.lang.model.element.Element;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.camacho.simpleannotation.utils.ElementUtils.getAttributesFrom;

public final class ClassDescriptor {

    private static final String TO_STRING_FORMAT =
            "class %s%s: constructors: %s, attributes: %s, methods: %s";

    private String className;
    private String packageName;
    private final Set<ConstructorDescriptor> constructors;
    private final Set<AttributeDescriptor> attributes;
    private final Set<MethodDescriptor> methods;

    public ClassDescriptor() {
        this.constructors = new HashSet<>();
        this.attributes = new HashSet<>();
        this.methods = new HashSet<>();
    }

    public ClassDescriptor setClassName(String className) {
        this.className = className;
        return this;
    }

    public ClassDescriptor setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public void addConstructor(ConstructorDescriptor constructor) {
        this.constructors.add(constructor);
    }

    public ClassDescriptor addConstructors(Set<ConstructorDescriptor> constructorDescriptors) {
        this.constructors.addAll(constructorDescriptors);
        return this;
    }

    public void addAttribute(AttributeDescriptor attribute) {
        this.attributes.add(attribute);
    }

    public ClassDescriptor addAttributes(Set<AttributeDescriptor> attributeDescriptors) {
        this.attributes.addAll(attributeDescriptors);
        return this;
    }

    public void addMethod(MethodDescriptor method) {
        this.methods.add(method);
    }

    public ClassDescriptor addMethods(Set<MethodDescriptor> methodDescriptors) {
        this.methods.addAll(methodDescriptors);
        return this;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public Set<ConstructorDescriptor> getConstructors() {
        return constructors;
    }

    public Set<AttributeDescriptor> getAttributes() {
        return attributes;
    }

    public Set<MethodDescriptor> getMethods() {
        return methods;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT,
                this.packageName,
                this.className,
                this.constructors,
                this.attributes,
                this.methods);
    }

    public static <E extends Element> ClassDescriptor from(E element) {
        return Optional.ofNullable(element)
                .map(e -> new ClassDescriptor()
                        .setPackageName(e.getEnclosingElement().getSimpleName().toString())
                        .setClassName(e.getSimpleName().toString())
                        .addConstructors(e.getEnclosedElements().stream()
                                .filter(ElementUtils::isConstructor)
                                .map(ConstructorDescriptor::from)
                                .collect(Collectors.toSet()))
                        .addAttributes(getAttributesFrom(e).stream()
                                .map(AttributeDescriptor::from)
                                .collect(Collectors.toSet()))
                        .addMethods(ElementUtils.getMethods(e).stream()
                                .map(MethodDescriptor::from)
                                .collect(Collectors.toSet()))
                )
                .orElseThrow(() -> new ClassGenerationException("Cannot parse element to ClassDescriptor"));
    }
}
