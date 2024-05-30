package JUnitMockitoProject.Respositories;


import JUnitMockitoProject.Entities.BankProject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankProjectRepository extends JpaRepository<BankProject,Long> {

//    List<BankProject> findAll();
//
//    BankProject findById(Long id);
//
//    void update (BankProject bankProject);
}
