package Ayudemos.objects;

import Ayudemos.datatypes.DTAlimento;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.datatypes.DTDonacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManejadorDonacion {
    private static ManejadorDonacion instance = null;
    private Set<Donacion> donaciones = new HashSet<Donacion>();//

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
    // TODO: Imlpementar Exepciones y ver si se puede mejorar
    public DTDonacion buscarDonacionID(Integer id) {
        DTDonacion dt = null;

        // Itera sobre cada donación en el set 'donaciones'
        for (Donacion d : donaciones) {
            // Comprueba si el ID de la donación coincide con el ID buscado
            if (d.getId().equals(id)) {
                // Si es una instancia de Articulo, crea un DTArticulo
                if (d instanceof Articulo) {
                    dt = new DTArticulo(d.getId(), d.getFechaIngresada(), ((Articulo) d).getDescripcion(), ((Articulo) d).getPeso(), ((Articulo) d).getDimensiones());
                }
                // Si es una instancia de Alimento, crea un DTAlimento
                else {
                    dt = new DTAlimento(d.getId(), d.getFechaIngresada(), ((Alimento) d).getDescripcionProductos(), ((Alimento) d).getCantElementos());
                }
                // Retorna el DT encontrado
                return dt;
            }
        }
        // Si no encuentra una donación con el ID especificado, retorna null
        return dt;
    }

    // Modifica una donación.
    // TODO: Implementar Exepciones.
    public void modificarDonacion(DTDonacion dtDonacion, Integer id) {
        for(Donacion d : donaciones) {
            if (d.getId().equals(id)) {
                if (d instanceof Articulo) {
                    if (dtDonacion instanceof DTArticulo) {
                        ((Articulo) d).setDescripcion(((DTArticulo) dtDonacion).getDescripcion());
                        ((Articulo) d).setPeso(((DTArticulo) dtDonacion).getPeso());
                        ((Articulo) d).setDimensiones(((DTArticulo) dtDonacion).getDimensiones());
                        return;
                    }
                    // Retornar un error si no se cumple las condiciones.
                } else {
                    if (dtDonacion instanceof DTAlimento) {
                        ((Alimento) d).setDescripcionProductos(((DTAlimento) dtDonacion).getDescripcionProductos());
                        ((Alimento) d).setCantElementos(((DTAlimento) dtDonacion).getCantElementos());
                        return;
                    }
                    // Retornar el error correspondiente.
                }
            }
        }
    }

    // Obtiene una lista de DTAlimentos
    //TODO: Implementar Exepciones.
    public List<DTAlimento> listarAlimentosManejador(){
        List<DTAlimento> dtAlimentos = new ArrayList<>();
        for (Donacion d : donaciones) {
            if (d instanceof Alimento) {
                DTAlimento dtAlimento = ((Alimento) d).getDTAlimento();
                dtAlimentos.add(dtAlimento);
            }
        }
        return dtAlimentos;
    }

    // Obtiene una lista de DTArticulos
    public List<DTArticulo> listarArticulosManejador(){
        List<DTArticulo> dtArticulos = new ArrayList<>();
        for (Donacion d : donaciones) {
            if (d instanceof Articulo) {
                DTArticulo dtArticulo = ((Articulo) d).getDTArticulo();
                dtArticulos.add(dtArticulo);
            }
        }
        return dtArticulos;
    }

    // Genera un ID unico para la donacion.
    public Integer generarID(){
        int nuevoId = donaciones.size();
        // Iterar hasta encontrar un ID que no esté en uso
        boolean idEnUso;
        do {
            idEnUso = false;
            for (Donacion d : donaciones) {
                if (d.getId().equals(nuevoId)) {
                    idEnUso = true;
                    nuevoId++;
                    break;
                }
            }
        } while (idEnUso);
        return nuevoId;  // Devolver el ID único encontrado
    }

}
