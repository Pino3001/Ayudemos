package Ayudemos.datatypes;

import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DateTime;

public class DTDonacion {
    private int id;
    private DTFechaHora fechaIngresada;

    //Consutructor
    public DTDonacion(int id, DTFechaHora fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }

    //Getters Y Setters
    public int getId() {
        return id;
    }

    public DTFechaHora getFechaIngresada() {
        return fechaIngresada;
    }
}