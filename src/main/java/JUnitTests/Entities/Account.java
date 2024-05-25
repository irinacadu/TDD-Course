package JUnitTests.Entities;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Account {
    private String person;
    private BigDecimal balance;
    private Bank bankName;

    /**
     * Author: Irina Casas
     * @param object
     * @return this.person && this.balance values
     * Para poder utilizar el assertEquals en nuestros tests y que no nos compare por instancia si no por valores
     * tenemos que sobreescribir el método equals de la entidad
     *
     */
    @Override
    public boolean equals(Object object){

    Account account = (Account) object;

    if(object == null || !(object instanceof Account)) return false;
    if(this.person == null || this.balance == null) return false;
    return this.person.equals(account.getPerson() )&& this.balance.equals(account.getBalance());
}
}
