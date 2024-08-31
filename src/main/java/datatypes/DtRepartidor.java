package datatypes;

public class DtRepartidor extends DtUsuario{
    private final String numeroLicencia;

    public DtRepartidor(Integer id, String nombre, String mail, String numeroLicencia) {
        super(id,nombre, mail);//
        this.numeroLicencia = numeroLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }
}
