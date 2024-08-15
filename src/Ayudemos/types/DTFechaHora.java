package Ayudemos.types;

public class DTFechaHora {
    private DTFecha fecha;
    private int hora;
    private int minuto;

    //Constructor
    public DTFechaHora(DTFecha fecha, int hora, int minuto) {
        this.fecha = fecha;
        this.hora = hora;
        this.minuto = minuto;
    }

    //Getters Y Setters

    public DTFecha getFecha() {
        return fecha;
    }

    public void setFecha(DTFecha fecha) {
        this.fecha = fecha;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
}