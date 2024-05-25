## Testeando repositorios con Mockito
- Tenemos que tener en cuenta que un *mock* no es una instancia real, no llama a los métodos reales.
- Podemos simular el comportamiento de un repositorio creando un *mock*. 
    * Ej. Ver [Exam repo. impl. test L20][exam-repo-impl-test-L20]
- **when**: Se indica qué se va a invocar una simulación del repositorio. En este ejemplo tenemos la llamada al método *findAll()* de ExamRepo
    * Ej. Ver [Exam repo. impl. test L39][exam-repo-impl-test-L39]
- **.then...**: Será lo que queremos obtener. En este caso hemos creado un array de objetos del tipo Exam.
    * Ej. Ver [Exam repo. impl. test L40][exam-repo-impl-test-L40]
- **verify** : nos permite comprobar si se ha ejecutado un método de nuestro *mock*.


[exam-repo-impl-test-L20]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/RepositoriesTests/ExamRepoImplTest.java#L20
[exam-repo-impl-test-L39]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/RepositoriesTests/ExamRepoImplTest.java#L39
[exam-repo-impl-test-L40]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/RepositoriesTests/ExamRepoImplTest.java#L40
[exam-repo-impl-test-L]: