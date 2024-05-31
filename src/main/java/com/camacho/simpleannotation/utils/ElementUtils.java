package com.camacho.simpleannotation.utils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
                        .toList())
                .orElse(List.of());
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
                        .toList())
                .orElse(List.of());
    }

    /**
     * Analyzes if an element contains any properties (fields)
     * @param elem The element to search within
     * @return {@code true} if provided element isn't null, is either a class
     * and has at least 1 method exists
     * @param <E> The implementation of the element
     */
    public static <E extends Element> boolean hasProperties(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .anyMatch(e -> e.getKind().isField()))
                .orElse(false);
    }

    /**
     * Analyzes an element and attempts to obtain its methods
     * @param elem the element to analyze
     * @return a {@link List} containing the methods of the provided element.
     * if the element is null, or it does not contain any methods {@link List#of()} will be returned
     * @param <E> the type of the element to analyze
     */
    public static <E extends Element> List<? extends Element> getProperties(E elem) {
        return Optional.ofNullable(elem)
                .filter(ElementUtils::isClass)
                .map(Element::getEnclosedElements)
                .map(es -> es.stream()
                        .filter(e -> e.getKind().isExecutable()).toList())
                .orElse(List.of());
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
                        .toList())
                .orElse(List.of());
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
                        .toList())
                .orElse(List.of());
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
