package objects;

import datatypes.DtRepartidor;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("R")
public class Repartidor extends Usuario {

    @Column(nullable = false)
    private String numeroLicencia;

    // Constructor completo
    public Repartidor(String nombre, String mail, String numeroLicencia) {
        super(nombre, mail);
        this.numeroLicencia = numeroLicencia;
    }

    // Constructor sin argumentos requerido por JPA
    protected Repartidor() {
        super();
    }

    // Getters y Setters
    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    // Método para obtener un DTO
    public DtRepartidor getDTRepartidor() {
        return new DtRepartidor(
                this.getId(),
                this.getNombre(),
                this.getMail(),
                this.getNumeroLicencia()
        );
    }
}
