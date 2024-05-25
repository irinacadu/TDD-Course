package JUnit.TransactionsMethods;

import JUnit.Entities.Account;
import GeneralTestResources.Enums.ErrorEnum;
import JUnit.Exceptions.InsufficientMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Hasta que los métodos no estén implementados los tests no van a funcionar
 */
class AccountMethodsTest {
    /**
     * Si al pasarle como parámetro una cantidad que implique el saldo sea menor que 0 este test va a fallar ya que
     * se va a lanzar la excepción.
     */
    @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
    @ValueSource(strings = {"100", "200", "300", "500", "1000"})
    void debit_account_test(String balance) {
        Account newAccountReal = Account.builder()
                .person("John Doe")
                .balance(new BigDecimal("2500.12345"))
                .build();
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.debit(new BigDecimal(balance), newAccountReal);

        assertNotNull(newAccountReal.getBalance());
        assertTrue(newAccountReal.getBalance().compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2000, newAccountReal.getBalance().intValue(), ErrorEnum.CUENTA_VACIA.getErrorMessage());
        assertEquals("2000.12345", newAccountReal.getBalance().toPlainString(), ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());
    }

    @ParameterizedTest(name = "numero {index} ejecutando con valor {0} - {argumentsWithNames}")
    @CsvSource({"1,100", "2,200", "3,300", "4,500", "5,1000"})
    void credit_account_test(String index, String balance) {
        Account newAccountReal = Account.builder()
                .person("John Doe")
                .balance(new BigDecimal("2500.12345"))
                .build();
        System.out.println(index + ":" + balance);
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.credit(new BigDecimal(balance), newAccountReal);

        assertNotNull(newAccountReal.getBalance(), ErrorEnum.CUENTA_VACIA.getErrorMessage());
        assertEquals(3000, newAccountReal.getBalance().intValue(), ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());// hasta que el método no esté implementado esta comprobación va a fallar
        assertEquals("3000.12345", newAccountReal.getBalance().toPlainString(), ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());
    }

    /**
     * Test que comprueba que el saldo es < 0
     * Se están utilizando dos comprobaciones:
     * 1º -> Se utiliza una excepción personalizada para que salte si el saldo de la cuenta es <0
     * 2º -> Se utiliza el assertEquals para saber si el mensaje que recibimos y el esperado son iguales.
     * Por ejemplo si utilizamos el NumberFormatException no va a coincidir el tipo de excepción ya que en el
     * método estamos utilizando InsufficientMoneyException
     */
    @Test
    @DisplayName("Comprobar que salta la excepción si hay saldo insuficiente")
    void insufficient_money_account_exception() {
        Account newAccountReal = Account.builder()
                .person("John Doe")
                .balance(new BigDecimal("2500.12345"))
                .build();
        AccountMethods accountMethods = new AccountMethods();
        Exception exception = assertThrows(InsufficientMoneyException.class, () -> {
            accountMethods.debit(new BigDecimal(3000), newAccountReal);
        });

        String actualBalance = exception.getMessage();
        String expectedBalance = "Dinero insuficiente";

        assertEquals(expectedBalance, actualBalance);
    }

}