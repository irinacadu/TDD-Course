package tddCourse.tdd.TransactionsMethods;

import org.junit.jupiter.api.Test;
import tddCourse.tdd.Entities.Account;
import tddCourse.tdd.Entities.Bank;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankMethodsTest {

    @Test
    void transfer_money_accounts_test(){
        Account originAccount =  Account.builder()
                                    .person("John Doe")
                                    .balance(new BigDecimal("2500.12345"))
                                    .build();
        Account destinyAccount =  Account.builder()
                                    .person("Irina Casas")
                                    .balance(new BigDecimal("2500.12345"))
                                    .build();

        Bank bank = new Bank();
        BankMethods bankMethods = new BankMethods();
        bank.setName("Irina's Bank");

        bankMethods.transferMoney(originAccount,destinyAccount, new BigDecimal(500));
        assertEquals("2000.12345",originAccount.getBalance().toPlainString() );
        assertEquals("3000.12345",destinyAccount.getBalance().toPlainString() );

    }


    @Test
    void bank_account_relationship_test(){
        BankMethods bankMethods = new BankMethods();
        Bank bank = new Bank();
        bank.setName("Bank Irina");


        Account originAccount =  Account.builder()
                                    .person("Andrés")
                                    .balance(new BigDecimal("2500.12345"))
                                    .build();
        Account destinyAccount =  Account.builder()
                                    .person("Irina Casas")
                                    .balance(new BigDecimal("2500.12345"))
                                    .build();

        bankMethods.addAccount(bank,originAccount);
        bankMethods.addAccount(bank,destinyAccount);

        bankMethods.transferMoney(originAccount,destinyAccount, new BigDecimal(500));


        assertAll(
                ()-> assertEquals("2000.12375",originAccount.getBalance().toPlainString(),"El importe no es el esperado"), //fallo intencionado
                ()-> assertEquals("3000.12345",destinyAccount.getBalance().toPlainString(),"El importe no es el esperado"),
                ()-> assertEquals(2, bank.getBankAccounts().size(),"La cantidad de cuentas no coincide con la cantidad de cuentas que tiene el banco"),
                ()-> assertEquals("Irina's Bank.", originAccount.getBankName().getName(),"El nombre del banco no es correcto : se esperaba 'Irina's Bank.' y se ha obtenido '" + destinyAccount.getBankName().getName() + "'."), //fallo intencionado
                ()-> assertEquals("Andrés", bank.getBankAccounts().stream()
                        .filter(account->account.getPerson().equals("Andrés"))
                        .findFirst()
                        .get()
                        .getPerson(),"No existe ningún cliente con ese nombre"),
                ()-> assertTrue(bank.getBankAccounts().stream()
                        .anyMatch(account->account.getPerson().equals("Andrés")),"No existe ningún cliente con ese nombre")
                );


    }
}