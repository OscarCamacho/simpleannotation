package com.example.simpleannotation;

import org.junit.jupiter.api.Test;

public final class BuilderAnnotationProcessorTest {
    @Test
    public void fakeAnnotatedClass_isDetectedBy_processor () {
        FakeAnnotatedClass instance = new FakeAnnotatedClass();
    }
}
