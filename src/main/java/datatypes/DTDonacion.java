package datatypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class DTDonacion {
    private final Integer id;
    private final LocalDateTime fechaIngresada;

    //Consutructor
    public DTDonacion(Integer id, LocalDateTime fechaIngresada) {
        this.id = id;//
        this.fechaIngresada = fechaIngresada;
    }

    //Getters Y Setters
    public Integer getId() {
        return id;
    }

    public LocalDateTime getFechaIngresada() {
        return fechaIngresada;
    }
}