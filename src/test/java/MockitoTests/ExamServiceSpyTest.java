package MockitoTests;

import GeneralResources.Data.ExamData;
import Mockito.Entities.Exam;
import Mockito.Repositories.ExamQuestionsRepository;
import Mockito.Repositories.ExamQuestionsRepositoryImpl;
import Mockito.Repositories.ExamRepo;
import Mockito.Repositories.ExamRepoImpl;
import Mockito.Services.ExamService;
import Mockito.Services.ExamServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ExamServiceSpyTest {

    @Spy
    ExamRepoImpl examRepo;
    @Spy
    ExamQuestionsRepositoryImpl examQuestionsRepository;
    @InjectMocks
    ExamServiceImpl examService;

    @Test
    void spy_test() {
        ExamRepo examRepo = spy(ExamRepoImpl.class);
        ExamQuestionsRepository examQuestionsRepository1 = spy(ExamQuestionsRepositoryImpl.class);
        //No lo podemos utilizar con spy -> when(examQuestionsRepository1.findQuestionsByExamId(anyLong())).thenReturn(ExamData.EXAM.getQuestions());
        List<String> questions = ExamData.QUESTIONS;
        doReturn(questions).when(examQuestionsRepository1).findQuestionsByExamId(anyLong());

        ExamService examService1 = new ExamServiceImpl(examRepo, examQuestionsRepository1);

        Exam exam = examService1.findQuestionsByExamName("Matemáticas");
        assertEquals(5, exam.getId());
        assertEquals("Matemáticas", exam.getName());
        assertEquals(4, exam.getQuestions().size());
        assertTrue(exam.getQuestions().contains("Geometría"));

        verify(examRepo).findAll();
        verify(examQuestionsRepository1).findQuestionsByExamId(anyLong());

    }
}
