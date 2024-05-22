# Apuntes curso TDD

## Contenido 
1. [Información general](#informacion-general)
2. [Assertions](#assertions)
3. [Tests unitarios](#tests-unitarios)
4. [Conceptos TDD](#conceptos-tdd)
5. [Manejo de excepciones](#manejo-de-excepciones)
6. [Anotaciones JUnit](#anotaciones-junit)



### Información general
- Inicialmente los tests no tienen un orden al ejecutar una clase de principo a fin. Si queremos un orden en la ejecución de los tests usaremos:
- En testing se suelen utilizar los guiones bajos para separar las palabras de las nomenclaturas de los métodos. 
  * Ej. *test_account_name()*.
- El tipo *BigDecimal* es inmutable esto quiere decir que cualquier operación que se realice sobre un objeto *BigDecimal* devolverá un nuevo objeto del mismo tipo en lugar de modificar el existente.
- El tipo BigDecimal se compara con *compareTo()*
- Una de las características de JUnit Jupiter es que soporta expresiones lambda.
- Utilizando *fail();* al principio del test vamos a provocar el error del test entero. Es un método estático de la clase Assertions.
   * Ver Ej. [Account entity test L53][account-entity-test-L53]

### Assertions
- Como último argumento, podemos poner a cada uno de los asserts un mensaje de error para clarificar el fallo. Si el test no falla el error no aparecerá. Si utilizamos una expresión Lambda antes del mensaje
  *()-> "cuerpo del mensaje"* en vez de pasar la instancia del objeto String se instanciará , solo, si salta el error. Esto mejora la eficiencia.
   * Ej. ver [Bank methods test L57][bank-methods-test-L57]
- **assertEquals**: compara por referencia y no por valor a no ser que sobreescribamos el método "*Equals*" de la Entidad que estamos testeando. 
   * Ej. ver [Account entity testL25][account-entity-L25].
- **assertNotEquals**: funcionaigual que el assertEquals pero comprobando que las dos instancias no sean iguales.
   * Ej. ver [Account entity test L43][account-entity-test-L43]
- **assertTrue**: compara por valor literal. Si por ejemplo, comparamos dos cadenas iguales pero una de ellas está utilizando el *toUppercase()* ese test no será válido. 
   * Ej. ver [Account entity test L22][account-entity-test-L22].
- **assertFalse**: compara por valor literal.
   * Ej. ver [Account entity test L34][account-entity-test-L34].
- **assertNotNull**: comprueba que el valor no está vacío.
   * Ej. ver [Account methods test L18][account-methods-test-L18].
- [**assertThrows**](#manejo-de-excepciones)
- **assertAll**: Crea una agrupación de asserts. Cada test fallido se mostrará en consola pero el test no se parará y podremos ver los resultados de todas las comprobaciones agrupadas.
   * Ej. ver [Bank methods test L57][bank-methods-test-L57]



### Tests unitarios 
- Tenemos que tener en cuenta que por cada tests se va a crear una instancia nueva es decir, el valor que utilicemos en el test solo funcionará para ese test.
- Los tests unitarios prueban, solamente, si un método concreto funciona pero no comprueba si es compatible con todas las demás.

### Conceptos TDD
- Primero se crean las pruebas y luego se crea el código.

### Manejo de excepciones
- Para excepciones, es común definir los constructores manualmente para poder enviar mensajes a la superclase.
   * Ej. Ver [Insufficient money exception L12][insufficient-money-exception-L12].
- **assertThrows**: se utiliza para manejar excepciones. Llamada a la excepción:
    <code>assertThrows (NombreClaseExcpción.class,()->{resto de código que queremos testear})</code>
   * Ej. Ver [Account methods test L41][account-methods-test-L41];
    
### Anotaciones JUnit
- **@Test**: si no se utiliza esta anotación el programa no entenderá que la función es un test.
- **@DisplayName**: Con esta anotación se puede añadir una descripción al test.
   * Ej ver [Bank methods test L40][bank-methods-test-L40]
- **@Disabled**: para deshabilitar un test concreto por si queremos que no se ejecute.
   * Ej ver [Account entity test L53][account-entyty-test-L53]



[account-entity-L25]: https://github.com/irinacadu/TDD-Course/blob/1c67331cc3952452c4dc9148d7a75f9626febf2e/src/main/java/tddCourse/tdd/Entities/Account.java#L25
[account-entity-test-L22]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L22
[account-entity-test-L34]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L34
[account-entity-test-L43]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L43
[account-entity-test-L53]:https://github.com/irinacadu/TDD-Course/blob/7ed3ffc7cd1db2a334db6e14e791556e75c2558e/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L53
[account-entyty-test-L53]:https://github.com/irinacadu/TDD-Course/blob/7ed3ffc7cd1db2a334db6e14e791556e75c2558e/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L53
[account-methods-test-L18]:https://github.com/irinacadu/TDD-Course/blob/1c67331cc3952452c4dc9148d7a75f9626febf2e/src/test/java/tddCourse/tdd/AccountMethods/AccountMethodsTest.java#L18
[insufficient-money-exception-L12]:https://github.com/irinacadu/TDD-Course/blob/3189652547adebbae4f378dd92a15d479a266113/src/test/java/tddCourse/tdd/Exceptions/InsufficientMoneyException.java#L12
[account-methods-test-L41]:https://github.com/irinacadu/TDD-Course/blob/3189652547adebbae4f378dd92a15d479a266113/src/test/java/tddCourse/tdd/AccountMethods/AccountMethodsTest.java#L41
[bank-methods-test-L57]:https://github.com/irinacadu/TDD-Course/blob/ebbb87b0c61c28ce568c35ed125fa9954f49c9cb/src/test/java/tddCourse/tdd/TransactionsMethods/BankMethodsTest.java#L57
[bank-methods-test-L40]:https://github.com/irinacadu/TDD-Course/blob/a60ab82f17dad03b56a07f427d87f958e4ee95d8/src/test/java/tddCourse/tdd/TransactionsMethods/BankMethodsTest.java#L40