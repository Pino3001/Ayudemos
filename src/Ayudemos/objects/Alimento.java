package Ayudemos.objects;

public class Alimento extends Donacion {
    private String descripcionProductos;
    private int cantElementos;
    //Agregar dependencias

    //Constructor
    public Alimento(Integer id, String descripcionProductos, int cantElementos) {
        super(id);
        this.descripcionProductos = descripcionProductos;
        this.cantElementos = cantElementos;
    }

    //Getters Y Setters
    public String getDescripcionProductos() {
        return descripcionProductos;
    }

    public void setDescripcionProductos(String descripcionProductos) {
        this.descripcionProductos = descripcionProductos;
    }

    public int getCantElementos() {
        return cantElementos;
    }

    public void setCantElementos(int cantElementos) {
        this.cantElementos = cantElementos;
    }
}