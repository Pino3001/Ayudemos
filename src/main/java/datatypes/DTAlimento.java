package datatypes;

import java.util.Date;

public class DTAlimento extends DTDonacion{
    private final String descripcionProductos;
    private final int cantElementos;

    public DTAlimento(int id, Date fechaIngresada, String descripcionProductos, int cantElementos) {
        super(id, fechaIngresada);//
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