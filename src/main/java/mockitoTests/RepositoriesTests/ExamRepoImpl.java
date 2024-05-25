package mockitoTests.RepositoriesTests;

import mockito.Entities.Exam;

import java.util.Arrays;
import java.util.List;

public class ExamRepoImpl implements ExamRepo {
    @Override
    public List<Exam> findAll() {
        return Arrays.asList(
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
    }
}
