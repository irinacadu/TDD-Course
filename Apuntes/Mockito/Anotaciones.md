## Anotaciones
- Para habilitar las anotaciones de Mockito en el método de inicialización de tests utilizaremos *MockitoAnnotations.openMocks(this)*
    * Ej.Ver [Exam service. impl.test L101][exam-service-impl-test-L101]
- También se pueden habilitar anotando nuestra clase con @ExtendWith(MockitoExtension.class) 
    * Ej.Ver [Exam service. impl.test L27][exam-service-impl-test-L25]
- **@Mock**: Utilizamos esta anotación para que en vez de crearlo nosotros se cree automáticamente.
    * Ej.Ver [Exam service. impl.test L25][exam-service-impl-test-L25]
- **@InjectMock**: Utilizamos esta anotación para que los mocks se inyectén automáticamente. La inyección no funcionará con interfaces.
    * Ej.Ver [Exam service. impl.test L31][exam-service-impl-test-L31]
- **@Spy**: a parte de utilizar el método spy descrito en el archivo MetodosMockito.md, podemos crear un spy automáticamente con esta anotación.

[exam-service-impl-test-L27]: https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/ExamServiceImplTest.java#L27
[exam-service-impl-test-L31]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/ExamServiceImplTest.java#L31
[exam-service-impl-test-L101]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/ExamServiceImplTest.java#L101
[exam-service-impl-test-L25]:https://github.com/irinacadu/TDD-Course/blob/97739bad76f701e03e730960385f6ce7626e911f/src/test/java/MockitoTests/ExamServiceImplTest.java#L25
[exam-service-impl-test-L26]:https://github.com/irinacadu/TDD-Course/blob/b408678ee1fa161f1eaeb198d6b34981a4c78f1e/src/test/java/MockitoTests/ExamServiceSpyTest.java#L26