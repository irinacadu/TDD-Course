package GeneralTestResources.ArgsMatchers;

import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;

public class PersonalizedArgsMatchers implements ArgumentMatcher<Long> {

    private Long argument;
    @Override
    public boolean matches(Long argument) {
        this.argument = argument;
        return argument != null && argument > 0;
    }

    /***
     * Este es el mensaje de error que aparecerá cuando los argumentos no coincidan.
     * Es parecido a las excepciones
     * @return
     */
    @Override
    public String toString() {
        return "El argumento " + argument +" no es válido.";
    }
}
