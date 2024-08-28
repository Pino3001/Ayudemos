package objects;

import types.DTFecha;
import types.EstadoBeneficiario;
import types.Barrio;

import java.util.List;

public class Beneficiario extends Usuario {
    private String direccion;
    private DTFecha fechaNacimiento;
    private EstadoBeneficiario estado;
    private Barrio barrio;
    private List<Distribucion> distribuciones;

    // Constructor
    public Beneficiario(String nombre, String mail, String direccion, DTFecha fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
        super(nombre, mail);//
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

    public DTFecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(DTFecha fechaNacimiento) {
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