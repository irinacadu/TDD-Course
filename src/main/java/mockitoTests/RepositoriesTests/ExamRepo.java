package mockitoTests.RepositoriesTests;

import mockito.Entities.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepo {
    List<Exam> findAll();
}
