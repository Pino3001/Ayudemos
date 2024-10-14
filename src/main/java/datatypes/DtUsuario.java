package datatypes;

import datatypes.soap.DtUsuarioSOAP;

public class DtUsuario {
    private final Integer id;
    private final String nombre;
    private final String mail;
    private final String contrasenia;

    public DtUsuario(){
        this.id = null;
        this.nombre = null;
        this.mail = null;
        this.contrasenia = null;
    }
    public DtUsuario(Integer id, String nombre, String mail, String contrasenia) {
        this.id = id;
        this.nombre = nombre;//
        this.mail = mail;
        this.contrasenia = contrasenia;
    }

    public DtUsuario(DtUsuarioSOAP dt) {
        this.id = dt.getId();
        this.nombre = dt.getNombre();//
        this.mail = dt.getMail();
        this.contrasenia = dt.getContrasenia();
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
