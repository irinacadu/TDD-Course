# Apuntes curso TDD

## Contenido 
1. [Información general](#informacion-general)
2. [Assertions](#assertions)
3. [Tests unitarios](#tests-unitarios)



### Información general
- Inicialmente los tests no tienen un orden al ejecutar una clase de principo a fin. Si queremos un orden en la ejecución de los tests usaremos:
- En testing se suelen utilizar los guiones bajos para separar las palabras de las nomenclaturas de los métodos. 
  * Ej. *test_account_name()*.

### Assertions
- **assertEquals**: compara por instancia y no por valor a no ser que sobreescribamos el método "*Equals*" de la Entidad que estamos testeando. 
   * Ej. ver [Account entity L25][account-entity-L25].
- **assertTrue**: compara por valor literal. Si por ejemplo, comparamos dos cadenas iguales pero una de ellas está utilizando el *toUppercase()* ese test no será válido. 
   * Ej. ver [Account entity test L22][account-entity-test-L22].
- **assertFalse**: compara por valor literal.
   * Ej. ver [Account entity test L34][account-entity-test-L34].


### Tests unitarios 
- Tenemos que tener en cuenta que por cada tests se va a crear una instancia nueva es decir, el valor que utilicemos en el test solo funcionará para ese test.



[account-entity-L25]: https://github.com/irinacadu/TDD-Course/blob/1c67331cc3952452c4dc9148d7a75f9626febf2e/src/main/java/tddCourse/tdd/Entities/Account.java#L25
[account-entity-test-L22]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L22
[account-entity-test-L34]: https://github.com/irinacadu/TDD-Course/blob/c81aa88ec3b839221f58b29aa03bd766f36b108f/src/test/java/tddCourse/tdd/Entities/AccountTest.java#L34

