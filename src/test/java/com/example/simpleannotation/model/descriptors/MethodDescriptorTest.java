package com.example.simpleannotation.model.descriptors;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class MethodDescriptorTest {

    private static Stream<Arguments> provideMethodDescriptors () {
        return Stream.of(
                Arguments.of("protected void myMethod()",
                        new MethodDescriptor("myMethod", "void")
                                .addModifier("protected")),
                Arguments.of("private int myMethod(String s, MyObject mObj)",
                        new MethodDescriptor("myMethod", "int")
                                .addModifier("private")
                                .addArgument("s", "String")
                                .addArgument("mObj", "MyObject"))
        );
    }

    @ParameterizedTest
    @MethodSource("provideMethodDescriptors")
    public void methodDescriptor_shouldPrintCorrectly(String expected, MethodDescriptor underTest) {
        assertEquals(expected, underTest.toString());
    }
}
