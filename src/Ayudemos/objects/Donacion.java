package Ayudemos.objects;


import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Donacion {
    private Integer id;
    private DateTime fechaIngresada;
    //Agregar Dependencias
    // Una donación puede tener mas de una distribución según el diagrama de clases dado por letra.
    private List<Distribucion> distribuciones;

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

    //Metodos de clase:
    //Inserta una distribucion a la lista de ditribuciones asociadas
    public void addDistribucion(Distribucion distribucion) {
        distribuciones.add(distribucion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donacion donacion = (Donacion) o;
        return id != null && id.equals(donacion.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}