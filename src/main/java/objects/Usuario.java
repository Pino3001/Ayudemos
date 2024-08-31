package objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
    @Id
    private Integer id;
    private String nombre;
    private String mail;

    // Constructor
    public Usuario(String nombre, String mail, Integer id) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
    }

    public Usuario() {

    }

    // Getters y Setters
    public String getNombre() {
        return nombre;//
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
