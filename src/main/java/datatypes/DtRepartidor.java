package datatypes;

import datatypes.soap.DtRepartidorSOAP;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)

public class DtRepartidor extends DtUsuario {
    private final String numeroLicencia;

    public DtRepartidor(){
        super();
        this.numeroLicencia = "";
    }

    public DtRepartidor(Integer id, String nombre, String mail, String numeroLicencia, String contrasenia) {
        super(id, nombre, mail, contrasenia);//
        this.numeroLicencia = numeroLicencia;
    }

    public DtRepartidor(DtRepartidorSOAP dt) {
        super(dt.getId(), dt.getNombre(), dt.getMail(), dt.getContrasenia());//
        this.numeroLicencia = dt.getNumeroLicencia();
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    // Para poder mostrarlo en pantalla
    @Override
    public String toString() {
        return getMail();
    }
}
