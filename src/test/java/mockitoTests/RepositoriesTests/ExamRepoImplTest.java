package mockitoTests.RepositoriesTests;

import mockito.Entities.Exam;
import mockito.Services.ExamService;
import mockito.Services.ExamServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExamRepoImplTest {

    @Test
    @DisplayName("Comprueba que el examen que buscamos existe")
    void find_exam_by_name(){
        ExamRepo examRepo = Mockito.mock(ExamRepo.class);
        ExamService examService = new ExamServiceImpl(examRepo);
        Exam exam = examService.findExamByName("Matemáticas");

      List<Exam> exams = Arrays.asList(
                Exam.builder()
                        .id(5L)
                        .name("Matemáticas")
                        .build(),
                Exam.builder()
                        .id(6L)
                        .name("Lengua")
                        .build(),
                Exam.builder()
                        .id(7L)
                        .name("Hisoria")
                        .build()
        );



        assertNotNull(exam);
        assertEquals(5L, exam.getId());
        assertEquals("Matemáticas",exam.getName());


    }

}