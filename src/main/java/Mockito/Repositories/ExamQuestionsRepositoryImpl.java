package Mockito.Repositories;

import GeneralResources.Data.ExamData;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExamQuestionsRepositoryImpl implements ExamQuestionsRepository {
    @Override
    public List<String> findQuestionsByExamId(Long id) {
        System.out.println("ExamQuestionsRepositoryImpl.findQuestionsByExamId");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ExamData.QUESTIONS;
    }

    @Override
    public void saveQuestions(List<String> questions) {
        System.out.println("ExamQuestionsRepositoryImpl.saveQuestions");

    }
}
