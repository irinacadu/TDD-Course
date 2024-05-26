package JUnitMockitoProject.Services;

import JUnitMockitoProject.Entities.AccountProject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountProjectService {

    AccountProject findById(Long id);

    int reviewTotalTransfer(Long bankId);

    BigDecimal reviewBalance(Long bankId);

    void transferAmount(Long originAccount, Long destinyAccount, BigDecimal amount,Long bankId);

    void debit(BigDecimal amount, AccountProject accountProject);

    void credit(BigDecimal amount, AccountProject accountProject);



}
