package datatypes.soap;

import datatypes.DtBeneficiario;
import datatypes.DtUsuario;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import types.Barrio;
import types.DTFecha;
import types.EstadoBeneficiario;
import utils.DateConverterSOAP;

import javax.xml.datatype.XMLGregorianCalendar;
import java.lang.reflect.Constructor;
import java.time.LocalDate;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtBeneficiarioSOAP extends DtUsuarioSOAP {
    private String direccion;
    private XMLGregorianCalendar fechaNacimiento;
    private EstadoBeneficiario estado;
    private Barrio barrio;

    public DtBeneficiarioSOAP(){
        super();
    }

    // Constructor .
    public DtBeneficiarioSOAP(Integer id, String nombre, String mail, String direccion, LocalDate fechaNacimiento, EstadoBeneficiario estado, Barrio barrio, String contrasenia) {
        super(id, nombre, mail, contrasenia);
        this.direccion = direccion;
       this.fechaNacimiento = DateConverterSOAP.toXMLGregorianCalendar(fechaNacimiento);
       this.estado = estado;
       this.barrio = barrio;
    }

    public DtBeneficiarioSOAP(DtBeneficiario dt) {
        super(dt.getId(), dt.getNombre(), dt.getNombre(), dt.getContrasenia());
        this.direccion = dt.getDireccion();
        this.fechaNacimiento = DateConverterSOAP.toXMLGregorianCalendar(dt.getFechaNacimiento());
        this.estado = dt.getEstado();
        this.barrio = dt.getBarrio();
    }


    // Getters y Setters.

    public String getDireccion() {
        return direccion;
    }

    public XMLGregorianCalendar getFechaNacimiento() {
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
