package mockito.Services;

import mockito.Entities.Exam;
import mockitoTests.RepositoriesTests.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ExamServiceImpl implements ExamService{

    private ExamRepo examRepo;

    public ExamServiceImpl(ExamRepo examRepo) {
        this.examRepo = examRepo;
    }

    @Override
    public Exam findExamByName(String name) {
        Exam exam = null;

      Optional<Exam> examFound=  examRepo.findAll()
                                            .stream()
                                                .filter(examName -> examName.getName().equals(name))
                                                .findFirst();

      if(examFound.isPresent()) exam = examFound.orElseThrow();

      return exam;
    }
}
