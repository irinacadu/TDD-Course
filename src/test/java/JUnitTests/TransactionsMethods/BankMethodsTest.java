package JUnitTests.TransactionsMethods;

import JUnitTests.Entities.Bank;
import JUnitTests.Eums.ErrorEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import JUnitTests.Entities.Account;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BankMethodsTest {

    @Test
    @DisplayName("Comprobar que las transferencias entre cuentas se ejecutan.")
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

        assertEquals("2000.12345",originAccount.getBalance().toPlainString(), ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage() );
        assertEquals("3000.12345",destinyAccount.getBalance().toPlainString(),ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage() );

    }


    @Test
    @DisplayName("Comprobar relación entre banco y cliente")
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
                ()-> assertEquals("2000.12375",originAccount.getBalance().toPlainString(),()->ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage()), //fallo intencionado
                ()-> assertEquals("3000.12345",destinyAccount.getBalance().toPlainString(), ()-> ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage()),
                ()-> assertEquals(2, bank.getBankAccounts().size(),()-> ErrorEnum.CANTIDAD_DE_CUENTAS_ERRONEA.getErrorMessage()),
                ()-> assertEquals("Irina's Bank.", originAccount.getBankName().getName(),()-> ErrorEnum.NOMBRE_CUENTA_ERRONEO.getErrorMessage()+ ". Se esperaba 'Irina's Bank.' y se ha obtenido '" + destinyAccount.getBankName().getName() + "'."), //fallo intencionado
                ()-> assertEquals("Andrés", bank.getBankAccounts().stream()
                        .filter(account->account.getPerson().equals("Andrés"))
                        .findFirst()
                        .get()
                        .getPerson(),()-> ErrorEnum.NOMBRE_CLIENTE_INEXISTENTE.getErrorMessage()),
                ()-> assertTrue(bank.getBankAccounts().stream()
                        .anyMatch(account->account.getPerson().equals("Andrés")),()-> ErrorEnum.NOMBRE_CLIENTE_INEXISTENTE.getErrorMessage())
                );
    }




}