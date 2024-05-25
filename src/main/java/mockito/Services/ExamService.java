package mockito.Services;

import mockito.Entities.Exam;
import org.springframework.stereotype.Service;


public interface ExamService {
    Exam findExamByName(String name);
}
