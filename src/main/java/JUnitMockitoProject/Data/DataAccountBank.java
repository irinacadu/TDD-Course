package JUnitMockitoProject.Data;

import JUnitMockitoProject.Entities.AccountProject;
import JUnitMockitoProject.Entities.BankProject;

import java.math.BigDecimal;

public class DataAccountBank {
    public static final AccountProject ACCOUNT_001 = AccountProject.builder()
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
                                                            .build();
}
