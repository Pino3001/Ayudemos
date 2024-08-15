package Ayudemos.objects;

public class Repartidor extends Usuario {
    private string numeroLicencia;
    //Crear Dependencias

    //Constructor
    public Repartidor(String nombre, String mail, string numeroLicencia) {
        super(nombre, mail);
        this.numeroLicencia = numeroLicencia;
    }

    //Getters Y Setters
    public string getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(string numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
}