package com.camacho.simpleannotation.exceptions;

/** Thrown when custom annotation used in an unexpected element or unexpected way. */
public final class BadAnnotationUsageException extends Exception{
    private static final String EXCEPTION_DESCRIPTION =
            "Element %s is incorrectly annotated with %s: %s";

    public BadAnnotationUsageException (String annotatedElementClassName,
                                        String annotation,
                                        String description) {
        super(String.format(EXCEPTION_DESCRIPTION,
                annotatedElementClassName,
                annotation,
                description));
    }
}
