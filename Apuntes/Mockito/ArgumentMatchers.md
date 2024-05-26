## Argument Matchers
- Los [ArgumentMatchers] [exam-repo-impl-tests-L146] es una característica de mockito que permite saber si coincide el valor real que se pasa por argumento en un método y lo comparamos con los definidos en el mock.
- Podemos crear nuestros propios arguments. Ej. [PersonalizedArgsMatchers.java] [personalized-args-matcher-L6]
- También podemos capturar los argumentos con [ArgumentCaptor.class] [exam-repo-impl-tests-L168]
- **doAnswer**: establecemos una respuesta personalizada según el valor del argumento.
    * Ej ver [Exam repo. impl. tests L192][exam-repo-impl-tests-L192]

[exam-repo-impl-tests-L146]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L146
[personalized-args-matcher-L6]: https://github.com/irinacadu/TDD-Course/blob/0e0866d9bc86724c85402cef112c943bb3f1a505/src/test/java/GeneralTestResources/ArgsMatchers/PersonalizedArgsMatchers.java#L6
[exam-repo-impl-tests-L168]:https://github.com/irinacadu/TDD-Course/blob/0e0866d9bc86724c85402cef112c943bb3f1a505/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L168
[exam-repo-impl-tests-L192]:https://github.com/irinacadu/TDD-Course/blob/0e0866d9bc86724c85402cef112c943bb3f1a505/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L192
