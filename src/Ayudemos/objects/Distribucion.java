package Ayudemos.objects;

import Ayudemos.types.DTFechaHora;
import Ayudemos.types.EstadoDistribucion;

public class Distribucion {
    private DTFechaHora fechaPreparacion;
    private DTFechaHora fechaEntrega;
    private EstadoDistribucion estado;
    // Agregar Dependencias

    //Constructor
    public Distribucion(DTFechaHora fechaPreparacion, DTFechaHora fechaEntrega, EstadoDistribucion estado) {
        this.fechaPreparacion = fechaPreparacion;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    //Getters y Setters

    public DTFechaHora getFechaPreparacion() {
        return fechaPreparacion;
    }

    public void setFechaPreparacion(DTFechaHora fechaPreparacion) {
        this.fechaPreparacion = fechaPreparacion;
    }

    public DTFechaHora getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(DTFechaHora fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public EstadoDistribucion getEstado() {
        return estado;
    }

    public void setEstado(EstadoDistribucion estado) {
        this.estado = estado;
    }
}