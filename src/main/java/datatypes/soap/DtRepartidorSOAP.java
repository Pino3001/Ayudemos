package datatypes.soap;

import datatypes.DtRepartidor;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtRepartidorSOAP extends DtUsuarioSOAP {
    private String numeroLicencia;

    public DtRepartidorSOAP() {
    }

    // Constructor con todos los parámetros.
    public DtRepartidorSOAP(Integer id, String nombre, String mail, String numeroLicencia, String contrasenia) {
        super(id, nombre, mail, contrasenia);
        this.numeroLicencia = numeroLicencia;
    }

    // Constructor que recibe un objeto DtRepartidor.
    public DtRepartidorSOAP(DtRepartidor dt) {
        super(dt.getId(), dt.getNombre(), dt.getMail(), dt.getContrasenia());
        this.numeroLicencia = dt.getNumeroLicencia();
    }

    // Getters y Setters.
    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    @Override
    public String toString() {
        return (getMail() != null) ? getMail() : "Sin mail";
    }
}