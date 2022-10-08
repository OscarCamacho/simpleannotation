package com.example.simpleannotation.model.descriptors;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class AttributeDescriptorTest {

    private static Stream<Arguments> attributeDescriptorProvider () {
        return Stream.of(
                Arguments.of("protected Boolean aBoolean",
                        new AttributeDescriptor("aBoolean", "Boolean", "protected"))
        );
    }

    @ParameterizedTest
    @MethodSource("attributeDescriptorProvider")
    void attributeDesciptor_shouldPrintCorrectly() {
        String expected = "protected Boolean aBoolean";

        AttributeDescriptor attributeDescriptor = new AttributeDescriptor("aBoolean",
                "Boolean", "protected");

        assertEquals(attributeDescriptor.toString(), expected);
    }

}
