package tddCourse.tdd.AccountMethods;

import org.junit.jupiter.api.Test;
import tddCourse.tdd.Entities.Account;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Hasta que los métodos no estén implementados los tests no van a funcionar
 */
class AccountMethodsTest {
    @Test
    void debit_account_test(){
        Account newAccountReal = new Account("John Doe",new BigDecimal("1000.12345"));
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.debit(new BigDecimal(100),newAccountReal);

        assertNotNull(newAccountReal.getBalance());
        assertEquals(900, newAccountReal.getBalance().intValue());
        assertEquals("900.12345", newAccountReal.getBalance().toPlainString());
    }

    @Test
    void credit_account_test(){
        Account newAccountReal = new Account("John Doe",new BigDecimal("1000.12345"));
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.credit(new BigDecimal(100),newAccountReal);

        assertNotNull(newAccountReal.getBalance());
        assertEquals(1100, newAccountReal.getBalance().intValue());// hasta que el método no esté implementado esta comprobación va a fallar
        assertEquals("1100.12345", newAccountReal.getBalance().toPlainString());
    }


}