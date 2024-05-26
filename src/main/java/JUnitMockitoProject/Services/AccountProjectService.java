package JUnitMockitoProject.Services;

import JUnitMockitoProject.Entities.AccountProject;

import java.math.BigDecimal;

public interface AccountProjectService {

    void debit(BigDecimal amount, AccountProject accountProject);

    void credit(BigDecimal amount, AccountProject accountProject);

}
