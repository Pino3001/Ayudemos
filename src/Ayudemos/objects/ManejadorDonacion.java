package Ayudemos.objects;

import java.util.ArrayList;
import java.util.List;

public class ManejadorDonacion {
    private static ManejadorDonacion instance = null;
    private List<Donacion> donaciones = new ArrayList<>();

    private ManejadorDonacion() {}

    public static ManejadorDonacion getInstance() {
        if (instance == null) {
            instance = new ManejadorDonacion();
        }
        return instance;
    }

    public void agregarDonacion(Donacion donacion) {
        donaciones.add(donacion);
    }
}
