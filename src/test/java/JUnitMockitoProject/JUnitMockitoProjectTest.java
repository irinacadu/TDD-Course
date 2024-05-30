package JUnitMockitoProject;

import JUnitMockitoProject.Data.DataAccountBank;
import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.BankProject;
import JUnitMockitoProject.Exceptions.InsufficientMoneyProjectException;
import JUnitMockitoProject.Respositories.AccountProjectRepository;
import JUnitMockitoProject.Respositories.BankProjectRepository;
import JUnitMockitoProject.Services.AccountProjectService;
import JUnitMockitoProject.Services.AccountProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class JUnitMockitoProjectTest {

    AccountProjectRepository accountProjectRepository;
    BankProjectRepository bankProjectRepository;

    AccountProjectService accountProjectService;

    @BeforeEach
    void setUp() {
        accountProjectRepository = mock(AccountProjectRepository.class);
        bankProjectRepository = mock(BankProjectRepository.class);
        accountProjectService = new AccountProjectServiceImpl(accountProjectRepository,bankProjectRepository);
    }

    @Test
    @DisplayName("Comprobamos aldo inicial, transferencias entre cuentas, saldo final despues de transfer y veces que se ha llamado a los método")
    void contexLoad() {

        //Establecemos los datos
        when(accountProjectRepository.findById(1l)).thenReturn(DataAccountBank.createAccount_001());
        when(accountProjectRepository.findById(2l)).thenReturn(DataAccountBank.createAccount_002());
        when(bankProjectRepository.findById(1l)).thenReturn(DataAccountBank.createBank_001());

        //Comprobamos el saldo inicial de las cuentas
        BigDecimal originBalance = accountProjectService.reviewBalance(1L);
        BigDecimal destinyBalance = accountProjectService.reviewBalance(2L);

        assertEquals("1000",originBalance.toPlainString());
        assertEquals("2000",destinyBalance.toPlainString());

        //transferimos dinero entre cuentas y comprobamos que el importe después de la transferencia es correcto
        accountProjectService.transferAmount(1L, 2L, new BigDecimal("100"),1L);

        originBalance = accountProjectService.reviewBalance(1L);
        destinyBalance = accountProjectService.reviewBalance(2L);

        assertEquals("900",originBalance.toPlainString());
        assertEquals("2100",destinyBalance.toPlainString());

        // Comprobamos el número de transferencias que se han hecho
        int total = accountProjectService.reviewTotalTransfer(1L);
        assertEquals(1,total);

        // Comprobamos la cantidad de veces que se han llamado a los métodos
        verify(accountProjectRepository,times(3)).findById(1L);
        verify(accountProjectRepository,times(3)).findById(2L);

        verify(accountProjectRepository,times(2)).save(any(AccountProject.class));
        verify(bankProjectRepository,times(2)).findById(1L);
        verify(bankProjectRepository).save(any(BankProject.class));

        verify(accountProjectRepository,never()).findAll();
        verify(accountProjectRepository,times(6)).findById(anyLong());

    }

    @Test
    @DisplayName("Comprueba si se lanza la excepción de dinero insuficiente")
    void constextLoads2() {
        //Establecemos los datos
        when(accountProjectRepository.findById(1l)).thenReturn(DataAccountBank.createAccount_001());
        when(accountProjectRepository.findById(2l)).thenReturn(DataAccountBank.createAccount_002());
        when(bankProjectRepository.findById(1l)).thenReturn(DataAccountBank.createBank_001());

        //Comprobamos el saldo inicial de las cuentas
        BigDecimal originBalance = accountProjectService.reviewBalance(1L);
        BigDecimal destinyBalance = accountProjectService.reviewBalance(2L);

        assertEquals("1000",originBalance.toPlainString());
        assertEquals("2000",destinyBalance.toPlainString());

        assertThrows(InsufficientMoneyProjectException.class,()->{
            accountProjectService.transferAmount(1L, 2L, new BigDecimal("1200"),1L);
        });

        originBalance = accountProjectService.reviewBalance(1L);
        destinyBalance = accountProjectService.reviewBalance(2L);

        assertEquals("1000",originBalance.toPlainString());
        assertEquals("2000",destinyBalance.toPlainString());

        // Comprobamos el número de transferencias que se han hecho
        int total = accountProjectService.reviewTotalTransfer(1L);
        assertEquals(0,total);

        // Comprobamos la cantidad de veces que se han llamado a los métodos
        verify(accountProjectRepository,times(3)).findById(1L);
        verify(bankProjectRepository,times(1)).findById(1L);
        verify(accountProjectRepository,never()).save(any(AccountProject.class));

        verify(bankProjectRepository,times(1)).findById(1L);
        verify(bankProjectRepository,never()).save(any(BankProject.class));
        verify(accountProjectRepository,times(5)).findById(anyLong());
        verify(accountProjectRepository,never()).findAll();
    }

    @Test
    @DisplayName("comprobamos que es la misma cuenta")
    void contextLoads3() {
        when(accountProjectRepository.findById(1l)).thenReturn(DataAccountBank.createAccount_001());

        AccountProject accountProject1 = accountProjectService.findById(1L);
        AccountProject accountProject2 = accountProjectService.findById(1L);

        assertSame(accountProject1,accountProject2);
        assertTrue(accountProject2==accountProject1);
        assertEquals("Irina",accountProject1.getPerson());
        assertEquals("Irina",accountProject2.getPerson());
        verify(accountProjectRepository,times(2)).findById(1L);

    }

}