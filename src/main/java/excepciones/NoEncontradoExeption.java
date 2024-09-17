package excepciones;

public class NoEncontradoExeption extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NoEncontradoExeption(String message) {
        super(message);
    }
}
