package com.camacho.simpleannotation.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.camacho.simpleannotation.model.descriptors.AttributeDescriptor;
import com.camacho.simpleannotation.model.descriptors.ConstructorDescriptor;
import com.camacho.simpleannotation.model.descriptors.MethodDescriptor;

public abstract class GeneratedClass {
    
    private String packageName;
    private String className;
    private String extendClass;
    private List<String> implementClasses;
    private List<String> imports;
    private List<String> staticImports;
    private List<AttributeDescriptor> attributes;
    private List<ConstructorDescriptor> constructors;
    private List<MethodDescriptor> methods;

    protected GeneratedClass () {
        this.imports = new ArrayList<>();
        this.staticImports = new ArrayList<>();
        this.implementClasses = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.constructors = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    public Optional<String> getPackageName() {
        return Optional.ofNullable(packageName);
    }

    public GeneratedClass setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Optional<String> getClassName() {
        return Optional.ofNullable(className);
    }

    public GeneratedClass setClassName(String className) {
        this.className = className;
        return this;
    }

    public Optional<String> getExtendClass() {
        return Optional.ofNullable(extendClass);
    }

    public GeneratedClass setExtendClass(String extendClass) {
        this.extendClass = extendClass;
        return this;
    }

    public List<String> getImplementClasses() {
        return implementClasses;
    }

    public GeneratedClass setImplementClasses(List<String> implementClasses) {
        this.implementClasses = implementClasses;
        return this;
    }

    public List<String> getImports () {
        return imports;
    }

    public GeneratedClass setImports (List<String> imports) {
        this.imports = imports;
        return this;
    }

    public List<String> getStaticImports () {
        return staticImports;
    }

    public GeneratedClass setStaticImports (List<String> staticImports) {
        this.staticImports = staticImports;
        return this;
    }

    public List<AttributeDescriptor> getAttributes() {
        return attributes;
    }

    public GeneratedClass setAttributes(List<AttributeDescriptor> attributes) {
        this.attributes = attributes;
        return this;
    }

    public List<ConstructorDescriptor> getConstructors() {
        return constructors;
    }

    public GeneratedClass setConstructors(List<ConstructorDescriptor> constructors) {
        this.constructors = constructors;
        return this;
    }

    public List<MethodDescriptor> getMethods() {
        return methods;
    }

    public GeneratedClass setMethods(List<MethodDescriptor> methods) {
        this.methods = methods;
        return this;
    }

    @Override
    public final String toString() {
        StringBuilder classString = new StringBuilder();
        this.getPackageName().ifPresent(packageNameString -> classString.append("package ")
                .append(packageNameString).append(";\n"));
        if (!this.imports.isEmpty()) {
            for (String importString : this.imports) {
                classString.append(importString).append("\n");
            }
        }
        classString.append("public class ")
                .append(this.getClassName().orElseThrow(ClassGenerationException::new))
                .append(" ");
        this.getExtendClass().ifPresent(extendClassString -> classString.append("extends ")
                .append(extendClassString).append(" "));
        if (!this.implementClasses.isEmpty()) {
            classString.append("implements ");
            for (int i = 0; i < this.implementClasses.size(); i++) {
                classString.append(this.implementClasses.get(i));
                if (i < this.implementClasses.size() - 1) {
                    classString.append(", ");
                }
            }
        }
        classString.append("{ //Class Begin\n"); // Class begin
        for (AttributeDescriptor attribute : this.attributes) {
            classString.append("\t").append(attribute).append(";\n");
        }
        for (ConstructorDescriptor constructor : this.constructors) {
            classString.append("\t").append(constructor).append("\n");
        }
        for (MethodDescriptor method : this.methods) {
            classString.append("\t").append(method).append("\n");
        }
        classString.append("}// Class end\n"); // End of Class
        return classString.toString();
    }

}