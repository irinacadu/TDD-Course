## Condicionales
- Se pueden condicionar las pruebas para que se ejecuten según el escenario dado como por ejemplo el tipo de SO o la versión de java
- **@EnabledOn...**: Habilita la prueba solo en la condición establecida en la anotación.
    * Ej. ver  [Conditional examples test L18][conditional-examples-test-L18]
- **@DisabledOn...**: Deshabilita la prueba solo en la condición establecida en la anotación
- **@DisabledOn...**: Deshabilita la prueba solo en la condición establecida en la anotación
    * Ej. ver [Conditional examples test L32][conditional-examples-test-L32]
- **@EnabledIfSystemProperties**: Test que se ejecutará solo si la condición relacionada con las propiedades del sistema entre paréntesis se cumple. El *"matches"* de esta anotación soporta expresiones regulares.
    * Ej. ver [Conditional examples test L64][conditional-examples-test-L64]
- **@EnabledIfEnvironmentVariables**: Test que se ejecutará solo si la condición relacionada con las variables del sistema entre paréntesis se cumple. El *"matches"* de esta anotación soporta expresiones regulares.
    * Ej. ver [Conditional examples test L100][conditional-examples-test-L100]



[conditional-examples-test-L18]: https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L18
[conditional-examples-test-L32]:https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L32
[conditional-examples-test-L64]:https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L64
[conditional-examples-test-L100]:https://github.com/irinacadu/TDD-Course/blob/f247291c1ab3de81a49736c769c177bf33651093/src/test/java/tddCourse/tdd/ConditionalTestsExamples/ConditionalTestsExamples.java#L100