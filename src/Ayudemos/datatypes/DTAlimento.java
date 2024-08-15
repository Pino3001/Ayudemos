package Ayudemos.datatypes;

import Ayudemos.types.DTFechaHora;

public class DTAlimento extends DTDonacion{
    private String descripcionProductos;
    private int cantElementos;

    //Constructor
    public DTAlimento(int id, DTFechaHora fechaIngresada, String descripcionProductos, int cantElementos) {
        super(id, fechaIngresada);
        this.descripcionProductos = descripcionProductos;
        this.cantElementos = cantElementos;
    }

    //Getters Y Setters
    public String getDescripcionProductos() {
        return descripcionProductos;
    }

    public int getCantElementos() {
        return cantElementos;
    }

}