package com.example.simpleannotation.model.descriptors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

final class AttributeDescriptorTest {

    private static Stream<Arguments> attributeDescriptorProvider () {
        return Stream.of(
                Arguments.of("protected Boolean aBoolean",
                        new AttributeDescriptor("aBoolean", "Boolean", "protected"))
        );
    }

    @ParameterizedTest
    @MethodSource("attributeDescriptorProvider")
    void attributeDescriptor_shouldPrintCorrectly(String expected, AttributeDescriptor descriptor) {
        assertEquals(expected, descriptor.toString());
    }

}
