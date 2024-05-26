# Apuntes curso TDD
- [Información general](#informacion-general)
- [Tests unitarios](#tests-unitarios)
- [Ejecución de tests en clases anidadas](#organización-de-tests-en-clases-anidadas-nested-class)
- [Maven surefire](#maven-surefire)


## Información general
- Para trabajar en TDD primero se crean las pruebas y luego se crea el código.
- Inicialmente, los tests no tienen un orden al ejecutar una clase de principo a fin. Si queremos un orden en la ejecución de los tests usaremos:
- En testing se suelen utilizar los guiones bajos para separar las palabras de las nomenclaturas de los métodos. 
  * Ej. *test_account_name()*.
- El tipo *BigDecimal* es inmutable esto quiere decir que cualquier operación que se realice sobre un objeto *BigDecimal* devolverá un nuevo objeto del mismo tipo en lugar de modificar el existente.
- El tipo BigDecimal se compara con *compareTo()*
- Una de las características de JUnit Jupiter es que soporta expresiones lambda.
- Utilizando *fail();* al principio del test vamos a provocar el error del test entero. Es un método estático de la clase Assertions.
   * Ver Ej. [Account entity test L53][account-entity-test-L53]
- Para poder testear en entornos concretos [Conditional examples test L113][conditional-examples-test-L113]

## Tests unitarios 
- Tenemos que tener en cuenta que por cada test se va a crear una instancia nueva es decir, el valor que utilicemos en el test solo funcionará para ese test.
- Los tests unitarios prueban, solamente, si un método concreto funciona. No comprueba si es compatible con todas las demás.
- Hay que procurar que cada uno de loes tests estén lo más desacoplados posible entre ellos.
- Los tests se tienen que volver a probar constantemente.
- Podemos estructurar los tests resolviendo los siguientes:
    * GIVEN: Describe el estado antes de empezar el comportamiento que se está especificando en el escenario. Condiciones previas a la prueba.
    * WHEN: Es el comportamiento que se especifica en la prueba.
    * THEN: Son los comportamientos que se esperan debidos al WHEN.

## Ejecución de tests en clases anidadas (nested class)
- Con la anotación **@Nested** indicamos que la clase es anidada. Para que las clases anidadas se ejecuten en los tests deben ser static.
- Si falla un test dentro de la clase anidada fallará la clase y el padre.
- Se recomienda utilizar las clases anidadas para categorizar una clase y ordenarla.

## Maven surefire
- Este plugin nos permite ejecutar pruebas mediante la terminal.
- Lo primero es modificar el .pom.xml
- Añadimos maven-surefire-plugin a los plugins (no en las dependencias)
- Desde el ciclo de vida de maven podemos ejecutar "test"
- En la terminal nos saldrán todos los resultados de los tests. Podemos añadir dentro del plugin <code> < groups >nombreEtiqueta< /groups> </code> para establecer qué etiquetas queremos ejecutar.
- Para poder ver el reporte de las pruebas podemos ir a la carpeta *target -> surfire-reports*


[account-entity-test-L53]:https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L53
[conditional-examples-test-L113]: https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L113


