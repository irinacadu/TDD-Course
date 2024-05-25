package Mockito.Data;

import Mockito.Entities.Exam;

import java.util.Arrays;
import java.util.List;

public class ExamData {

    public final static List<Exam> EXAMS = Arrays.asList(
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
                    .name("Historia")
                    .build()
    );


    public final static List <String> QUESTIONS = Arrays.asList( "Aritmética", "Trigonometría", "Integrales", "Geometría");
}
