package Ayudemos.datatypes;

import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DateTime;

public class DTDonacion {
    private Integer id;
    private DTFechaHora fechaIngresada;

    //Consutructor
    public DTDonacion(Integer id, DTFechaHora fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }

    //Getters Y Setters
    public Integer getId() {
        return id;
    }

    public DTFechaHora getFechaIngresada() {
        return fechaIngresada;
    }
}