package tddCourse.tdd.TransactionsMethods;

import tddCourse.tdd.Entities.Account;
import tddCourse.tdd.Entities.Bank;

import java.math.BigDecimal;

public class BankMethods {

    public void transferMoney(Account originAccount, Account destinyAccount, BigDecimal money){
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.debit(money,originAccount);
        accountMethods.credit(money,destinyAccount);

    }

    public void addAccount(Bank bank,Account account){

        bank.getBankAccounts().add(account);
        account.setBankName(bank);
    }
}
