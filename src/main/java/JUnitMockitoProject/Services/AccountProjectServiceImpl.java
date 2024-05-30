package JUnitMockitoProject.Services;

import JUnit.Exceptions.InsufficientMoneyException;
import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.BankProject;
import JUnitMockitoProject.Exceptions.InsufficientMoneyProjectException;
import JUnitMockitoProject.Respositories.AccountProjectRepository;
import JUnitMockitoProject.Respositories.BankProjectRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountProjectServiceImpl implements AccountProjectService {

    private AccountProjectRepository accountProjectRepository;
    private BankProjectRepository bankProjectRepository;

    public AccountProjectServiceImpl(AccountProjectRepository accountProjectRepository, BankProjectRepository bankProjectRepository) {
        this.accountProjectRepository = accountProjectRepository;
        this.bankProjectRepository = bankProjectRepository;
    }

    @Override
    public AccountProject findById(Long id) {

        return accountProjectRepository.findById(id).orElseThrow();
    }

    @Override
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
