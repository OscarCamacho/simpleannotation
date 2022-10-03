package com.example.simpleannotation.model.descriptors;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementKindVisitor8;

public final class TypeMapper extends ElementKindVisitor8<TypeMapper, Void> {

    private final ClassDescriptor classDescriptor;

    public TypeMapper () {
        this.classDescriptor = new ClassDescriptor();
    }

    @Override
    public TypeMapper visitTypeAsClass(TypeElement e, Void unused) {
        this.classDescriptor.setClassName(e.getSimpleName().toString());
        e.getEnclosedElements().forEach(
                element -> this.classDescriptor.addMethod(element.getSimpleName().toString()));
        return this;
    }

    public ClassDescriptor getClassDescriptor() {
        return classDescriptor;
    }
}
