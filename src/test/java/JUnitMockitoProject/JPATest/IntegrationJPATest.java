package JUnitMockitoProject.JPATest;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Respositories.AccountProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IntegrationJPATest {
    @Autowired
    AccountProjectRepository accountProjectRepository;

    @Test
    void findById_test() {
        Optional<AccountProject> account = accountProjectRepository.findById(1L);
        assertTrue(account.isPresent());
        assertEquals("Irina", account.orElseThrow().getPerson());
    }

    @Test
    void findByPerson_test() {
        Optional<AccountProject> account = accountProjectRepository.findByPerson("Candela");
//        assertThrows(NoSuchElementException.class, () -> {
//            account.orElseThrow();
//        });
        assertTrue(account.isPresent());
        assertEquals("Candela", account.orElseThrow().getPerson());
//        assertEquals("1000", account.orElseThrow().getBalance());
    }

    @Test
    void findAll_test() {
        List<AccountProject> accountsProject = accountProjectRepository.findAll();
        assertFalse(accountsProject.isEmpty());
        assertEquals(2, accountsProject.size());
    }

    @Test
    void save_test() {

        //GIVEN
        AccountProject accountMaria = AccountProject.builder()
                .person("Maria")
                .balance(new BigDecimal("3000"))
                .build();

        accountProjectRepository.save(accountMaria);

        //WHEN
        AccountProject account = accountProjectRepository.findByPerson("Maria").orElseThrow();

        //THEN
        assertEquals("Maria", account.getPerson());
        assertEquals("3000", account.getBalance().toPlainString());
        assertEquals(3, account.getId());

    }

    @Test
    void update_test() {
        //GIVEN
        AccountProject accountMaria = AccountProject.builder()
                .person("Maria")
                .balance(new BigDecimal("3000"))
                .build();

        AccountProject account = accountProjectRepository.save(accountMaria);
        assertEquals("Maria", account.getPerson());
        assertEquals("3000", account.getBalance().toPlainString());
        assertEquals(3, account.getId());

        //WHEN
        account.setBalance(new BigDecimal("4000"));
        AccountProject updatedAccount = accountProjectRepository.save(accountMaria);

        //THEN
        assertEquals("Maria", updatedAccount.getPerson());
        assertEquals("4000", updatedAccount.getBalance().toPlainString());
        assertEquals(3, updatedAccount.getId());

    }

    @Test
    void delete_test() {

        //GIVEN
        AccountProject account = accountProjectRepository.findById(1L).orElseThrow();
        assertEquals("Irina", account.getPerson());

        //WHEN
        accountProjectRepository.delete(account);
        
        //THEN
        assertThrows(NoSuchElementException.class, () -> {
            accountProjectRepository.findByPerson("Irina").orElseThrow();
        });

    }


}
