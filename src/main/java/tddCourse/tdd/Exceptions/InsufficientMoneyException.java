package tddCourse.tdd.Exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 */

public class InsufficientMoneyException extends RuntimeException{
    public InsufficientMoneyException(String message) {
        super(message);
    }
}
