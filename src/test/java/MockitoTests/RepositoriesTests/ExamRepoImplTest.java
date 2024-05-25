package MockitoTests.RepositoriesTests;

import GeneralTestResources.ArgsMatchers.PersonalizedArgsMatchers;
import GeneralTestResources.Data.ExamData;
import Mockito.Entities.Exam;
import Mockito.Repositories.ExamQuestionsRepository;
import Mockito.Repositories.ExamRepo;
import Mockito.Services.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExamRepoImplTest {
    @Mock
    ExamRepo examRepo;
    @Mock
    ExamQuestionsRepository examQuestionsRepository;
    @InjectMocks
    ExamServiceImpl examService;

    @Test
    @DisplayName("Comprueba que el examen que buscamos existe")
    void find_exam_by_name() {

        when(examRepo.findAll())
                .thenReturn(ExamData.EXAMS);

        Optional<Exam> exam = examService.findExamByName("Matemáticas");

        assertTrue(exam.isPresent());
        assertEquals(5L, exam.orElseThrow().getId());
        assertEquals("Matemáticas", exam.get().getName());
    }

    @Test
    @DisplayName("Comprueba que la lista de exámenes está vacía")
    void find_exam_empty_list() {
        List<Exam> exams = Collections.emptyList();

        when(examRepo.findAll())
                .thenReturn(exams);

        Optional<Exam> exam = examService.findExamByName("Matemáticas");

        assertFalse(exam.isPresent());
    }

    @Test
    @DisplayName("Comprueba cuantas preguntas tiene el examen")
    void exam_questions_test() {
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
        when(examQuestionsRepository.findQuestionsByExamId(5L)).thenReturn(ExamData.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("Matemáticas");
        assertEquals(4, exam.getQuestions().size());
//      assertTrue(exam.getQuestions().contains("lengua"));

    }

    @Test
    @DisplayName("Comprueba si se ejecuta la función")
    void exam_questions_verify_test() {
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
        when(examQuestionsRepository.findQuestionsByExamId(5L)).thenReturn(ExamData.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("Matemáticas");
        assertEquals(4, exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("Geometría"));
        verify(examRepo).findAll();
        verify(examQuestionsRepository).findQuestionsByExamId(5L);
    }

    @Test
    @DisplayName("Comprueba si la lista de exámenes está vacía")
    void exam_verify_test() {
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
        when(examQuestionsRepository.findQuestionsByExamId(5L)).thenReturn(ExamData.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("Ética");
        assertNull(exam);
        verify(examRepo).findAll();
        verify(examQuestionsRepository).findQuestionsByExamId(5L);
    }
    @Test
    @DisplayName("Comprueba si el examen se guarda")
    void save_exam_test() {
        Exam exam = ExamData.EXAM;
        exam.setQuestions(ExamData.QUESTIONS);

        when(examRepo.save(exam)).then(new Answer(){

            Long sequence  = 8L;
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Exam examAnonymous = invocationOnMock.getArgument(0);
                examAnonymous.setId(sequence++);
                return examAnonymous;
            }
        });
        examService.save(ExamData.EXAM);

        assertNotNull(exam.getId());
        assertEquals(8L,exam.getId());
        assertEquals("Física",exam.getName());

        verify(examRepo).save(exam);
        verify(examQuestionsRepository).saveQuestions(anyList());

    }

    @Test
    void excepcion_handling_test(){
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS_ID_NULL);
        when(examQuestionsRepository.findQuestionsByExamId(isNull())).thenThrow(IllegalArgumentException.class);

        Exception exception =  assertThrows(IllegalArgumentException.class, ()->{
           examService.findQuestionsByExamName("Sociales");
        });
        assertEquals(IllegalArgumentException.class, exception.getClass());

    }

    @Test
    void argument_matchers_test(){
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
        when(examQuestionsRepository.findQuestionsByExamId(anyLong())).thenReturn(ExamData.QUESTIONS);


        examService.findQuestionsByExamName("Matemáticas");

        verify(examRepo).findAll();
        verify(examQuestionsRepository).findQuestionsByExamId(argThat(arg->arg.equals(5L)));

    }

    @Test
    void personalized_argument_matchers_test(){
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS_ID_NULL);
        when(examQuestionsRepository.findQuestionsByExamId(isNull())).thenReturn(ExamData.QUESTIONS);


        examService.findQuestionsByExamName("Naturales");

        verify(examRepo).findAll();
        verify(examQuestionsRepository).findQuestionsByExamId(argThat(new PersonalizedArgsMatchers()));

    }
    /**
     * Ejemplo de como sería la creación e inyección de mocks sin utilizar las anotaciones @Mock e @InjectMocks
     */
    @BeforeEach
    void setUp() {
      /* examRepo = mock(ExamRepo.class);
       examQuestionsRepository = mock(ExamQuestionsRepository.class);
       examService = new ExamServiceImpl(examRepo,examQuestionsRepository);*/
    // MockitoAnnotations.openMocks(this);
    }


}