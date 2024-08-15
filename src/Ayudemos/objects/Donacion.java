package Ayudemos.objects;


import Ayudemos.types.DateTime;

public abstract class Donacion {
    private int id;
    private DateTime fechaIngresada;
    //Agregar Dependencias

    //Constructor
    public Donacion(int id) {
        this.id = id;
        this.fechaIngresada = new DateTime();
    }

    // Getters Y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getFechaIngresada() {
        return fechaIngresada;
    }

    public void setFechaIngresada(DateTime fechaIngresada) {
        this.fechaIngresada = fechaIngresada;
    }
}