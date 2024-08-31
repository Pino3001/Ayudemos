package datatypes;

import java.util.Date;

public class DTDonacion {
    private final Integer id;
    private final Date fechaIngresada;

    //Consutructor
    public DTDonacion(Integer id, Date fechaIngresada) {
        this.id = id;//
        this.fechaIngresada = fechaIngresada;
    }

    //Getters Y Setters
    public Integer getId() {
        return id;
    }

    public Date getFechaIngresada() {
        return fechaIngresada;
    }
}