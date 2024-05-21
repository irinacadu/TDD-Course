package tddCourse.tdd.Entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountTest {

    @Test
    public void test_account_name(){
        Account newAccount = new Account("Irina",new BigDecimal("12546.2356"));
        String expectedName="Irina";
        String realName = newAccount.getPerson();
        assertEquals(expectedName,realName);

      /*
          String expectedName="Irina".toUpperCase();
          assertTrue(expectedName.equals("Irina"));

       */


    }

    @Test
    void test_account_balance(){
        Account newAccount = new Account("Irina",new BigDecimal("1000.12345"));
        assertEquals(1000.12345,newAccount.getBalance().doubleValue());
        assertFalse(newAccount.getBalance().compareTo(BigDecimal.ZERO)<0);// El saldo es mayor que 0
     // assertFalse(newAccount.getBalance().compareTo(BigDecimal.ZERO)>0); va a fallar porque es es "true" que el saldo es mayor que 0
    }

    @Test
    void account_reference(){
        Account newAccountReal = new Account("John Doe",new BigDecimal("9000.12345"));
        Account newAccountExpected = new Account("John Doe",new BigDecimal("9000.12345"));

       // assertNotEquals(newAccountExpected,newAccountReal);//comparación por instancia
        assertEquals(newAccountExpected,newAccountReal);//Este test falla (si no tenemos el "Equals" en la entidad porque son dos objetos distintos, dos instancias distintas. Está comparando por valor

    }

}