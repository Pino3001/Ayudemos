package Ayudemos.objects;

public class Repartidor extends Usuario {
    private String numeroLicencia;
    //Crear Dependencias

    //Constructor
    public Repartidor(String nombre, String mail, String numeroLicencia) {
        super(nombre, mail);
        this.numeroLicencia = numeroLicencia;
    }

    //Getters Y Setters
    public String getNumeroLicencia() {
        return numeroLicencia;//
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
}