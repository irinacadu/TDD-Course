package tddCourse.tdd.Entities;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tddCourse.tdd.Eums.ErrorEnum;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountTest {

    @Test
    @DisplayName("Comprobar que el nombre del cliente es correcto")
    public void test_account_name(){
        Account newAccount =  Account.builder()
                .person("Irina")
                .balance(new BigDecimal("12546.2356"))
                .build();

        String expectedName="Irina";
        String realName = newAccount.getPerson();
        assertEquals(expectedName,realName, ErrorEnum.NOMBRE_CLIENTE_INEXISTENTE.getErrorMessage());

      /*
          String expectedName="Irina".toUpperCase();
          assertTrue(expectedName.equals("Irina"));

       */


    }

    @Test
    @DisplayName("Comprobar que los importes en la cuenta coincide")
    void test_account_balance(){
        Account newAccount =  Account.builder()
                                .person("Irina")
                                .balance(new BigDecimal("1000.12345"))
                                .build();

        assertEquals(1000.12345,newAccount.getBalance().doubleValue(),ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());//Con el doubleValue() estamos convirtiendo el BigDecimal a Double.
        assertFalse(newAccount.getBalance().compareTo(BigDecimal.ZERO)<0,ErrorEnum.SALDO_INSUFICIENTE.getErrorMessage());// El saldo es mayor que 0
     // assertFalse(newAccount.getBalance().compareTo(BigDecimal.ZERO)>0); va a fallar porque es es "true" que el saldo es mayor que 0
    }

    @Test
    @DisplayName("Comprobar que las dos cuentas son la misma")
    @Disabled
    void account_reference(){
        fail();
        Account newAccountReal = Account.builder()
                                    .person("John Doe")
                                    .balance(new BigDecimal("9000.12345"))
                                    .build();
        Account newAccountExpected = Account.builder()
                                        .person("John Doe")
                                        .balance(new BigDecimal("9000.12345"))
                                        .build();


       // assertNotEquals(newAccountExpected,newAccountReal);
        assertEquals(newAccountExpected,newAccountReal,ErrorEnum.CUENTA_NO_COINCIDE.getErrorMessage());
    }

}