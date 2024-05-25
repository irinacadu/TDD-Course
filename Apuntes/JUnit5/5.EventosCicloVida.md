## Eventos del ciclo de vida
- **@BeforeAll**: Se ejecuta una sola vez al principio nuestra prueba unitaria. Crea un método estático el cual pertenece a la clase y no a la instancia. Sin embargo, si no hay intancia va a ignorar este método y no lo va a ejecutar.
    * Ej. ver  [Account entity test L94][account-entity-test-L94]
- **@BeforeEach**: Se va a ejecutar al principio de cada método. Este evento por ejemplo, puede crear una nueva instancia en cada método evitando así la dependencia.
    * Ej. ver  [Account entity test 80][account-entity-test-L80]
- **@AfterEach**: Se va a ejecutar al final de cada método.
    * Ej. ver  [Account entity test L90][account-entity-test-L90]
- **@AfterAll**: Se ejecuta una sola vez al final de nuestra prueba unitaria. También crea un método estático
    * Ej. ver  [Account entity test L100][account-entity-test-L100]
- **@TestInstance**: Podemos establecer como queremos que se ejecute el ciclo de vida del test ya sea por método o por instancia. Esta última no se recomienda ya que, como decíamos, no es buena práctica utilizar una instancia global para todo el test.
    * Ej. ver  [Account entity test L12][account-entity-test-L12]

[account-entity-test-L80]:https://github.com/irinacadu/TDD-Course/blob/463ad1da5012fe0478193b52216263abecec1d53/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L80
[account-entity-test-L90]:https://github.com/irinacadu/TDD-Course/blob/463ad1da5012fe0478193b52216263abecec1d53/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L90
[account-entity-test-L94]: https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L94
[account-entity-test-L100]:https://github.com/irinacadu/TDD-Course/blob/133e856b7c03dbe2b2b287fe77216b839fe773c5/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L100
[account-entity-test-L12]:https://github.com/irinacadu/TDD-Course/blob/463ad1da5012fe0478193b52216263abecec1d53/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L12
