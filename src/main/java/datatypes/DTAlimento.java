package datatypes;

import java.time.LocalDateTime;

public class DTAlimento extends DTDonacion{

    private final String descripcionProductos;
    private final int cantElementos;

    public DTAlimento() {
        super();
        this.descripcionProductos = null;
        this.cantElementos = 0;
    }

    public DTAlimento(int id, LocalDateTime fechaIngresada, String descripcionProductos, int cantElementos) {
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

    @Override
    public String toString() {
        return "ID: " + this.getId() + " - " + descripcionProductos;
    }
}