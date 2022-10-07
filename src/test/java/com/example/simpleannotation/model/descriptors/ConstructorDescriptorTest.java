package com.example.simpleannotation.model.descriptors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public final class ConstructorDescriptorTest {

    private static Stream<Arguments> constructorDescriptorProvider () {
        return Stream.of(
                Arguments.of("private FakeClass(String string, Integer intVariable)",
                        new ConstructorDescriptor("FakeClass")
                                .addModifier("private")
                                .addArgument("string", "String")
                                .addArgument("intVariable", "Integer")),
                Arguments.of("protected FakeClass()",
                        new ConstructorDescriptor("FakeClass")
                                .addModifier("protected"))
        );
    }

    @ParameterizedTest
    @MethodSource("constructorDescriptorProvider")
    public void constructorDescriptor_shouldPrintCorrectly (String expected,
                                                            ConstructorDescriptor constructorDescriptor) {
        assertEquals(expected, constructorDescriptor.toString());
    }

}
