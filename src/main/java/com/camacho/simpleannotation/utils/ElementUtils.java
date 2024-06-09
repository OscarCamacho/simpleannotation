package com.camacho.simpleannotation.utils;

import com.camacho.simpleannotation.exceptions.ClassGenerationException;
import com.google.common.collect.Lists;

import javax.lang.model.element.*;
import java.util.*;
import java.util.stream.Collectors;

public final class ElementUtils {
    private ElementUtils() {}

    public static <E extends Element> boolean isClass(E elem) {
        return Optional.ofNullable(elem).map(e -> e.getKind().isClass()).orElse(false);
    }

    public static <E extends Element> boolean isInterface(E elem) {
        return Optional.ofNullable(elem).map(e -> e.getKind().isInterface()).orElse(false);
    }

    public static <E extends  Element> boolean isAbstractClass(E elem) {
        return Optional.ofNullable(elem).map(e -> e.getKind().isClass()
                && e.getModifiers().contains(Modifier.ABSTRACT)).orElse(false);
    }

    /**
     * Analyzes if an element contains any constructors
     * @param elem The element to search within
     * @return {@code true} if provided element isn't null, is either a class or an interface
     * and at least 1 method exists
     * @param <E> The implementation of the element
     */
    public static <E extends Element> boolean hasConstructors(E elem) {
        return Optional.ofNullable(elem)
                .filter(e -> isClass(elem) || isInterface(elem))
                .map(Element::getEnclosedElements)
                .map(elements -> elements.stream()
                        .anyMatch(e -> ElementKind.CONSTRUCTOR.equals(e.getKind())))
                .orElse(false);
    }

    /**
     * Analyzes an element and attempts to obtain its constructors
     * @param elem the element to analyze
     * @return a {@link List} containing the methods of the provided element
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> List<? extends Element> getConstructors(E elem) {
        return Optional.ofNullable(elem)
                .filter(e -> isClass(elem) || isInterface(elem))
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .filter(e -> ElementKind.CONSTRUCTOR.equals(e.getKind()))
                        .collect(Collectors.toList()))
                .orElse(Lists.newArrayList());
    }

    public static <E extends Element> boolean isConstructor(E element) {
        return Optional.ofNullable(element)
                .map(e -> ElementKind.CONSTRUCTOR.equals(e.getKind()))
                .orElse(false);
    }

    public static <E extends Element> boolean isMethod(E element) {
        return Optional.ofNullable(element)
                .map(e -> ElementKind.METHOD.equals(e.getKind()) && e instanceof ExecutableElement)
                .orElse(false);
    }

    /**
     * Analyzes if an element contains any methods
     * @param elem The element to search within
     * @return {@code true} if provided element isn't null, is either a class or an interface
     * and at least 1 method exists
     * @param <E> The implementation of the element
     */
    public static <E extends Element> boolean hasMethods(E elem) {
        return Optional.ofNullable(elem)
                .filter(e -> isClass(elem) || isInterface(elem))
                .map(Element::getEnclosedElements)
                .map(elements -> elements.stream()
                        .anyMatch(e -> ElementKind.METHOD.equals(e.getKind())))
                .orElse(false);
    }

