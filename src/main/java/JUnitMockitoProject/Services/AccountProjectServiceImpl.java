package JUnitMockitoProject.Services;

import JUnit.Exceptions.InsufficientMoneyException;
import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.BankProject;
import JUnitMockitoProject.Exceptions.InsufficientMoneyProjectException;
import JUnitMockitoProject.Respositories.AccountProjectRepository;
import JUnitMockitoProject.Respositories.BankProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountProjectServiceImpl implements AccountProjectService {

    private AccountProjectRepository accountProjectRepository;
    private BankProjectRepository bankProjectRepository;

    public AccountProjectServiceImpl(AccountProjectRepository accountProjectRepository, BankProjectRepository bankProjectRepository) {
        this.accountProjectRepository = accountProjectRepository;
        this.bankProjectRepository = bankProjectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AccountProject findById(Long id) {

        return accountProjectRepository.findById(id).orElseThrow();
    }

    @Override
    public AccountProject findByPerson(String person) {
        return accountProjectRepository.findByPerson(person).orElseThrow();
    }

    @Override
    public List<AccountProject> findAll() {
      return  accountProjectRepository.findAll();
    }

    @Override
    public AccountProject save(AccountProject accountProject) {

        return accountProjectRepository.save(accountProject);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        accountProjectRepository.deleteById(id);
    }

    @Override
    @Transactional()
    public int reviewTotalTransfer(Long bankId) {
        BankProject bankProject = bankProjectRepository.findById(bankId).orElseThrow();
        return bankProject.getTotalTransfer();
    }

    @Override
    public BigDecimal reviewBalance(Long bankId) {
        AccountProject accountProject = accountProjectRepository.findById(bankId).orElseThrow();
        return accountProject.getBalance();
    }

    @Override
    public void transferAmount(Long originAccount, Long destinyAccount, BigDecimal amount, Long bankId) {

        AccountProject accountProjectOrigin = accountProjectRepository.findById(originAccount).orElseThrow();
        debit(amount,accountProjectOrigin);
        accountProjectRepository.save(accountProjectOrigin);

        AccountProject accountProjectDestiny = accountProjectRepository.findById(destinyAccount).orElseThrow();
        credit(amount,accountProjectDestiny);
        accountProjectRepository.save(accountProjectDestiny);

        BankProject bankProject = bankProjectRepository.findById(bankId).orElseThrow();
        int totalTransfers = bankProject.getTotalTransfer();
        bankProject.setTotalTransfer(++totalTransfers);
        bankProjectRepository.save(bankProject);

    }

    @Override
    public void debit(BigDecimal amount, AccountProject accountProject) {
        BigDecimal remainingBalance = accountProject.getBalance().subtract(amount);
        if (remainingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientMoneyProjectException("Dinero insuficiente");
        }

        accountProject.setBalance(remainingBalance);
    }

    @Override
    public void credit(BigDecimal amount, AccountProject accountProject) {
        BigDecimal remainingBalance = accountProject.getBalance().add(amount);
        accountProject.setBalance(remainingBalance);
    }
}
