package Mockito.Services;

import Mockito.Entities.Exam;

import java.util.Optional;


public interface ExamService {
    Optional<Exam> findExamByName(String name);

    Exam findQuestionsByExamName(String name);
    Exam save (Exam exam);
}
