package tddCourse.tdd.Entities;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import tddCourse.tdd.Eums.ErrorEnum;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountTest {
Account account;

    /**
     * Con este método "globalizamos" la cuenta de manera que podemos evitar la sobrecarga de código en los demás métodos.
     * @BeforeEach va a crear una nueva instancia por cada función de manera que, aunque estemos "reutilizando", evitará que
     * todos los métodos dependan de una sola instancia.
     */
    @BeforeEach
    void init_test_method(){
        account =  Account.builder()
                .person("Irina")
                .balance(new BigDecimal("1000.12345"))
                .build();
        System.out.println("Iniciando el método");
    }

    @AfterEach
    void end_test_method(){
        System.out.println("Finalizando el método");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando el test");

    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando el test");
    }

    @Test
    @DisplayName("Comprobar que el nombre del cliente es correcto")
    public void test_account_name(){


        String expectedName="Irina";
        String realName = account.getPerson();
        assertEquals(expectedName,realName, ErrorEnum.NOMBRE_CLIENTE_INEXISTENTE.getErrorMessage());

      /*
          String expectedName="Irina".toUpperCase();
          assertTrue(expectedName.equals("Irina"));

       */

    }

    @Test
    @DisplayName("Comprobar que los importes en la cuenta coincide")
    void test_account_balance(){

        boolean isDevEnvironment = "DEV".equals(System.getProperty("ENV"));

        assertEquals(1000.12345,account.getBalance().doubleValue(),ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());//Con el doubleValue() estamos convirtiendo el BigDecimal a Double.
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO)<0,ErrorEnum.SALDO_INSUFICIENTE.getErrorMessage());// El saldo es mayor que 0
     // assertFalse(newAccount.getBalance().compareTo(BigDecimal.ZERO)>0); va a fallar porque es es "true" que el saldo es mayor que 0
    }

    @Test
    @DisplayName("Comprobar que las dos cuentas son la misma")
   // @Disabled
    void account_reference(){
       // fail();
        account = Account.builder()
                                    .person("John Doe")
                                    .balance(new BigDecimal("1000.12345"))
                                    .build();
        Account newAccountExpected = Account.builder()
                                        .person("John Doe")
                                        .balance(new BigDecimal("1000.12345"))
                                        .build();


       // assertNotEquals(newAccountExpected,newAccountReal);
        assertEquals(newAccountExpected,account,ErrorEnum.CUENTA_NO_COINCIDE.getErrorMessage());
    }

}