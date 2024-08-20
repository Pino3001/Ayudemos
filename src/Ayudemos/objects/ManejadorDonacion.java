package Ayudemos.objects;

import Ayudemos.datatypes.DTAlimento;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.datatypes.DTDonacion;

import java.util.ArrayList;
import java.util.List;

public class ManejadorDonacion {
    private static ManejadorDonacion instance = null;
    private List<Donacion> donaciones = new ArrayList<>();

    private ManejadorDonacion() {
    }

    public static ManejadorDonacion getInstance() {
        if (instance == null) {
            instance = new ManejadorDonacion();
        }
        return instance;
    }

    // Retorna una lista datatypes de todas las donaciones del sistema.
    public List<DTDonacion> obtenerDonaciones() {
        List<DTDonacion> lista = new ArrayList<DTDonacion>();
        for (Donacion d : donaciones) {
            // creamos el dt y lo añadimos a la lista que retornaremos al terminar.
            DTDonacion dt = new DTDonacion(d.getId(), d.getFechaIngresada());
            lista.add(dt);
        }

        return lista;
    }

    // Agrega donacion a la lista de donaciones existentes.
    public void agregarDonacion(Donacion donacion) {
        donaciones.add(donacion);
    }

    // Busca una donación por ID en la lista de donaciones y retorna la información en un dt.
    public DTDonacion buscarDonacionID(Integer id) {
        DTDonacion dt = null;
        Donacion donacion = null;
        boolean encontrado = false;
        int i = 0;

        while (i < donaciones.size() && !encontrado) {
            if (donaciones.get(i).getId().equals(id)) {
                donacion = donaciones.get(i);
                encontrado = true;
            }
            i++;
        }
        if (encontrado) {
            if (donacion instanceof Articulo) {
                dt = new DTArticulo(donacion.getId(), donacion.getFechaIngresada(), ((Articulo) donacion).getDescripcion(), ((Articulo) donacion).getPeso(), ((Articulo) donacion).getDimensiones());
            } else {
                dt = new DTAlimento(donacion.getId(), donacion.getFechaIngresada(), ((Alimento) donacion).getDescripcionProductos(), ((Alimento) donacion).getCantElementos());
            }
            return dt;
        }
        return dt;
    }

    // Modifica una donación.
    public void modificarDonacion(DTDonacion dtDonacion, Integer id) {
        Donacion donacion = null;
        boolean encontrado = false;
        int i = 0;

        while (i < donaciones.size() && !encontrado) {
            if (donaciones.get(i).getId().equals(id)) {
                donacion = donaciones.get(i);
                encontrado = true;
            }
            i++;
        }
        if (encontrado) {
            if (donacion instanceof Articulo) {
                if (dtDonacion instanceof DTArticulo) {
                    donacion.setId(dtDonacion.getId());
                    ((Articulo) donacion).setDescripcion(((DTArticulo) dtDonacion).getDescripcion());
                    ((Articulo) donacion).setPeso(((DTArticulo) dtDonacion).getPeso());
                    ((Articulo) donacion).setDimensiones(((DTArticulo) dtDonacion).getDimensiones());
                }
                // Retornar un error si no se cumple las condiciones.
            } else {
                if (dtDonacion instanceof DTAlimento) {
                    donacion.setId(dtDonacion.getId());
                    ((Alimento) donacion).setDescripcionProductos(((DTAlimento) dtDonacion).getDescripcionProductos());
                    ((Alimento) donacion).setCantElementos(((DTAlimento) dtDonacion).getCantElementos());
                }
                // Retornar el error correspondiente.
            }
        }
    }
}
