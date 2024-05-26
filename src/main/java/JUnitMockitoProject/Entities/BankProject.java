package JUnitMockitoProject.Entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class BankProject {
    private Long id;

    private String bankName;

    private int totalTransfer;


}
