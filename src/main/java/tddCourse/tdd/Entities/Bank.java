package tddCourse.tdd.Entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Bank {
    private String name;
    private Account originAccount;
    private Account destinyAccount;
    BigDecimal balance;
}
