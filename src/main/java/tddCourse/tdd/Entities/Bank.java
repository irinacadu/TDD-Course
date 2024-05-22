package tddCourse.tdd.Entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Bank {
    private String name;
    BigDecimal balance;
    List <Account> bankAccounts = new ArrayList<>();
}
