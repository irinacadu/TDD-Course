package JUnitMockitoProject.Services;

import JUnitMockitoProject.Entities.AccountProject;

import java.math.BigDecimal;

public interface AccountProjectService {

    AccountProject findById(Long id);

    int reviewTotalTransfer(Long bankId);

    BigDecimal reviewBalance(Long bankId);

    void transferAmount(Long originAccount, Long destinyAccount, BigDecimal amount,Long bankId);

    void debit(BigDecimal amount, AccountProject accountProject);

    void credit(BigDecimal amount, AccountProject accountProject);



}