    /**
     * Analyzes an element and attempts to obtain its methods
     * @param elem the element to analyze
     * @return a {@link List} containing the methods of the provided element
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> List<? extends Element> getMethods(E elem) {
        return Optional.ofNullable(elem)
                .filter(e -> isClass(elem) || isInterface(elem))
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .filter(e -> ElementKind.METHOD.equals(e.getKind()))
                        .collect(Collectors.toList()))
                .orElse(Lists.newArrayList());
    }

    public static <E extends Element> boolean isAttribute(E element) {
        return Optional.ofNullable(element)
                .map(e -> ElementKind.FIELD.equals(e.getKind()) && e instanceof VariableElement)
                .orElse(false);
    }

    /**
     * Analyzes if an element contains any properties (fields)
     * @param elem The element to search within
     * @return {@code true} if provided element isn't null, is either a class
     * and has at least 1 method exists
     * @param <E> The implementation of the element
     */
    public static <E extends Element> boolean hasAttributes(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .anyMatch(ElementUtils::isAttribute))
                .orElse(false);
    }

    /**
     * Analyzes an element and attempts to obtain its methods
     *
     * @param elem the element to analyze
     * @param <E>  the type of the element to analyze
     * @return a {@link List} containing the methods of the provided element.
     * if the element is null, or it does not contain any methods {@link List#of()} will be returned
     */
    public static <E extends Element> Set<? extends VariableElement> getAttributesFrom(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .filter(ElementUtils::isArgument)
                        .map(e -> (VariableElement)e)
                        .collect(Collectors.toSet()))
                .orElse(Collections.emptySet());
    }

    /**
     * Analyzes if an element contains any inner classes
     * @param elem The element to search within
     * @return {@code true} if provided element isn't null, is either a class
     * and has at least 1 inner classes exists
     * @param <E> The implementation of the element
     */
    public static <E extends Element> boolean hasInnerClasses(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .anyMatch(e -> ElementKind.CLASS.equals(e.getKind())))
                .orElse(false);
    }

    /**
     * Analyzes an element and attempts to obtain its methods
     * @param elem the element to analyze
     * @return a {@link List} containing the methods of the provided element.
     * if the element is null, or it does not contain any methods {@link List#of()} will be returned
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> List<? extends Element> getInnerClasses(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .filter(e -> ElementKind.CLASS.equals(e.getKind()))
                        .collect(Collectors.toList()))
                .orElse(Lists.newArrayList());
    }

    /**
     * Analyzes if an element contains any inner classes
     * @param elem The element to search within
     * @return {@code true} if provided element isn't null, is either a class
     * and has at least 1 inner classes exists
     * @param <E> The implementation of the element
     */
    public static <E extends Element> boolean hasInnerEnums(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .anyMatch(e -> ElementKind.ENUM.equals(e.getKind())))
                .orElse(false);
    }

    /**
     * Analyzes an element and attempts to obtain its methods
     * @param elem the element to analyze
     * @return a {@link List} containing the methods of the provided element.
     * if the element is null, or it does not contain any methods {@link List#of()} will be returned
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> List<? extends Element> getInnerEnums(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .filter(e -> ElementKind.ENUM.equals(e.getKind()))
                        .collect(Collectors.toList()))
                .orElse(Lists.newArrayList());
    }

    /**
     * Checks if an {@link Element} is the representation of an attribute
     * @param element The instance to check
     * @return {@code true} if the provided element is not null and has {@link ElementKind#PARAMETER}
     * as its kind; {@code false} otherwise
     * @param <E> the implementation of element provided
     */
    public static <E extends Element> boolean isArgument(E element) {
        return Optional.ofNullable(element)
                .map(e -> ElementKind.PARAMETER.equals(e.getKind()))
                .orElse(false);
    }

    /**
     * Obtains the arguments of an element that can contain arguments
     * @param element the instance to be checked
     * @return a {@link List} of the elements contained by the provided element
     * @param <E> the actual implementation of {@link Element} to be analyzed
     */
    public static <E extends Element> List<? extends VariableElement> getArgumentsFrom(E element) {
        return Optional.ofNullable(element)
                .filter(e -> isConstructor(e) || isMethod(element))
                .filter(ElementUtils::isArgument)
                .map(e -> (ExecutableElement)e)
                .map(ExecutableElement::getParameters)
                .orElseThrow(() -> new ClassGenerationException("Cannot get arguments from element"));
    }

    /**
     * Obtains the name of the argument represented by the element provided
     * @param element the instance to be analyzed
     * @return a {@link String} representing the argument name of the element
     * @param <E> the implementation of th element to analyze
     */
    public static <E extends Element> String getArgumentName(E element) {
        return Optional.ofNullable(element)
                .filter(e -> ElementKind.PARAMETER.equals(e.getKind()) && e instanceof VariableElement)
                .map(e -> (VariableElement)e)
                .map(VariableElement::getSimpleName)
                .map(Object::toString)
                .orElseThrow(() -> new ClassGenerationException("Cannot parse element to get Argument Name"));
    }

    public static <E extends Element> String getArgumentType(E element) {
        return Optional.ofNullable(element)
                .filter(e -> ElementKind.PARAMETER.equals(e.getKind()) && e instanceof VariableElement)
                .map(Element::asType)
                .map(Object::toString)
                .orElseThrow(() -> new ClassGenerationException("Cannot parse element to get Argument Name"));
    }

    /**
     * Checks if an element is {@code public}
     * @param element the object to be analyzed
     * @return {@code true} if the modifier set contains public
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> boolean isPublic(E element) {
        return Optional.ofNullable(element)
                .map(Element::getModifiers)
                .map(mods -> mods.contains(Modifier.PUBLIC))
                .orElse(false);
    }

    /**
     * Checks if an element is {@code protected}
     * @param element the object to be analyzed
     * @return {@code true} if the modifier set contains protected
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> boolean isProtected(E element) {
        return Optional.ofNullable(element)
                .map(Element::getModifiers)
                .map(mods -> mods.contains(Modifier.PROTECTED))
                .orElse(false);
    }

    /**
     * Checks if an element is {@code private}
     * @param element the object to be analyzed
     * @return {@code true} if the modifier set contains private
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> boolean isPrivate(E element) {
        return Optional.ofNullable(element)
                .map(Element::getModifiers)
                .map(mods -> mods.contains(Modifier.PRIVATE))
                .orElse(false);
    }

}
