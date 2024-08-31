package objects;

import datatypes.DtRepartidor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Repartidor extends Usuario {
    private String numeroLicencia;
    //Crear Dependencias

    //Constructor
    public Repartidor(String nombre, String mail, String numeroLicencia, Integer id) {
        super(nombre, mail, id);
        this.numeroLicencia = numeroLicencia;
    }

    public Repartidor(String nombre, String mail, String numeroLicencia) {
    }

    public DtRepartidor getDTRepartidor() {
        return new DtRepartidor(
                this.getNombre(),
                this.getMail(),
                this.getNumeroLicencia()
        );
    }


    public Repartidor() {

    }


    //Getters Y Setters
    public String getNumeroLicencia() {
        return numeroLicencia;//
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }
}