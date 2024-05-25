## Assumptions
- Se aplican a un código, de forma programática, dentro de un método
- Todo lo que está debajo del **assumeTrue** o **assumeFalse**   se ejecuta o no se ejecuta si se cumple la condición booleanda especificada pero no fallará.
    * Ej. Ver [Account entity test L97] [account-entity-test-L77]
- **assumingThat** se pasa un valor si se cumple se pasa la expresión lambda que contendrá el código que queremos que se ejecute si la asunción es cierta.
    * Ej. Ver [Account entity test L98] [account-entity-test-L98]

[account-entity-test-L97]:https://github.com/irinacadu/TDD-Course/blob/64429de3712dcc19faa8b3bc0c74c9b13097bb5a/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L97
[account-entity-test-L98]:https://github.com/irinacadu/TDD-Course/blob/64429de3712dcc19faa8b3bc0c74c9b13097bb5a/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L98