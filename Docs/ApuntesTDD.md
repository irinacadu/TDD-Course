# Wiki del proyecto
## Contenido
   - [Información general](#informacion-general)
   - [Assertions](#assertions)
   - [Tests unitarios](#tests-unitarios)



## Información general

- Inicialmente los tests no tienen un orden al ejecutar una clase de principo a fin. Si queremos un orden en la ejecución de los tests usaremos:
- En testing se suelen utilizar los guiones bajos para separar las palabras de las nomenclaturas de los métodos. Ej. ver [AccountTest][account-test]#L15

## Assertions
- assertEquals va a comparar siempre por instancia y no por valor a no ser que sobreescribamos el método Equals de la Entidad que estamos testeando. Ej. ver [Account entity][account-entity]#L25
- assertTrue va a comparar por valor literal. Si por ejemplo, comparamos dos cadenas iguales pero una de ellas está utilizando el toUppercase() ese test no será válido. Ej. ver [test_account_name][account-test]#L15

## Tests unitarios
- Tenemos que tener en cuenta que por cada tests se va a crear una instancia nueva es decir, el valor que utilicemos en el test solo funcionará para ese test.



[account-entity]: ../src/main/java/tddCourse/tdd/Entities/Account.java
[account-entity-test]: ../src/test/java/tddCourse/tdd/Entities/AccountTest.java
[account-methods]: ../src/main/java/tddCourse/tdd/AccountMethods/AccountMethods.java
[account-methods-test]: ../src/test/java/tddCourse/tdd/AccountMethods/AccountMethodsTest.java
