package datatypes;

import datatypes.soap.DtDonacionSOAP;
import utils.DateConverterSOAP;

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

    public DTDonacion(DtDonacionSOAP dt) {
        this.id = dt.getId();
        this.fechaIngresada = DateConverterSOAP.toLocalDateTime(dt.getFechaIngresada());
    }

    //Getters Y Setters
    public Integer getId() {
        return id;
    }

    public LocalDateTime getFechaIngresada() {
        return fechaIngresada;
    }
}