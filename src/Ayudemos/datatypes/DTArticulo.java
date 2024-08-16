package Ayudemos.datatypes;

import Ayudemos.types.DTFechaHora;

public class DTArticulo extends DTDonacion{
    private String descripcion;
    private float peso;
    private String dimenciones;

    //Constructor
    public DTArticulo(Integer id, DTFechaHora fechaIngresada, String descripcion, float peso, String dimenciones) {
        super(id, fechaIngresada);
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimenciones = dimenciones;
    }

    //Getters Y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public String getDimenciones() {
        return dimenciones;
    }

}