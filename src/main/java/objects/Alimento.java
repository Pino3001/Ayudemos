package objects;

import datatypes.DTAlimento;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;


@Entity
@DiscriminatorValue("alimento")
public class Alimento extends Donacion {

    @Column(nullable = false, length = 200)
    private String descripcionProductos;

    @Column(nullable = false)
    private int cantElementos;

    //Constructor
    public Alimento(String descripcionProductos, int cantElementos) {
        super();
        this.descripcionProductos = descripcionProductos;//
        this.cantElementos = cantElementos;
    }

    protected Alimento() {
    }

    //Getters Y Setters
    public String getDescripcionProductos() {
        return descripcionProductos;
    }

    public void setDescripcionProductos(String descripcionProductos) {
        this.descripcionProductos = descripcionProductos;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }

    // Devuelvo un DTAlimento con mis datos.
    @Override
    public DTAlimento getDTDonacion() {
        DTAlimento dtAlimento = new DTAlimento(this.getId(), this.getFechaIngresada(), this.descripcionProductos, this.cantElementos);
        return dtAlimento;
    }
}