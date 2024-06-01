package JUnitMockitoProject.Services;

import JUnitMockitoProject.Entities.AccountProject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountProjectService {

    AccountProject findById(Long id);

    AccountProject findByPerson(String person);

    List<AccountProject> findAll();

    AccountProject save(AccountProject accountProject);

    void deleteById(Long id);
    int reviewTotalTransfer(Long bankId);

    BigDecimal reviewBalance(Long bankId);

    void transferAmount(Long originAccount, Long destinyAccount, BigDecimal amount,Long bankId);

    void debit(BigDecimal amount, AccountProject accountProject);

    void credit(BigDecimal amount, AccountProject accountProject);



}
