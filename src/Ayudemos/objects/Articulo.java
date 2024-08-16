package Ayudemos.objects;

public class Articulo extends Donacion {
    private String descripcion;
    private float peso;
    private String dimenciones;
    //Agregar dependencias

    //Constructor
    public Articulo(Integer id, String descripcion, float peso, String dimenciones) {
        super(id);
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimenciones = dimenciones;
    }

    //Getters Y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getDimenciones() {
        return dimenciones;
    }

    public void setDimenciones(String dimenciones) {
        this.dimenciones = dimenciones;
    }
}