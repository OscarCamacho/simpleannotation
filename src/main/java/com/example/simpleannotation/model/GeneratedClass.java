package com.example.simpleannotation.model;

import java.util.List;
import java.util.ArrayList;
import com.example.simpleannotation.model.descriptors.*;

public abstract class GeneratedClass {
    
    private String packageName;
    private String className;
    private String extendClass;
    private List<String> implementClasses;
    private List<AttributeDescriptor> attributes;
    private List<ConstructorDescriptor> constructors;
    private List<MethodDescriptor> methods;

    protected GeneratedClass () {
        this.implementClasses = new ArrayList<>();
        this.attributes = new ArrayList<>();
        this.constructors = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "";
    }

}