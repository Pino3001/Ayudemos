package Ayudemos.datatypes;

import Ayudemos.types.Barrio;
import Ayudemos.types.DTFecha;
import Ayudemos.types.EstadoBeneficiario;

public class DtBeneficiario extends DtUsuario{
    private String direccion;
    private DTFecha fechaNacimiento;
    private EstadoBeneficiario estado;
    private Barrio barrio;

    // Constructor.

    public DtBeneficiario(String nombre, String mail, String direccion, DTFecha fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
        super(nombre, mail);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    // Getters y Setters.

    public String getDireccion() {
        return direccion;
    }

    public DTFecha getFechaNacimiento() {
        return fechaNacimiento;
    }

    public EstadoBeneficiario getEstado() {
        return estado;
    }

    public Barrio getBarrio() {
        return barrio;
    }
}
