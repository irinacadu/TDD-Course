package Mockito.Services;

import Mockito.Entities.Exam;
import Mockito.Repositories.ExamQuestionsRepository;
import Mockito.Repositories.ExamRepo;

import java.util.List;
import java.util.Optional;

public class ExamServiceImpl implements ExamService {

    private final ExamRepo examRepo;
    private final ExamQuestionsRepository examQuestionsRepository;

    public ExamServiceImpl(ExamRepo examRepo, ExamQuestionsRepository examQuestionsRepository) {
        this.examRepo = examRepo;
        this.examQuestionsRepository = examQuestionsRepository;
    }


    @Override
    public Optional<Exam> findExamByName(String name) {
        return examRepo.findAll()
                .stream()
                .filter(examName -> examName.getName().equals(name))
                .findFirst();

    }

    @Override
    public Exam findQuestionsByExamName(String name) {

        Optional<Exam> examOptional = findExamByName(name);
        Exam exam = null;

        if (examOptional.isPresent()) {
            exam = examOptional.orElseThrow();
            List<String> questions = examQuestionsRepository.findQuestionsByExamId(exam.getId());
            exam.setQuestions(questions);
        }

        return exam;
    }

    @Override
    public Exam save(Exam exam) {
        if(!exam.getQuestions().isEmpty()){
            examQuestionsRepository.saveQuestions(exam.getQuestions());
        }
        return examRepo.save(exam);
    }
}
