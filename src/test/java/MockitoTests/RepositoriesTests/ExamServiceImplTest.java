package MockitoTests.RepositoriesTests;

import GeneralResources.ArgsMatchers.PersonalizedArgsMatchers;
import GeneralResources.Data.ExamData;
import Mockito.Entities.Exam;
import Mockito.Repositories.ExamQuestionsRepository;
import Mockito.Repositories.ExamQuestionsRepositoryImpl;
import Mockito.Repositories.ExamRepo;
import Mockito.Repositories.ExamRepoImpl;
import Mockito.Services.ExamService;
import Mockito.Services.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {
    @Mock
    ExamRepoImpl examRepo;
    @Mock
    ExamQuestionsRepositoryImpl examQuestionsRepository;
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
    @Test
    void argument_captor_test(){
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
        when(examQuestionsRepository.findQuestionsByExamId(anyLong())).thenReturn(ExamData.QUESTIONS);

        examService.findQuestionsByExamName("Matemáticas");

        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);

        verify(examQuestionsRepository).findQuestionsByExamId(captor.capture());

        assertEquals(5l, captor.getValue());

    }

    @Test
    void do_throw_test(){
        Exam exam = ExamData.EXAM;
        exam.setQuestions(ExamData.QUESTIONS);

        doThrow(IllegalArgumentException.class).when(examQuestionsRepository).saveQuestions(anyList());

        assertThrows(IllegalArgumentException.class,()->{
            examService.save(exam);
        });

    }

    @Test
    void do_answer_test(){
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
//        when(examQuestionsRepository.findQuestionsByExamId(anyLong())).thenReturn(ExamData.QUESTIONS);
        doAnswer(invocation ->{
            Long idExam = invocation.getArgument(0);
            return idExam == 5L? ExamData.QUESTIONS:null;
        }).when(examQuestionsRepository).findQuestionsByExamId(anyLong());

        Exam exam = examService.findQuestionsByExamName("Matemáticas");
        assertEquals(5L, exam.getId());
        assertEquals("Matemáticas", exam.getName());

    }

    @Test
    void do_call_real_method_test(){
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
//        when(examQuestionsRepository.findQuestionsByExamId(anyLong())).thenReturn(ExamData.QUESTIONS);
        doCallRealMethod().when(examQuestionsRepository).findQuestionsByExamId(anyLong());
        Exam exam = examService.findQuestionsByExamName("Matemáticas");

        assertEquals(5L, exam.getId());
        assertEquals("Matemáticas", exam.getName());

    }
    @Test
    void spy_test(){
        ExamRepo examRepo = spy(ExamRepoImpl.class);
        ExamQuestionsRepository examQuestionsRepository1 = spy(ExamQuestionsRepositoryImpl.class);
        //No lo podemos utilizar con spy -> when(examQuestionsRepository1.findQuestionsByExamId(anyLong())).thenReturn(ExamData.EXAM.getQuestions());
        List <String> questions = ExamData.QUESTIONS;
        doReturn(questions).when(examQuestionsRepository1).findQuestionsByExamId(anyLong());

        ExamService examService1 = new ExamServiceImpl(examRepo,examQuestionsRepository1);

        Exam exam = examService1.findQuestionsByExamName("Matemáticas");
        assertEquals(5,exam.getId());
        assertEquals("Matemáticas",exam.getName());
        assertEquals(4,exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("Geometría"));

        verify(examRepo).findAll();
        verify(examQuestionsRepository1).findQuestionsByExamId(anyLong());

    }
    /**
     * Ejemplo de como sería la creación e inyección de mocks sin utilizar las anotaciones @Mock e @InjectMocks
     */
    @BeforeEach
    void setUp() {
      /* examRepo = mock(ExamRepoImpl.class);
       examQuestionsRepository = mock(ExamQuestionsRepositoryImpl.class);
       examService = new ExamServiceImpl(examRepo,examQuestionsRepository);*/
    // MockitoAnnotations.openMocks(this);
    }


}