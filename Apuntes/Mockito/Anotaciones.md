## Anotaciones
- Para habilitar las anotaciones de Mockito en el método de inicialización de tests utilizaremos *MockitoAnnotations.openMocks(this)*
    * Ej.Ver [Exam repo. impl.test L101][exam-repo-impl-test-L101]
- También se pueden habilitar anotando nuestra clase con @ExtendWith(MockitoExtension.class) 
    * Ej.Ver [Exam repo. impl.test L27][exam-repo-impl-test-L25]
- **@Mock**: Utilizamos esta anotación para que en vez de crearlo nosotros se cree automáticamente.
    * Ej.Ver [Exam repo. impl.test L25][exam-repo-impl-test-L25]
- **@InjectMock**: Utilizamos esta anotación para que los mocks se inyectén automáticamente. La inyección no funcionará con interfaces.
    ** Ej.Ver [Exam repo. impl.test L31][exam-repo-impl-test-L31]

[exam-repo-impl-test-L27]: https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L27
[exam-repo-impl-test-L31]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L31
[exam-repo-impl-test-L101]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L101
[exam-repo-impl-test-L25]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/RepositoriesTests/ExamRepoImplTest.java#L25