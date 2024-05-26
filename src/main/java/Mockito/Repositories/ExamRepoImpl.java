package Mockito.Repositories;


import GeneralResources.Data.ExamData;
import Mockito.Entities.Exam;

import java.util.Arrays;
import java.util.List;

public class ExamRepoImpl implements ExamRepo {
    @Override
    public List<Exam> findAll() {
        System.out.println("ExamRepoImpl.findAll");
        return ExamData.EXAMS;
    }

    @Override
    public Exam save(Exam examen) {
        System.out.println("ExamRepoImpl.save");
        return ExamData.EXAM;
    }
}
