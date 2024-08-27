package Ayudemos.datatypes;

public class DtRepartidor extends DtUsuario{
    private final String numeroLicencia;

    public DtRepartidor(String nombre, String mail, String numeroLicencia) {
        super(nombre, mail);//
        this.numeroLicencia = numeroLicencia;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }
}
