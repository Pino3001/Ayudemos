package objects;

import datatypes.DTAlimento;

import javax.persistence.Entity;

@Entity
public class Alimento extends Donacion {
    private String descripcionProductos;//
    private int cantElementos;

    //Constructor
    public Alimento(Integer id, String descripcionProductos, int cantElementos) {
        super(id);
        this.descripcionProductos = descripcionProductos;//
        this.cantElementos = cantElementos;
    }

    public Alimento() {

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
    public DTAlimento getDTAlimento() {
        DTAlimento dtAlimento = new DTAlimento(this.getId(), this.getFechaIngresada(), this.descripcionProductos, this.cantElementos);
        return dtAlimento;
    }
}