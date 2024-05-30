package JUnitMockitoProject.Entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionProjectDTO {

    private Long originAccountId;
    private Long destinyAccountId;
    private BigDecimal amount;
    private Long bankId;
}
