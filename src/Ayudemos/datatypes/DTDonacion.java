package Ayudemos.datatypes;

public class DTDonacion {
    private int id;
    private DateTime fechaIngresada;

    //Consutructor
    public DTDonacion(int id, DateTime fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
    }

    //Getters Y Setters
    public int getId() {
        return id;
    }

    public DateTime getFechaIngresada() {
        return fechaIngresada;
    }
}