# Apuntes curso TDD
- [Información general](#informacion-general)
- [Tests unitarios](#tests-unitarios)
- [Conceptos TDD](#conceptos-tdd)
- [Manejo de excepciones](#manejo-de-excepciones)
- [Ejecución de tests en clases anidadas](#organización-de-tests-en-clases-anidadas-nested-class)


## Información general
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

## Conceptos TDD
- Primero se crean las pruebas y luego se crea el código.

## Manejo de excepciones
- Para excepciones, es común definir los constructores manualmente para poder enviar mensajes a la superclase.
   * Ej. Ver [Insufficient money exception L12][insufficient-money-exception-L12].
- **assertThrows**: se utiliza para manejar excepciones. Llamada a la excepción:
    <code>assertThrows (NombreClaseExcepción.class,()->{resto de código que queremos testear})</code>
   * Ej. Ver [Account methods test L41][account-methods-test-L41];

## Ejecución de tests en clases anidadas (nested class)
- Con la anotación **@Nested** indicamos que la clase es anidada. Para que las clases anidadas se ejecuten en los tests deben ser static.




[account-entity-test-L53]:https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L53
[conditional-examples-test-L113]: https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L113
[insufficient-money-exception-L12]:https://github.com/irinacadu/TDD-Course/blob/3189652547adebbae4f378dd92a15d479a266113/src/test/java/tddCourse/tdd/Exceptions/InsufficientMoneyException.java#L12
[account-methods-test-L41]:https://github.com/irinacadu/TDD-Course/blob/3189652547adebbae4f378dd92a15d479a266113/src/test/java/tddCourse/tdd/AccountMethods/AccountMethodsTest.java#L41


