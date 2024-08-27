package Ayudemos.datatypes;

public class DtUsuario {
    private final String nombre;
    private final String mail;

    public DtUsuario(String nombre, String mail) {
        this.nombre = nombre;//
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }
}
