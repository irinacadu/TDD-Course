package JUnitMockitoProject.Respositories;

import JUnitMockitoProject.Entities.BankProject;

import java.util.List;

public interface BankProjectRepository {

    List<BankProject> findAll();

    BankProject findById(Long id);

    void update (BankProject bankProject);
}
