package JUnitMockitoProject.Respositories;

import JUnit.Entities.Account;
import JUnitMockitoProject.Entities.AccountProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountProjectRepository extends JpaRepository <AccountProject,Long> {

    @Query("select account from AccountProject account where account.person =?1")
    Optional<AccountProject> findByPerson(String person);



//    List<AccountProject> findAll();
//    AccountProject findById(Long id);
//
//    void update(AccountProject accountProject);
}
