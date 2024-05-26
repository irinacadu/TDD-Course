package Mockito.Repositories;

import java.util.List;

public interface ExamQuestionsRepository {

    List<String> findQuestionsByExamId(Long id);

    void saveQuestions(List<String> questions);
}
