package objects;


import datatypes.DTAlimento;
import datatypes.DTArticulo;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("articulo")
public class Articulo extends Donacion {
    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private float peso;

    @Column(nullable = false)
    private String dimensiones;

    //Constructor
    public Articulo(String descripcion, float peso, String dimensiones) {
        super();
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimensiones = dimensiones;
    }

    public Articulo() {

    }

    //Getters Y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    //Devuelvo un DTArticulo con mis datos.
    @Override
    public DTArticulo getDTDonacion(){
        return new DTArticulo(this.getId(), this.getFechaIngresada(), this.descripcion, this.peso, this.dimensiones);
    }
}