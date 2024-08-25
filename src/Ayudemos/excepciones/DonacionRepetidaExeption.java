package Ayudemos.excepciones;

public class DonacionRepetidaExeption extends Exception {
    private static final long serialVersionUID = 1L;

    public DonacionRepetidaExeption(String message) {
        super(message);
    }
}
