package objects;

import types.DTFecha;
import types.EstadoBeneficiario;
import types.Barrio;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("B")
public class Beneficiario extends Usuario {
    private String direccion;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private EstadoBeneficiario estado;
    private Barrio barrio;

    @OneToMany(mappedBy = "distribuciones", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Distribucion> distribuciones;

    // Constructor
    public Beneficiario(String nombre, String mail, String direccion, Date fechaNacimiento, EstadoBeneficiario estado, Barrio barrio, Integer id) {
        super(nombre, mail, id);//
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    public Beneficiario() {
        super();
    }

    // Getters y Setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public EstadoBeneficiario getEstado() {
        return estado;
    }

    public void setEstado(EstadoBeneficiario estado) {
        this.estado = estado;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    // Metodos de clase:
    // Inserta una distribucion a la lista de ditribuciones asociadas
    public void addDistribucion(Distribucion distribucion) {
        distribuciones.add(distribucion);
    }
}