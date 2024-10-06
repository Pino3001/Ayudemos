package datatypes;

public class DtUsuario {
    private final Integer id;
    private final String nombre;
    private final String mail;
    private final String contrasenia;

    public DtUsuario(Integer id, String nombre, String mail, String contrasenia) {
        this.id = id;
        this.nombre = nombre;//
        this.mail = mail;
        this.contrasenia = null;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public Integer getId() {
        return id;
    }

    public String getContrasenia() {
        return contrasenia;
    }

}
