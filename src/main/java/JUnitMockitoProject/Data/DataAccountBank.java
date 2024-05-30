package JUnitMockitoProject.Data;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.BankProject;

import java.math.BigDecimal;
import java.util.Optional;

public class DataAccountBank {
/*    public static final AccountProject ACCOUNT_001 = AccountProject.builder()
                                                                    .id(1L)
                                                                    .person("Irina")
                                                                    .balance(new BigDecimal("1000"))
                                                                    .build();
    public static final AccountProject ACCOUNT_002 = AccountProject.builder()
                                                                    .id(2L)
                                                                    .person("Candela")
                                                                    .balance(new BigDecimal("2000"))
                                                                    .build();

    public static final BankProject BANK_001 = BankProject.builder()
                                                            .id(1L)
                                                            .bankName("Financial Bank")
                                                            .totalTransfer(0)
                                                            .build();*/


    public static Optional<AccountProject> createAccount_001(){
        return Optional.of( AccountProject.builder()
                .id(1L)
                .person("Irina")
                .balance(new BigDecimal("1000"))
                .build());
    }

    public static Optional <AccountProject> createAccount_002(){
        return Optional.of (AccountProject.builder()
                .id(2L)
                .person("Candela")
                .balance(new BigDecimal("2000"))
                .build());
    }

    public static Optional <BankProject> createBank_001(){
        return Optional.of (BankProject.builder()
                .id(1L)
                .bankName("Financial Bank")
                .totalTransfer(0)
                .build());
    }
}
