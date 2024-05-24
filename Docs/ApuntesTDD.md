# Apuntes curso TDD

## Contenido 
1. [Información general](#informacion-general)
2. [Ciclo de vida](#ciclo-de-vida)
3. [Assertions](#assertions)
4. [Tests unitarios](#tests-unitarios)
5. [Conceptos TDD](#conceptos-tdd)
6. [Manejo de excepciones](#manejo-de-excepciones)
7. [Anotaciones JUnit](#anotaciones-junit)
8. [Eventos del ciclo de vida](#eventos-del-ciclo-de-vida)
9. [Condicionales](#condicionales)
10. [Asunciones](#asunciones)



### Información general
- Inicialmente los tests no tienen un orden al ejecutar una clase de principo a fin. Si queremos un orden en la ejecución de los tests usaremos:
- En testing se suelen utilizar los guiones bajos para separar las palabras de las nomenclaturas de los métodos. 
  * Ej. *test_account_name()*.
- El tipo *BigDecimal* es inmutable esto quiere decir que cualquier operación que se realice sobre un objeto *BigDecimal* devolverá un nuevo objeto del mismo tipo en lugar de modificar el existente.
- El tipo BigDecimal se compara con *compareTo()*
- Una de las características de JUnit Jupiter es que soporta expresiones lambda.
- Utilizando *fail();* al principio del test vamos a provocar el error del test entero. Es un método estático de la clase Assertions.
   * Ver Ej. [Account entity test L53][account-entity-test-L53]
- Para poder testear en entornos concretos [Conditional examples test L105][conditional-examples-test-L105]


### Ciclo de vida
- Proceso en el cual se crea una instancia, se administra y se destruye. Lo solemos hacer con new
- JUnit crea el ciclo de vida de la instancia.
- Con JUnit podemos ejecutar una pieza de código espécifico en cualquier momento del ciclo de vida. Esto lo hace con los [eventos](#eventos-del-ciclo-de-vida)
- Los métodos de una clase de test también forman parte del ciclo de vida.
- Por cada método se crea una nueva instancia de la clase. El orden de ejecución de los métodos es aleatorio y no hay garantía de cual se va a ejecutar primero.
- Se puede crear una instancia sola para toda la clase.
- Se puede establecer unas normas de ordenación de los métodos de las clases de test.
- No es una buena parte realizar ordenación de métodos dependientes de otros ya que se puede cambiar el comportamiento de los tests y no obtener los resultaos esperados.
- Se pueden ordenar de manera visual pero no para crear dependencias.


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
- Tenemos que tener en cuenta que por cada test se va a crear una instancia nueva es decir, el valor que utilicemos en el test solo funcionará para ese test.
- Los tests unitarios prueban, solamente, si un método concreto funciona. No comprueba si es compatible con todas las demás.
- Hay que procurar que cada uno de loes tests estén lo más desacoplados posible entre ellos.

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
   * Ej ver [Account entity test L53][account-entity-test-L53]

 ### Eventos del ciclo de vida
- **@BeforeAll**: Se ejecuta una sola vez al principio nuestra prueba unitaria. Crea un método estático el cual pertenece a la clase y no a la instancia. Sin embargo, si no hay intancia va a ignorar este método y no lo va a ejecutar.
   * Ej. ver  [Account entity test L36][account-entity-test-L36]
- **@BeforeEach**: Se va a ejecutar al principio de cada método. Este evento por ejemplo, puede crear una nueva instancia en cada método evitando así la dependencia.
   * Ej. ver  [Account entity test L24][account-entity-test-L24]
- **@AfterEach**: Se va a ejecutar al final de cada método.
   * Ej. ver  [Account entity test L30][account-entity-test-L30]
- **@AfterAll**: Se ejecuta una sola vez al final de nuestra prueba unitaria.También crea un método estático
   * Ej. ver  [Account entity test L42][account-entity-test-L42]
- **@TestInstance**: Podemos establecer como queremos que se ejecute el ciclo de vida del test ya sea por método o por instancia. Esta última no se recomienda ya que, como decíamos, no es buena práctica utilizar una instancia global para todo el test.
   * Ej. ver  [Account entity test L13][account-entity-test-L13]

### Condicionales
- Se pueden condicionar las pruebas para que se ejecuten según el escenario dado como por ejemplo el tipo de SO o la versión de java
- **@EnabledOn...**: Habilita la prueba solo en la condición establecida en la anotación.
  * Ej. ver  [Conditional examples test L15][conditional-examples-test-L15]
- **@DisabledOn...**: Deshabilita la prueba solo en la condición establecida en la anotación
- **@DisabledOn...**: Deshabilita la prueba solo en la condición establecida en la anotación
  * Ej. ver [Conditional examples test L29][conditional-examples-test-L29]
- **@EnabledIfSystemProperties**: Test que se ejecutará solo si la condición relacionada con las propiedades del sistema entre paréntesis se cumple. El *"matches"* de esta anotación soporta expresiones regulares.
  * Ej. ver [Conditional examples test L58][conditional-examples-test-L58]
- **@EnabledIfEnvironmentVariables**: Test que se ejecutará solo si la condición relacionada con las variables del sistema entre paréntesis se cumple. El *"matches"* de esta anotación soporta expresiones regulares.
  * Ej. ver [Conditional examples test L58][conditional-examples-test-L58]

### Asunciones
- Se aplican a un código, de forma programática, dentro de un método
- 


[account-entity-L25]: https://github.com/irinacadu/TDD-Course/blob/1c67331cc3952452c4dc9148d7a75f9626febf2e/src/main/java/tddCourse/tdd/Entities/Account.java#L25
[account-entity-test-L22]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L22
[account-entity-test-L34]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L34
[account-entity-test-L43]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L43
[account-entity-test-L53]:https://github.com/irinacadu/TDD-Course/blob/7ed3ffc7cd1db2a334db6e14e791556e75c2558e/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L53
[account-entity-test-L24]:https://github.com/irinacadu/TDD-Course/blob/463ad1da5012fe0478193b52216263abecec1d53/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L24
[account-entity-test-L30]:https://github.com/irinacadu/TDD-Course/blob/463ad1da5012fe0478193b52216263abecec1d53/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L30
[account-entity-test-L36]: https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L36
[account-entity-test-L42]:https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L42
[account-entity-test-L13]:https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L13
[account-methods-test-L18]:https://github.com/irinacadu/TDD-Course/blob/1c67331cc3952452c4dc9148d7a75f9626febf2e/src/test/java/tddCourse/tdd/AccountMethods/AccountMethodsTest.java#L18
[insufficient-money-exception-L12]:https://github.com/irinacadu/TDD-Course/blob/3189652547adebbae4f378dd92a15d479a266113/src/test/java/tddCourse/tdd/Exceptions/InsufficientMoneyException.java#L12
[account-methods-test-L41]:https://github.com/irinacadu/TDD-Course/blob/3189652547adebbae4f378dd92a15d479a266113/src/test/java/tddCourse/tdd/AccountMethods/AccountMethodsTest.java#L41
[bank-methods-test-L57]:https://github.com/irinacadu/TDD-Course/blob/ebbb87b0c61c28ce568c35ed125fa9954f49c9cb/src/test/java/tddCourse/tdd/TransactionsMethods/BankMethodsTest.java#L57
[bank-methods-test-L40]:https://github.com/irinacadu/TDD-Course/blob/a60ab82f17dad03b56a07f427d87f958e4ee95d8/src/test/java/tddCourse/tdd/TransactionsMethods/BankMethodsTest.java#L40
[conditional-examples-test-L15]: https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L15
[conditional-examples-test-L29]:https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L29
[conditional-examples-test-L58]:https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L58
[conditional-examples-test-L66]:https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L66
[conditional-examples-test-L105]: https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L105