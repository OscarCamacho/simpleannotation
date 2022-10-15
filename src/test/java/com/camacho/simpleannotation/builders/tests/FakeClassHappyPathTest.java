package com.camacho.simpleannotation.builders.tests;

import com.camacho.simpleannotation.builders.FakeClassHappyPath;
import com.camacho.simpleannotation.builders.FakeClassHappyPathBuilder;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

final class FakeClassHappyPathTest {
    @Test
    void buider_mustHave_necessaryMethods() {
        List<String> expectedMethods = Arrays.asList("setI", "setD", "setS", "setStrings", "build");

        assertTrue(Arrays.stream(FakeClassHappyPathBuilder.class.getMethods())
                .map(Method::getName)
                .collect(Collectors.toList()).containsAll(expectedMethods));
    }

    @Test
    void builder_mustProvideAValidInstance() {
        List<String> strings = Arrays.asList("string 1", "string 2");
        FakeClassHappyPath expected = new FakeClassHappyPath();
        expected.setI(1);
        expected.setD(2.0);
        expected.setS("string");
        expected.setStrings(strings);

        FakeClassHappyPathBuilder builder = FakeClassHappyPathBuilder.getInstance();
        builder.setI(1);
        builder.setD(2.0);
        builder.setS("string");
        builder.setStrings(strings);
        FakeClassHappyPath obtained = builder.build();

        assertEquals(expected, obtained);
    }

    @Test
    void builder_shouldRequireNewValues_forNewInstance() {
        List<String> strings = Arrays.asList("string 1", "string 2");
        FakeClassHappyPathBuilder builder = FakeClassHappyPathBuilder.getInstance();
        builder.setI(1);
        builder.setD(2.0);
        builder.setS("string");
        builder.setStrings(strings);
        FakeClassHappyPath obtained = builder.build();

        assertThrows(IllegalStateException.class, () -> builder.build());
    }
}
