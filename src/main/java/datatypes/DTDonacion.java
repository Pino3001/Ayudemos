package datatypes;

import types.DTFechaHora;
import types.DateTime;

public class DTDonacion {
    private final Integer id;
    private final DTFechaHora fechaIngresada;

    //Consutructor
    public DTDonacion(Integer id, DTFechaHora fechaIngresada) {
        this.id = id;//
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