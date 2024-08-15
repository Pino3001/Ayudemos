package Ayudemos.objects;

public abstract class Donacion {
    private int id;
    private DateTime fechaIngresada;
    //Agregar Dependencias

    //Constructor
    public Donacion(int id, DateTime fechaIngresada) {
        this.id = id;
        this.fechaIngresada = fechaIngresada;
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