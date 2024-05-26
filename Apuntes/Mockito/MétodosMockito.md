## Métodos Mockito
- Tenemos que tener en cuenta que un *mock* no es una instancia real, no llama a los métodos reales.
- Podemos simular el comportamiento de un repositorio creando un *mock*. 
    * Ej. Ver [Exam repo. impl. test L98][exam-repo-impl-test-L98]
- **when**: Se indica qué se va a invocar una simulación del repositorio. Le pasamos por parámetro nuestro mock. En este ejemplo tenemos la llamada al método *findAll()* de ExamRepo
    * Ej. Ver [Exam repo. impl. test L39][exam-repo-impl-test-L39]
- **.then...**: Será lo que queremos obtener. En este caso hemos creado un array de objetos del tipo Exam.
    * Ej. Ver [Exam repo. impl. test L40][exam-repo-impl-test-L40]
- **verify** : nos permite comprobar si se ha ejecutado un método de nuestro *mock*.
    * Ej. Ver [Exam repo. impl. test L72][exam-repo-impl-test-L72]
- **doCallRealMethod**: Para llamar a los métodos reales. No funciona con interfaces ya que sus métodos son abstractos y no están implementados .

[exam-repo-impl-test-L98]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/RepositoriesTests/ExamRepoImplTest.java#L98
[exam-repo-impl-test-L35]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/RepositoriesTests/ExamRepoImplTest.java#L35
[exam-repo-impl-test-L36]:https://github.com/irinacadu/TDD-Course/blob/4cd7567f2b553785fe7cc10b12f7ba0b7b8dfb1a/src/test/java/mockitoTests/RepositoriesTests/ExamRepoImplTest.java#L36
[exam-repo-impl-test-L72]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L72
[exam-repo-impl-test-L]: 