package com.example.simpleannotation.model;

import com.example.simpleannotation.model.descriptors.AttributeDescriptor;
import com.example.simpleannotation.model.descriptors.ConstructorDescriptor;
import com.example.simpleannotation.model.descriptors.MethodDescriptor;

import java.util.Map;
import java.util.Optional;

public final class BuilderGeneratedClass extends GeneratedClass {

    private static final String BUILDER_NAME = "%sBuilder";
    private static final String INSTANCE = "instance";
    private static final String PUBLIC = "public";
    private static final String FINAL = "final";

    private final BuilderAnnotatedClass annotatedClass;

    public BuilderGeneratedClass(BuilderAnnotatedClass annotatedClass) {
        this.annotatedClass = annotatedClass;
        this.setClassName(computeBuilderName());
        this.setPackageName(annotatedClass.getClassToBuildPackageName());
        if (annotatedClass.useSingletonBuilder()) {
            addSingletonPattern();
        } else {
            addSimpleConstructor();
        }
        addCommonFields();
        if (annotatedClass.useFluentBuilder()) {
            addFluentSetters();
        } else {
            addSimpleSetters();
        }
        addBuildMethod();
    }

    private void addCommonFields () {
        this.getAttributes().add(new AttributeDescriptor("container",
                "Map<String, Object>",
                "private"));
        this.getImports().add("import java.util.Map;");
        this.getImports().add("import java.util.HashMap;");
        this.getConstructors().forEach(constructor ->
            constructor.addCodeLine("this.container = new HashMap<>();"));
    }

    private void addSingletonPattern() {
        this.getAttributes().add(new AttributeDescriptor(INSTANCE,
                computeBuilderName(),
                "private static"));
        MethodDescriptor descriptor = new MethodDescriptor("getInstance", computeBuilderName());
        descriptor.addModifier(PUBLIC);
        descriptor.addModifier("static");
        descriptor.addCodeLine("if (instance == null) {")
                .addCodeLine(String.format("\tinstance = new %s();", computeBuilderName()))
                .addCodeLine("}")
                .addCodeLine("return instance;");
        this.getMethods().add(descriptor);
        ConstructorDescriptor privateConstructor = new ConstructorDescriptor(computeBuilderName());
        privateConstructor.addModifier("private");
        this.getConstructors().add(privateConstructor);
    }

    private void addSimpleConstructor() {
        ConstructorDescriptor publicConstructor = new ConstructorDescriptor(computeBuilderName());
        publicConstructor.addModifier(PUBLIC);
        this.getConstructors().add(publicConstructor);
    }

    private void addFluentSetters () {
        for (Map.Entry<AttributeDescriptor, Optional<MethodDescriptor>> attributeMethodMapping :
             this.annotatedClass.getAttributeSetterMapping().entrySet()) {
            this.getMethods().add(new MethodDescriptor(
                    computeSetterName(attributeMethodMapping.getKey().getName()),
                    computeBuilderName())
                    .addModifier(PUBLIC)
                    .addModifier(FINAL)
                    .addArgument("value",
                            attributeMethodMapping.getKey().getType())
                    .addCodeLine(String.format("this.container.putIfAbsent(\"%s\", value);",
                            attributeMethodMapping.getKey().getName()))
                    .addCodeLine("return this;"));
        }
    }

    private void addSimpleSetters () {
        for(Map.Entry<AttributeDescriptor, Optional<MethodDescriptor>> attributeMethodMapping:
                this.annotatedClass.getAttributeSetterMapping().entrySet()) {
            this.getMethods().add(new MethodDescriptor(
                    computeSetterName(attributeMethodMapping.getKey().getName()), "void")
                    .addModifier(PUBLIC)
                    .addModifier(FINAL)
                    .addArgument("value",
                            attributeMethodMapping.getKey().getType())
                    .addCodeLine(String.format("this.container.putIfAbsent(\"%s\", value);",
                            attributeMethodMapping.getKey().getName())));
        }
    }

    private void addBuildMethod () {
        this.getMethods().add(new MethodDescriptor("build",
                this.annotatedClass.getClassToBuild())
                .addModifier(PUBLIC)
                .addModifier(FINAL)
                .addCodeLine("if(this.container.isEmpty()) {")
                .addCodeLine("\tthrow new IllegalStateException(\"Not enough information to build\");")
                .addCodeLine("}")
                // TODO: Finish
        );
    }

    private String computeBuilderName () {
        return this.getClassName()
                .orElse(String.format(BUILDER_NAME, this.annotatedClass.getClassToBuild()));
    }

    private String computeSetterName (String attributeName) {
        return "set" + attributeName.substring(0,1).toUpperCase() +
                attributeName.substring(1);
    }
}
