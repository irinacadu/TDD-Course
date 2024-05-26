package JUnitMockitoProject.Services;

import JUnit.Exceptions.InsufficientMoneyException;
import JUnitMockitoProject.Entities.AccountProject;

import java.math.BigDecimal;

public class AccountProjectServiceImpl implements AccountProjectService {

    @Override
    public void debit(BigDecimal amount, AccountProject accountProject) {
        BigDecimal remainingBalance = accountProject.getBalance().subtract(amount);
        if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientMoneyException("Dinero insuficiente");
        }

        accountProject.setBalance(remainingBalance);
    }

    @Override
    public void credit(BigDecimal amount, AccountProject accountProject) {
        BigDecimal remainingBalance = accountProject.getBalance().add(amount);
        accountProject.setBalance(remainingBalance);
    }
}
