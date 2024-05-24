package tddCourse.tdd.TransactionsMethods;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tddCourse.tdd.Entities.Account;
import tddCourse.tdd.Eums.ErrorEnum;
import tddCourse.tdd.Exceptions.InsufficientMoneyException;

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
    @Test

    void debit_account_test(){
        Account newAccountReal =  Account.builder()
                .person("John Doe")
                .balance(new BigDecimal("2500.12345"))
                .build();
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.debit(new BigDecimal(500),newAccountReal);

        assertNotNull(newAccountReal.getBalance());
        assertEquals(2000, newAccountReal.getBalance().intValue(), ErrorEnum.CUENTA_VACIA.getErrorMessage());
        assertEquals("2000.12345", newAccountReal.getBalance().toPlainString(),ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());
    }

    @Test
    void credit_account_test(){
        Account newAccountReal =  Account.builder()
                .person("John Doe")
                .balance(new BigDecimal("2500.12345"))
                .build();
        AccountMethods accountMethods = new AccountMethods();
        accountMethods.credit(new BigDecimal(500),newAccountReal);

        assertNotNull(newAccountReal.getBalance(),ErrorEnum.CUENTA_VACIA.getErrorMessage());
        assertEquals(3000, newAccountReal.getBalance().intValue(),ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());// hasta que el método no esté implementado esta comprobación va a fallar
        assertEquals("3000.12345", newAccountReal.getBalance().toPlainString(),ErrorEnum.IMPORTE_INCORRECTO.getErrorMessage());
    }

    /**
     * Test que comprueba que el saldo es < 0
     * Se están utilizando dos comprobaciones:
     *  1º -> Se utiliza una excepción personalizada para que salte si el saldo de la cuenta es <0
     *  2º -> Se utiliza el assertEquals para saber si el mensaje que recibimos y el esperado son iguales.
     * Por ejemplo si utilizamos el NumberFormatException no va a coincidir el tipo de excepción ya que en el
     * método estamos utilizando InsufficientMoneyException
     */
    @Test
    @DisplayName("Comprobar que salta la excepción si hay saldo insuficiente")
    void insufficient_money_account_exception(){
        Account newAccountReal =  Account.builder()
                .person("John Doe")
                .balance(new BigDecimal("2500.12345"))
                .build();
        AccountMethods accountMethods = new AccountMethods();
        Exception exception = assertThrows(InsufficientMoneyException.class,()->{
            accountMethods.debit(new BigDecimal(3000),newAccountReal);
        });

        String actualBalance = exception.getMessage();
        String expectedBalance = "Dinero insuficiente";

        assertEquals(expectedBalance,actualBalance);
    }

}