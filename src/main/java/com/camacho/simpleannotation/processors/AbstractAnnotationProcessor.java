package com.camacho.simpleannotation.processors;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.camacho.simpleannotation.exceptions.BadAnnotationUsageException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * This class will contain the necessary logic to process an annotated class.
 * @param <A> - Represents the Annotation to process
 * @param <M> - Represents the Model to which the annotation information will transform
 */
public abstract class AbstractAnnotationProcessor<A extends Annotation, M>
        extends AbstractProcessor {

    private final Class<A> annotationClazz;

    protected AbstractAnnotationProcessor (Class<A> annotation) {
        this.annotationClazz = annotation;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(annotationClazz)) {
                try {
                    validateElement(annotatedElement);
                    M transformedModel = transformElementToModel(annotatedElement,
                            annotatedElement.getAnnotation(annotationClazz));
                    finalizeElementProcessing(transformedModel);
                } catch (BadAnnotationUsageException | ClassGenerationException e) {
                    printErrorMessage(e.getMessage(), annotatedElement);
                }
        }
        return false;
    }

    private void printErrorMessage(String errorMessage, Element element) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                errorMessage != null ? errorMessage : "", element);
    }

    /**
     * This method must contain the logic to validate if an annotated element uses the annotation
     * correctly and return feedback on how to use it appropriately
     * @param annotatedElement - The element that makes use of the annotation being processed.
     * @throws BadAnnotationUsageException - If the element using the annotation is doing so
     * inappropriately.
     */
    abstract void validateElement(Element annotatedElement) throws BadAnnotationUsageException;

    /**
     * This method must transform the related information of the <code>annotation</code> and the
     * <code>annotatedElement</code> into an instance of {@link M} for later consumption.
     * @param annotatedElement - The element that was annotated.
     * @param annotation - The annotation itself.
     * @return an instance of {@link M} containing the necessary information for later consumption
     */
    abstract M transformElementToModel(Element annotatedElement, A annotation);

    /**
     * This method triggers the final steps for annotation processing. It may be used to trigger
     * the build of generated class.
     * @param model - The information needed to complete this step.
     */
    abstract void finalizeElementProcessing(M model) throws ClassGenerationException;

}
