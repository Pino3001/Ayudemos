package datatypes;

import datatypes.soap.DtBeneficiarioSOAP;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import types.Barrio;
import types.DTFecha;
import types.EstadoBeneficiario;
import utils.DateConverterSOAP;

import java.time.LocalDate;
import java.util.Date;
@XmlAccessorType(XmlAccessType.FIELD)

public class DtBeneficiario extends DtUsuario {
    private final String direccion;
    private final LocalDate fechaNacimiento;
    private final EstadoBeneficiario estado;
    private final Barrio barrio;

    // Constructor.
    public DtBeneficiario(){
        this.direccion = "";
        this.fechaNacimiento = LocalDate.now();
        this.estado = null;
        this.barrio = null;
    }
    public DtBeneficiario(Integer id, String nombre, String mail, String direccion, LocalDate fechaNacimiento, EstadoBeneficiario estado, Barrio barrio, String contrasenia) {
        super(id, nombre, mail, contrasenia);
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.barrio = barrio;
    }

    public DtBeneficiario(DtBeneficiarioSOAP dt) {
        super(dt.getId(), dt.getNombre(), dt.getMail(), dt.getContrasenia());
        this.direccion = dt.getDireccion();
        this.fechaNacimiento = DateConverterSOAP.toLocalDate(dt.getFechaNacimiento());
        this.estado = dt.getEstado();
        this.barrio = dt.getBarrio();
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
