package JUnitTests.TransactionsMethods;

import JUnitTests.Entities.Bank;
import JUnitTests.Entities.Account;

import java.math.BigDecimal;

public class BankMethods {

    public void transferMoney(Account originAccount, Account destinyAccount, BigDecimal money){
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.debit(money,originAccount);
        accountMethods.credit(money,destinyAccount);

    }

    public void addAccount(Bank bank, Account account){

        bank.getBankAccounts().add(account);
        account.setBankName(bank);
    }
}
