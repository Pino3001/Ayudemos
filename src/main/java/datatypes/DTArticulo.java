package datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.time.LocalDateTime;
@XmlAccessorType(XmlAccessType.FIELD)

public class DTArticulo extends DTDonacion{
    private final String descripcion;
    private final float peso;
    private final String dimensiones;

    //Constructor
    public DTArticulo(){
        super();
        this.descripcion = "";
        this.peso = 0;
        this.dimensiones = "";
    }
    public DTArticulo(Integer id, LocalDateTime fechaIngresada, String descripcion, float peso, String dimensiones) {
        super(id, fechaIngresada);
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