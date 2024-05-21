package tddCourse.tdd.AccountMethods;

import org.junit.jupiter.api.Test;
import tddCourse.tdd.Entities.Account;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountMethodsTest {

    @Test
    void debit_account_test(){
        Account newAccountReal = new Account("John Doe",new BigDecimal("9000.12345"));
        assertEquals(9000.12345,newAccountReal.getBalance().doubleValue());
    }

    @Test
    void credit_account_test(){

    }
}