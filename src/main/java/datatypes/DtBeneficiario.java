package datatypes;

import types.Barrio;
import types.DTFecha;
import types.EstadoBeneficiario;

import java.time.LocalDate;
import java.util.Date;

public class DtBeneficiario extends DtUsuario {
    private final String direccion;
    private final LocalDate fechaNacimiento;
    private final EstadoBeneficiario estado;
    private final Barrio barrio;

    // Constructor.
    public DtBeneficiario(Integer id, String nombre, String mail, String direccion, LocalDate fechaNacimiento, EstadoBeneficiario estado, Barrio barrio, String contrasenia) {
        super(id, nombre, mail, contrasenia);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    // Getters y Setters.

    public String getDireccion() {
        return direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public EstadoBeneficiario getEstado() {
        return estado;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    @Override
    public String toString() {
        return getMail(); // Mostrar ID y nombre en el JComboBox
    }
}
