package com.example.simpleannotation.model.descriptors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class AttributeDescriptorTest {

    @Test
    public void attributeDesciptor_shouldPrintCorrectly() {
        String expected = "protected Boolean aBoolean";

        AttributeDescriptor attributeDescriptor = new AttributeDescriptor("aBoolean",
                "Boolean", "protected");

        assertEquals(attributeDescriptor.toString(), expected);
    }

}
