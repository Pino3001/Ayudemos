package Ayudemos.objects;


import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DateTime;

public abstract class Donacion {
    private Integer id;
    private DateTime fechaIngresada;
    //Agregar Dependencias

    //Constructor
    public Donacion(Integer id) {
        this.id = id;
        this.fechaIngresada = new DateTime();
    }

    // Getters Y Setters
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DTFechaHora getFechaIngresada() {

        return fechaIngresada.convertir();
    }

    public void setFechaIngresada(DateTime fechaIngresada) {
        this.fechaIngresada = fechaIngresada;
    }
}