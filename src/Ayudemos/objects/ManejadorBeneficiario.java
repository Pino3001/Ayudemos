package Ayudemos.objects;

import Ayudemos.datatypes.DTAlimento;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.datatypes.DTDonacion;
import Ayudemos.types.DtBeneficiario;

import java.util.ArrayList;
import java.util.List;

public class ManejadorBeneficiario {
    private static ManejadorBeneficiario instance = null;
    private List<Beneficiario> beneficiarios = new ArrayList<>();

    private ManejadorBeneficiario() {
    }

    public static ManejadorBeneficiario getInstance() {
        if (instance == null) {
            instance = new ManejadorBeneficiario();
        }
        return instance;
    }

    // Retorna una lista datatypes de todas los beneficiarios del sistema.
    public List<DtBeneficiario> obtenerBeneficiario() {
        List<DtBeneficiario> lista = new ArrayList<DtBeneficiario>();
        for (Beneficiario b : beneficiarios) {
            // creamos el dt y lo añadimos a la lista que retornaremos al terminar.
            DtBeneficiario dt = new DtBeneficiario(b.getDireccion(), b.getFechaNacimiento(), b.getEstado(), b.getBarrio());
            lista.add(dt);
        }
        return lista;
    }

    // Agrega donacion a la lista de beneficiarios existentes.
    public void agregarBeneficiario(Beneficiario beneficiario) {
        beneficiarios.add(beneficiario);
    }

    // Busca una donación por ID en la lista de beneficiarios y retorna la información en un dt.
//    public DTDonacion buscarBeneficiarioID(Integer id) {
//        DtBeneficiario dt = null;
//        Beneficiario beneficiario = null;
//        boolean encontrado = false;
//        int i = 0;
//
//        while (i < beneficiarios.size() && !encontrado) {
//            if (beneficiarios.get(i).getId().equals(id)) {
//                beneficiario = beneficiarios.get(i);
//                encontrado = true;
//            }
//            i++;
//        }
//        if (encontrado) {
//            if (beneficiarios instanceof Articulo) {
//                dt = new DTArticulo(donacion.getId(), donacion.getFechaIngresada(), ((Articulo) donacion).getDescripcion(), ((Articulo) donacion).getPeso(), ((Articulo) donacion).getDimensiones());
//            } else {
//                dt = new DTAlimento(donacion.getId(), donacion.getFechaIngresada(), ((Alimento) donacion).getDescripcionProductos(), ((Alimento) donacion).getCantElementos());
//            }
//            return dt;
//        }
//        return dt;
//    }
//
//    // Modifica una donación.
//    public void modificarDonacion(DTDonacion dtDonacion, Integer id) {
//        Donacion donacion = null;
//        boolean encontrado = false;
//        int i = 0;
//
//        while (i < donaciones.size() && !encontrado) {
//            if (donaciones.get(i).getId().equals(id)) {
//                donacion = donaciones.get(i);
//                encontrado = true;
//            }
//            i++;
//        }
//        if (encontrado) {
//            if (donacion instanceof Articulo) {
//                if (dtDonacion instanceof DTArticulo) {
//                    donacion.setId(dtDonacion.getId());
//                    ((Articulo) donacion).setDescripcion(((DTArticulo) dtDonacion).getDescripcion());
//                    ((Articulo) donacion).setPeso(((DTArticulo) dtDonacion).getPeso());
//                    ((Articulo) donacion).setDimensiones(((DTArticulo) dtDonacion).getDimensiones());
//                }
//                // Retornar un error si no se cumple las condiciones.
//            } else {
//                if (dtDonacion instanceof DTAlimento) {
//                    donacion.setId(dtDonacion.getId());
//                    ((Alimento) donacion).setDescripcionProductos(((DTAlimento) dtDonacion).getDescripcionProductos());
//                    ((Alimento) donacion).setCantElementos(((DTAlimento) dtDonacion).getCantElementos());
//                }
//                // Retornar el error correspondiente.
//            }
//        }
//    }

}
