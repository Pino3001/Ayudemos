package excepciones;

import java.text.ParseException;

public class IngresoIncorrectoExeption extends Exception{
    private static final long serialVersionUID = 1L;

    public IngresoIncorrectoExeption(String message) {
        super(message);
    }
}
