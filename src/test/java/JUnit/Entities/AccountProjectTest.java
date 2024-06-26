package JUnit.Entities;


import GeneralResources.Enums.ErrorEnum;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountProjectTest {
    Account account;



    @RepeatedTest(value = 5, name = "Repetición numero {currentRepetition} de {totalRepetitions}")
    @DisplayName("Comprobar que el nombre del cliente es correcto")
//  @Disabled
    public void test_account_name() {


        String expectedName = "Irina";
        String realName = account.getPerson();
        Assertions.assertEquals(expectedName, realName, ErrorEnum.NOMBRE_CLIENTE_INEXISTENTE.getErrorMessage());

      /*
          String expectedName="Irina".toUpperCase();
          assertTrue(expectedName.equals("Irina"));

       */

    }

    @Test
    @DisplayName("Comprobar que los importes en la cuenta coincide")
    void test_account_balance() {

        assertEquals(1000.12345, account.getBalance().doubleValue(), ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());//Con el doubleValue() estamos convirtiendo el BigDecimal a Double.
        assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0, ErrorEnum.SALDO_INSUFICIENTE.getErrorMessage());// El saldo es mayor que 0
        // assertFalse(newAccount.getBalance().compareTo(BigDecimal.ZERO)>0); va a fallar porque es es "true" que el saldo es mayor que 0
    }

    @Test
    @DisplayName("Comprobar que las dos cuentas son la misma")
        // @Disabled
    void account_reference() {
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
        assertEquals(newAccountExpected, account, ErrorEnum.CUENTA_NO_COINCIDE.getErrorMessage());
    }

    @Test
    @DisplayName("Comprobar que los importes en la cuenta coinciden // DEV")
    void test_account_balance_dev_env() {

        boolean isDevEnvironment = "dev".equals(System.getProperty("ENV"));
        //assumeTrue(isDevEnvironment);

        assumingThat(isDevEnvironment, () -> {
            assertEquals(1000.12345, account.getBalance().doubleValue(), ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());//Con el doubleValue() estamos convirtiendo el BigDecimal a Double.
            assertFalse(account.getBalance().compareTo(BigDecimal.ZERO) < 0, ErrorEnum.SALDO_INSUFICIENTE.getErrorMessage());// El saldo es mayor que 0
            // assertFalse(newAccount.getBalance().compareTo(BigDecimal.ZERO)>0); va a fallar porque es es "true" que el saldo es mayor que 0
        });

    }

    /**
     * Con este método "globalizamos" la cuenta de manera que podemos evitar la sobrecarga de código en los demás métodos.
     *
     * @BeforeEach va a crear una nueva instancia por cada función de manera que, aunque estemos "reutilizando", evitará que
     * todos los métodos dependan de una sola instancia.
     */
    @BeforeEach
    void init_test_method(@NotNull TestInfo testInfo, TestReporter testReporter) {
        System.out.println("ejecutando: " + testInfo.getDisplayName() + " " + testInfo.getTestMethod().orElse(null).getName());

        account = Account.builder()
                .person("Irina")
                .balance(new BigDecimal("1000.12345"))
                .build();
        System.out.println("Iniciando el método");
    }

    @AfterEach
    void end_test_method() {
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
}