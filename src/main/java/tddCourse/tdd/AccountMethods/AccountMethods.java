package tddCourse.tdd.AccountMethods;

import tddCourse.tdd.Entities.Account;

import java.math.BigDecimal;

public class AccountMethods {

    /**
     *
     * @param debit
     * @param account
     * Este método resta dinero a la cuenta
     */
    public void debit(BigDecimal debit,Account  account ){
        BigDecimal remainingBalance = account.getBalance().subtract(debit);
        account.setBalance(remainingBalance);

    }

    /**
     *
     * @param credit
     * @param account
     * Este método añade dinero a la cuenta
     */
    public void credit(BigDecimal credit , Account account){
        BigDecimal remainingBalance = account.getBalance().add(credit);
        account.setBalance(remainingBalance);

    }
}
