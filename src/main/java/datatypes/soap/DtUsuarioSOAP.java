package datatypes.soap;

import datatypes.DtUsuario;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DtUsuarioSOAP")
public class DtUsuarioSOAP {
    private Integer id;
    private String nombre;
    private String mail;
    private String contrasenia;

    public DtUsuarioSOAP() {}

    public DtUsuarioSOAP(Integer id, String nombre, String mail, String contrasenia) {
        this.id = id;
        this.nombre = nombre;//
        this.mail = mail;
        this.contrasenia = contrasenia;
    }

    public DtUsuarioSOAP(DtUsuario dt) {
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
