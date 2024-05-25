package JUnit.TransactionsMethods;

import JUnit.Entities.Account;
import JUnit.Exceptions.InsufficientMoneyException;

import java.math.BigDecimal;

public class AccountMethods {

    /**
     * @param debit
     * @param account Este método resta dinero a la cuenta
     */
    public void debit(BigDecimal debit, Account account) {
        BigDecimal remainingBalance = account.getBalance().subtract(debit);
        if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientMoneyException("Dinero insuficiente");
        }

        account.setBalance(remainingBalance);
    }

    /**
     * @param credit
     * @param account Este método añade dinero a la cuenta
     */
    public void credit(BigDecimal credit, Account account) {
        BigDecimal remainingBalance = account.getBalance().add(credit);
        account.setBalance(remainingBalance);
    }

}
