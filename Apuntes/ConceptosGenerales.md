# Apuntes curso TDD
- [Información general](#informacion-general)
- [Tests unitarios](#tests-unitarios)
- [Ejecución de tests en clases anidadas](#organización-de-tests-en-clases-anidadas-nested-class)
- [Maven surefire](#maven-surefire)
- [Ejemplos completos]


## Información general
- Para trabajar en TDD primero se crean las pruebas y luego se crea el código.
- Inicialmente, los tests no tienen un orden al ejecutar una clase de principo a fin. Si queremos un orden en la ejecución de los tests usaremos:
- En testing se suelen utilizar los guiones bajos para separar las palabras de las nomenclaturas de los métodos. 
  * Ej. *test_account_name()*.
- El tipo *BigDecimal* es inmutable esto quiere decir que cualquier operación que se realice sobre un objeto *BigDecimal* devolverá un nuevo objeto del mismo tipo en lugar de modificar el existente.
- El tipo BigDecimal se compara con *compareTo()*
- Una de las características de JUnit Jupiter es que soporta expresiones lambda.
- Utilizando *fail();* al principio del test vamos a provocar el error del test entero. Es un método estático de la clase Assertions.
   * Ver Ej. [Account entity test L64][account-entity-test-L64]
- Para poder testear en entornos concretos [Conditional examples test L14][conditional-examples-test-L14]
- Podemos deshabilitar la traza de los logs pero solo en los test. Para eso en test crearemos un directorio resources donde crearemos un archivo [application.properties].
- Si ejecutamos los test desde la carpeta raíz de *test -> botón derecho -> More Run/Debug -> "Run [...] with coverage"* podremos ver los porcentajes de cobertura de nuestros tests con respecto a nuestro proyecto.
- Si tenemos varios test de integración pueden causar conflictos. Asi que solo podemos tener una prueba de integración y si hay varias tenemos que utilizar etiquetas para excluir a un grupo editándolas en la configuración.
- Para pruebas de integración de aplicaciones reactivas utilizamos webflux.


## Tests unitarios 
- Tenemos que tener en cuenta que por cada test se va a crear una instancia nueva es decir, el valor que utilicemos en el test solo funcionará para ese test.
- Los tests unitarios prueban, solamente, si un método concreto funciona. No comprueba si es compatible con todas las demás.
- Hay que procurar que cada uno de loes tests estén lo más desacoplados posible entre ellos.
- Los tests se tienen que volver a probar constantemente.
- Podemos estructurar los tests resolviendo los siguientes:
    * **GIVEN**: Describe el estado antes de empezar el comportamiento que se está especificando en el escenario. Condiciones previas a la prueba.
    * **WHEN**: Es el comportamiento que se especifica en la prueba.
    * **THEN**: Son los comportamientos que se esperan debidos al WHEN.

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

## Ejemplos completos
- Ejemplo de clase con tests [sin anotaciones][JUnit-mockito-project-test]

## JPA

- [Documentación JPA] [Documentación JPA]
- Es una interfaz genérica que ya posee métodos propios cono findAll, delete, findByID...
- Nuestras clases repositorio heredan de estas clases. 
- Las consultas que se pueden realizar no tienen porque ser nativas, es decir, no tienen porque ir a consultar a una tabla si no que la consulta se hace al objeto. Para esto se utiliza la anotación @Query.
- CRUDRepository es más abstracto. JPARepository implementa ésta interfaz y añade más funcionalidades.


[account-entity-test-L64]:https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L64
[conditional-examples-test-L14]: https://github.com/irinacadu/TDD-Course/blob/0e0866d9bc86724c85402cef112c943bb3f1a505/src/test/java/JUnit/ConditionalTestsExamples/ConditionalTestsExamples.java#L14
[JUnit-mockito-project-test]:https://github.com/irinacadu/TDD-Course/blob/3d4384d4ee195f4c6b24c2fecc3db76d3f034023/src/test/java/JUnitMockitoProject/JUnitMockitoProjectTest.java#L20
[Documentación JPA]: https://spring.io/projects/spring-data


