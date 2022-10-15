# Simple Annotation

## Requirements
- Java 8 or newer installed and configured (`JAVA_HOME` environment variable should be available globally)
- Maven installed and configured (`M2_HOME` environment variable should be available globally)

## Installation 

To install this as a dependency please download the source and run the following command:

```bash
  mvn clean install
```
Afterwards, you can add it as a dependency to  your Maven project.

```XML
    <dependencies>
        ...
        <dependency>
            <groupId>com.camacho</groupId>
            <artifactId>simpleannotation</artifactId>
            <version>1.0</version>
        </dependency>
    </dependencies>
```
## Adding a new annotation
Preferably, you would need a different maven project to the one you are going to annotate. However, it is not entirely 
necessary. On the project which will hold the annotations and the processors you can add your annotations classes.
Simply follow the template below

```Java
package your.ownpackage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface YourAnnotation {}
```

Notice that the annotation you are creating is also annotated with `@Retention` and with `@Target`. The first annotation
indicates if the annotation you add to your code will be deleted at which stage or if it is going to be preserved at
runtime. This is useful if you want to check if a class is annotated; however, remember that Java Reflection API is
performance consuming and should be avoided in performance sensitive applications. The second annotation tells the
compiler what type of elements can be annotated, in this example `YourAnnotation` indicates only classes can be
annotated with it.

Once you created your new annotation, all that is left is to create your annotation processor. You are welcome to extend
from `com.camacho.AbstractAnnotationProcessor` which already implements from 
`javax.annotation.processing.AbstractProcessor` and provides some useful lifecycle annotation processing methods to
implement, or you can extend from `javax.annotation.processing.AbstractProcessor` yourself.

Whichever you end up choosing, you will need to annotate your processor with `@SupportedAnnotationTypes` to indicate the
annotations which the processor will processor, `@SupportedSourceVersion` to indicate the Java Versions supported by the
annotation and the processor, `@AutoService` to generate the necessary registrations for your processor. Note that
`@AutoService` is an annotation from an external dependency from Google. Check for the latest documentation and licenses
at [this page](https://github.com/google/auto/tree/master/service)

All that is left to do is to process the annotation you just created. The `SimpleAnnotation` project offers some utility
classes to generate new Java files. Note that at this time it is not compiler checked. Therefore, you may need check for
errors in your generated files. It also does not automatically format your code, so you are responsible for it.
