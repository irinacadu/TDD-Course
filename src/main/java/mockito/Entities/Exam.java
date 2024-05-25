package mockito.Entities;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Exam {
    private Long id;
    private String name;
    private List<String> questions = new ArrayList<>();
}
