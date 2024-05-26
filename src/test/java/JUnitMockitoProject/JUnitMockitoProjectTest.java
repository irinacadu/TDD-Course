package JUnitMockitoProject;

import JUnitMockitoProject.Data.DataAccountBank;
import JUnitMockitoProject.Respositories.AccountProjectRepository;
import JUnitMockitoProject.Respositories.BankProjectRepository;
import JUnitMockitoProject.Services.AccountProjectService;
import JUnitMockitoProject.Services.AccountProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void contextLoads() {

        when(accountProjectRepository.findById(1l)).thenReturn(DataAccountBank.ACCOUNT_001);
        when(accountProjectRepository.findById(2l)).thenReturn(DataAccountBank.ACCOUNT_002);
        when(bankProjectRepository.findById(1l)).thenReturn(DataAccountBank.BANK_001);

        BigDecimal originBalance = accountProjectService.reviewBalance(1L);
        BigDecimal destinyBalance = accountProjectService.reviewBalance(2L);

        assertEquals("1000",originBalance.toPlainString());
        assertEquals("2000",destinyBalance.toPlainString());

    }
}