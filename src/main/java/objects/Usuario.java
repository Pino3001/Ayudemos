package objects;

import datatypes.DTDonacion;
import datatypes.DtUsuario;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  // Estrategia de generación de ID automatico
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String mail;

    // Constructor con parámetros
    public Usuario(String nombre, String mail) {
        this.nombre = nombre;
        this.mail = mail;
    }

    // Constructor sin argumentos
    protected Usuario() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
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

    public Integer getId() {
        return id;
    }

    // Metodo abtracto para pasarse como datatype
    public abstract DtUsuario getDtUsuario();
}
