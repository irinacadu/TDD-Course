package JUnitMockitoProject.Exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class InsufficientMoneyProjectException extends RuntimeException{
    public InsufficientMoneyProjectException(String dineroInsuficiente) {
    }
}
