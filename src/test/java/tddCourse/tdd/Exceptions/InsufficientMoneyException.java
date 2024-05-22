package tddCourse.tdd.Exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class InsufficientMoneyException extends RuntimeException{
    public InsufficientMoneyException(String message) {
        super(message);
    }
}
