package JUnitMockitoProject.JPATest;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Respositories.AccountProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        Optional<AccountProject> account = accountProjectRepository.findByPerson("Irina");
        assertThrows(NoSuchElementException.class, () -> {
            account.orElseThrow();
        });
        assertTrue(account.isPresent());
        assertEquals("Irina", account.orElseThrow().getPerson());
        assertEquals("1000", account.orElseThrow().getBalance());
    }

    @Test
    void findAll_test() {
        List<AccountProject> accountsProject = accountProjectRepository.findAll();
        assertFalse(accountsProject.isEmpty());
        assertEquals(2, accountsProject.size());
    }
}
