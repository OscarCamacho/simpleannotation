package com.example.simpleannotation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

final class BuilderAnnotationProcessorTest {
    @Test
    void fakeAnnotatedClass_isDetectedBy_processor () {
        FakeAnnotatedClass instance = new FakeAnnotatedClass();
        assertNotNull(instance);
    }
}
