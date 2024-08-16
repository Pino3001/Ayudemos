package Ayudemos.objects;

public class Articulo extends Donacion {
    private String descripcion;
    private float peso;
    private String dimensiones;
    //Agregar dependencias

    //Constructor
    public Articulo(Integer id, String descripcion, float peso, String dimensiones) {
        super(id);
        this.descripcion = descripcion;
        this.peso = peso;
        this.dimensiones = dimensiones;
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

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }
}