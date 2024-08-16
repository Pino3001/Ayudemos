package Ayudemos.datatypes;

import Ayudemos.types.DTFechaHora;

public class DTArticulo extends DTDonacion{
    private final String descripcion;
    private final float peso;
    private final String dimensiones;

    //Constructor
    public DTArticulo(Integer id, DTFechaHora fechaIngresada, String descripcion, float peso, String dimensiones) {
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

}