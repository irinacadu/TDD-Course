## Métodos Mockito
- Tenemos que tener en cuenta que un *mock* no es una instancia real, no llama a los métodos reales.
- Podemos simular el comportamiento de un servicesitorio creando un *mock*. 
    * Ej. Ver [Exam service. impl. test L98][exam-service-impl-test-L98]
- **when**: Se indica qué se va a invocar una simulación del repositorio. Le pasamos por parámetro nuestro mock. En este ejemplo tenemos la llamada al método *findAll()* de Examservice
    * Ej. Ver [Exam service. impl. test L39][exam-service-impl-test-L39]
- **.then...**: Será lo que queremos obtener. En este caso hemos creado un array de objetos del tipo Exam.
    * Ej. Ver [Exam service. impl. test L40][exam-service-impl-test-L40]
- **verify** : nos permite comprobar si se ha ejecutado un método de nuestro *mock*.
    * Ej. Ver [Exam service. impl. test L72][exam-service-impl-test-L72]
- **doCallRealMethod**: Para llamar a los métodos reales. No funciona con interfaces ya que sus métodos no están implementados. No es recomendable utilizarlo excepto cuando no sea posible crear un *mock*.
    * Ej. Ver [Exam service. impl. test L210][exam-service-impl-test-L210]
- **spy**: son clones de objetos reales pero con características de los mocks. No tenemos el control 100%. No se puede crear desde una interface o clase abstracta. Aquí no 
podemos utilizar el when porque si no estaríamos llamando al método original. Cuando utilizamos spy debemos utilizar [**doReturn**][exam-service-impl-test-L224].
    * Ej. Ver [Exam service. impl. test L220][exam-service-impl-test-L220]

[exam-service-impl-test-L98]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/ExamServiceImplTest.java#L98
[exam-service-impl-test-L35]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/ExamServiceImplTest.java#L35
[exam-service-impl-test-L36]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/ExamServiceImplTest.java#L36
[exam-service-impl-test-L72]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/ExamServiceImplTest.java#L72
[exam-service-impl-test-L210]: https://github.com/irinacadu/TDD-Course/blob/016282e9ca5a6749a012ba4dbabede140aa1c9ea/src/test/java/MockitoTests/ExamServiceImplTest.java#L210
[exam-service-impl-test-L220]: https://github.com/irinacadu/TDD-Course/blob/016282e9ca5a6749a012ba4dbabede140aa1c9ea/src/test/java/MockitoTests/ExamServiceImplTest.java#L220
[exam-service-impl-test-L224]::https://github.com/irinacadu/TDD-Course/blob/016282e9ca5a6749a012ba4dbabede140aa1c9ea/src/test/java/MockitoTests/ExamServiceImplTest.java#L224