package JUnitTests.Eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorEnum {

    IMPORTE_INCORRECTO("El importe no es el esperado"),
    CUENTA_NO_ENCONTRADA("La cuenta no fue encontrada"),
    NOMBRE_CUENTA_ERRONEO("El nombre de la cuenta no coincide"),
    SALDO_INSUFICIENTE("Saldo insuficiente en la cuenta"),
    OPERACION_NO_PERMITIDA("Operación no permitida"),
    CANTIDAD_DE_CUENTAS_ERRONEA("La cantidad de cuentas seleccionadas no coincide con la cantidad de cuentas que tiene el banco"),
    NOMBRE_CLIENTE_INEXISTENTE("No existe ningún cliente con ese nombre"),
    CUENTA_NO_COINCIDE("Las cuentas no coinciden"),
    CUENTA_VACIA("La cuenta está vacía");
    private final String errorMessage;
}
