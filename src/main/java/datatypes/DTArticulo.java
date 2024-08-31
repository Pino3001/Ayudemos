package datatypes;

import java.time.LocalDate;
import java.util.Date;

public class DTArticulo extends DTDonacion{
    private final String descripcion;
    private final float peso;
    private final String dimensiones;

    //Constructor
    public DTArticulo(Integer id, LocalDate fechaIngresada, String descripcion, float peso, String dimensiones) {
        super(id, fechaIngresada);//
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimensiones = dimensiones;
    }

    //Getters Y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() + " - " + descripcion;
    }
}