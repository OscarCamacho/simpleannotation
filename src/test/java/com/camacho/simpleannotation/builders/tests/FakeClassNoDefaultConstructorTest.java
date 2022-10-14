package com.camacho.simpleannotation.builders.tests;

import com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructor;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

final class FakeClassNoDefaultConstructorTest {

    private static final List<String> EXPECTED_METHODS = Arrays.asList("setI","setD", "setS", "setStrings",
            "setObject", "build");

    @Test
    void builder_shouldHave_expectedMethods () {
        assertTrue(Arrays.stream(
                com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructorBuilder.class.getMethods())
                .map(Method::getName)
                .collect(Collectors.toList())
                .containsAll(EXPECTED_METHODS));
    }

    @Test
    void builder_mustProvideAValidInstance () {
        List<String> strings = Arrays.asList("string1", "string2");
        Object object = new Object();
        com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructor expected =
                new com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructor(1, 2.0, "string", strings);
        expected.setObject(object);

        com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructorBuilder builder =
                com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructorBuilder.getInstance();
        builder.setI(1);
        builder.setD(2.0);
        builder.setS("string");
        builder.setStrings(strings);
        builder.setObject(object);
        FakeClassNoDefaultConstructor obtained = builder.build();

        assertEquals(expected, obtained);
    }

    @Test
    void builder_shouldRequireNewValues_forNewInstance () {
        List<String> strings = Arrays.asList("string 1", "string 2");
        com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructorBuilder builder =
                com.camacho.simpleannotation.builders.FakeClassNoDefaultConstructorBuilder.getInstance();
        builder.setI(1);
        builder.setD(2.0);
        builder.setS("string");
        builder.setStrings(strings);
        builder.build();

        assertThrows(IllegalStateException.class, () -> builder.build());
    }
}
