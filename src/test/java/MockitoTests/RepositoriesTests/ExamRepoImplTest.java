package MockitoTests.RepositoriesTests;

import Mockito.Data.ExamData;
import Mockito.Entities.Exam;
import Mockito.Repositories.ExamQuestionsRepository;
import Mockito.Repositories.ExamRepo;
import Mockito.Services.ExamService;
import Mockito.Services.ExamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ExamRepoImplTest {
   ExamRepo examRepo;
    ExamService examService;

    ExamQuestionsRepository examQuestionsRepository;
    @Test
    @DisplayName("Comprueba que el examen que buscamos existe")
    void find_exam_by_name(){

        when(examRepo.findAll())
                     .thenReturn(ExamData.EXAMS);

        Optional<Exam>exam = examService.findExamByName("Matemáticas");

        assertTrue(exam.isPresent());
        assertEquals(5L, exam.orElseThrow().getId());
        assertEquals("Matemáticas",exam.get().getName());
    }

    @Test
    @DisplayName("Comprueba que la lista de exámenes está vacía")
    void find_exam_empty_list(){
        List<Exam> exams = Collections.emptyList();

        when(examRepo.findAll())
                .thenReturn(exams);

        Optional<Exam>exam = examService.findExamByName("Matemáticas");

        assertFalse(exam.isPresent());
    }

    @Test
    @DisplayName("Comprueba cuantas preguntas tiene el examen")
    void exam_questions_test() {
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
        when(examQuestionsRepository.findQuestionsByExamId(5L)).thenReturn(ExamData.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("Matemáticas");
        assertEquals(4, exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("lengua"));

    }

    @Test
    @DisplayName("Comprueba si se ejecuta la función")
    void exam_questions_verify_test() {
        when(examRepo.findAll()).thenReturn(ExamData.EXAMS);
        when(examQuestionsRepository.findQuestionsByExamId(5L)).thenReturn(ExamData.QUESTIONS);
        Exam exam = examService.findQuestionsByExamName("Matemáticas");

        verify(examRepo).findAll();
        verify(examQuestionsRepository).findQuestionsByExamId(5L);

    }

    @BeforeEach
    void setUp(){
       examRepo = mock(ExamRepo.class);
       examQuestionsRepository = mock(ExamQuestionsRepository.class);
       examService = new ExamServiceImpl(examRepo,examQuestionsRepository);
    }



}