# Simple Annotation
**Sugerencia**: describe aqui que hace tu proyecto y el objetivo de este tutorial/curso, como ejemplo.....
Demostración de procesado de una anotación, la cual utilizada en una clase tiene el efecto de implementar
el patrón de diseño builder en la clase anotada.

Para que desde que inicien el tutorial/sesión, se hagan una clara idea de donde van a partir,
que conocimientos obtendran y que es lo que debe hacer su código al final para confirmar que
realmente funciona y que lograron el objetivo de la platica/curso/tutorial.


O quizas listar los ejercicios que se esperan cumplir:
1. Uso de anotaciones
2. Procesamiento de anotaciones en clases.
3. Procesamiento de anotaciones en metodos.
4. .....


Describe cuantos proyectos vas a manejar y dales un alias, para que sepan con anticipación la
arquitectura y distribución del ejercicio, por ejemplo:

- SimpleAnnotationProject (this project) : contains the annotation code
- Client Project: contains your code where you will add the magic functionality through annotations.

Si es un solo proyecto, indicar en que paquetes puedo encontrar que cosa.

**Nota**: En este punto del review yo no se si realmente son 2 proyectos o mas o menos.

No se donde vive la anotación, si en este proyecto o en el cliente.

Yo no se donde vive el codigo de procesamiento, si aqui o lo hará en el cliente,
y el README debería bosquejar esas ideas que me ayudaran a navegar este tutorial/curso,
si me llegase a peder mientras tu explicas o si quiero seguirlo horas después de terminada
la explicación en vivo.

Si usas el mismo proyecto para todos los usos, entonces puedes explicar que el codigo
en main/com.camacho.un.cierto.paquete contiene el procesamiento,
y en test/com.camacho.otro.paquete se encuentra el codigo cliente que hace
uso de la anotación (por ejemplo).


## Requirements
**Sugerencia**: incluir las ligas a tutoriales donde se demuestre como configurar java y maven.
**Sugerencia**: incluir entre los requerimientos otro proyecto donde aplicar la anotacion,
incluso podrias proveer otro git repository donde bajar ese client project muy sencillo (e incluir
el comando git clone entre las instrucciones de instalación),
entiendo que mas abajo hablas de que puede ser otro o incluso este mismo proyecto, pero creo que
seria mas claro si tu ya les proporcionas otro proyecto donde ellos aplicaran los cambios.

Creo yo, que entre menos decisiones tengan ellos que tomar, mas fácil sera que capten y se concentren
en los conceptos de procesado de anotaciones y se vayan con una mejor experiencia.

- Java 8 or newer installed and configured (`JAVA_HOME` environment variable should be available globally)
- Maven installed and configured (`M2_HOME` environment variable should be available globally)
- Client project where you will make use of the annotation functionality.


-------

## 1. Installation 

**Sugenerencia**: incluye el paso de descarga de tu proyecto:
```bash
git clone https://github.com/OscarCamacho/simpleannotation
```

To install this as a dependency please download the source and run the following command:

**Sugerencia**: yo cambiaria el texto de arriba por uno que de
a entender que son varios pasos en diferentes proyectos:

To use it as a dependency:

1. Compile and install this project (annotation project):

```bash
  mvn clean install
```

2. Afterwards, you can add it as a dependency into your client project.

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


-------

## 2. Adding a new annotation
To create your client proyect, simply follow the template below
or download it with:

```bash
git clone http://url.to.client.template
```


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

Notice that the annotation you are creating is also annotated with `@Retention` and with `@Target`.

`@Retention` indicates if the annotation you add to your code will be deleted at which stage or if it is going to be preserved at
runtime. This is useful if you want to check if a class is annotated; however, remember that Java Reflection API is
performance consuming and should be avoided in performance sensitive applications.

`@Target` annotation tells the compiler what type of elements can be annotated (e.g. classes, methods, etc.), in this example `YourAnnotation` indicates only classes can be
annotated with it.


-------

## 3. Create Annotation Processor

Once you created your new annotation, all that is left is to create your annotation processor. You are welcome to extend
from `com.camacho.AbstractAnnotationProcessor` which already implements from 
`javax.annotation.processing.AbstractProcessor` and provides some useful lifecycle annotation processing methods to
implement, or you can extend from `javax.annotation.processing.AbstractProcessor` yourself.

Whichever you end up choosing, you will need to annotate your processor with:
- `@SupportedAnnotationTypes` to indicate the
annotations which the processor will process.
- `@SupportedSourceVersion` to indicate the Java Versions supported by the
annotation and the processor.
- `@AutoService` to generate the necessary registrations for your processor. Note that
this annotation is from an external dependency from Google. Check for the latest documentation and licenses
at [this page](https://github.com/google/auto/tree/master/service)

**Sugerencia**: poner la libreria de Google Auto dentro de una seccion de dependencias para compilación.
**Sugerencia**: incluir un snippet o un repo git (tuyo) desde el cual  bajar el skeleton de un procesador de
anotaciones, con el maven ya configurado con la dependencia de google auto.

All that is left to do is to process the annotation you just created. The `SimpleAnnotation` project offers some utility
classes to generate new Java files. Note that at this time it is not compiler checked. Therefore, you may need check for
errors your generated files. It also does not automatically format your code, so you are responsible for it.


## 4. Code Execution

Escribir la lista de comandos que necesito correr,
para ver el codigo en acción, quizas ejemplos de la salida en consola,
los cuales son analizados y explicados.

por ejemplo:

para correr el ejemplo:

```shell
mvn <algun commando para correr java main>
```

Lo cual generara la salida:


```
[INFO] blah1 blah2 blah3 blah4
[INFO] blah1 ----- blah3 blah4
[INFO] blah1 blah2 ----- blah4
[INFO] blah1 blah2 blah3 -----
```

El segundo renglon se imprime correctamente
gracias a que la anotacion se proceso correctamente.

