package JUnitMockitoProject.Respositories;

import JUnitMockitoProject.Entities.AccountProject;

import java.util.List;

public interface AccountProjectRepository {

    List<AccountProject> findAll();
    AccountProject findById(Long id);

    void update(AccountProject accountProject);
}
