package JUnitMockitoProject.Entities;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class AccountProject {

    private Long id;

    private String person;

    private BigDecimal balance;


}
