package datatypes;

import java.time.LocalDate;
import java.util.Date;

public class DTDonacion {

    private final Integer id;
    private final LocalDate fechaIngresada;

    //Consutructor
    public DTDonacion(Integer id, LocalDate fechaIngresada) {
        this.id = id;//
        this.fechaIngresada = fechaIngresada;
    }

    //Getters Y Setters
    public Integer getId() {
        return id;
    }

    public LocalDate getFechaIngresada() {
        return fechaIngresada;
    }
}