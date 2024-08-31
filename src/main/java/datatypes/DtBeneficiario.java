package datatypes;

import types.Barrio;
import types.DTFecha;
import types.EstadoBeneficiario;

import java.util.Date;

public class DtBeneficiario extends DtUsuario{
    private String direccion;
    private Date fechaNacimiento;
    private EstadoBeneficiario estado;
    private Barrio barrio;

    // Constructor.
    public DtBeneficiario(String nombre, String mail, String direccion, Date fechaNacimiento, EstadoBeneficiario estado, Barrio barrio) {
        super(nombre, mail);//
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    // Getters y Setters.

    public String getDireccion() {
        return direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public EstadoBeneficiario getEstado() {
        return estado;
    }

    public Barrio getBarrio() {
        return barrio;
    }
}
