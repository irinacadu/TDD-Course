package Mockito.Repositories;

import Mockito.Entities.Exam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepo {
    List<Exam> findAll();
}
