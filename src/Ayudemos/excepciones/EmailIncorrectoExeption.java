package Ayudemos.excepciones;

public class EmailIncorrectoExeption extends Exception {
    private static final long serialVersionUID = 1L;

    public EmailIncorrectoExeption(String message) {
        super(message);
    }
}
