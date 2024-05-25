package JUnit.Entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Bank {
    BigDecimal balance;
    List<Account> bankAccounts = new ArrayList<>();
    private String name;
}
