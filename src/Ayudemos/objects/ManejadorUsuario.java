package Ayudemos.objects;

import Ayudemos.datatypes.DtBeneficiario;
import Ayudemos.datatypes.DtRepartidor;
import Ayudemos.datatypes.DtUsuario;

import java.util.ArrayList;
import java.util.List;

public class ManejadorUsuario {
    private static ManejadorUsuario instance = null;//
    private List<Usuario> usuarios = new ArrayList<>();

    private ManejadorUsuario() {
    }

    public static ManejadorUsuario getInstance() {
        if (instance == null) {
            instance = new ManejadorUsuario();
        }
        return instance;
    }

    // Retorna una lista datatypes de todas los usuarios del sistema.
    public List<DtUsuario> obtenerUsuarios() {
        List<DtUsuario> lista = new ArrayList<DtUsuario>();
        DtUsuario dt = null;
        for (Usuario u : usuarios) {
            // creamos el dt y lo añadimos a la lista que retornaremos al terminar.
            if (u instanceof Beneficiario ){
                dt = new DtBeneficiario(u.getNombre(), u.getMail(), ((Beneficiario) u).getDireccion(), ((Beneficiario) u).getFechaNacimiento(), ((Beneficiario) u).getEstado(), ((Beneficiario) u).getBarrio());
                lista.add(dt);
            } else {
                dt = new DtRepartidor(u.getNombre(), u.getMail(), ((Repartidor) u).getNumeroLicencia());
                lista.add(dt);
            }
        }
        return lista;
    }

    public List<DtBeneficiario> obtenerBeneficiarios() {
        List<DtBeneficiario> list = new ArrayList<DtBeneficiario>();
        for (Usuario u : usuarios) {
            if (u instanceof Beneficiario ){
                DtBeneficiario dtBeneficiario = new DtBeneficiario(u.getNombre(), u.getMail(), ((Beneficiario) u).getDireccion(), ((Beneficiario) u).getFechaNacimiento(), ((Beneficiario) u).getEstado(), ((Beneficiario) u).getBarrio());
                list.add(dtBeneficiario);
            }
        }
        return list;
    }

    public List<DtRepartidor> obtenerRepartidores() {
        List<DtRepartidor> list = new ArrayList<DtRepartidor>();
        for (Usuario u : usuarios) {
            if (u instanceof Repartidor ){
                DtRepartidor dtRepartidor = new DtRepartidor(u.getNombre(), u.getMail(), ((Repartidor) u).getNumeroLicencia());
                list.add(dtRepartidor);
            }
        }
        return list;
    }

    // Agrega donacion a la lista de usuarios existentes.
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public boolean verificarMail(String mail) {
        boolean existe = false;
        for(Usuario u : usuarios) {
            if(u.getMail().equals(mail)) {
                existe = true;
                break;
            }
        }
        return existe;
    }
    // Busca una donación por ID en la lista de usuarios y retorna la información en un dt.
//    public DTDonacion buscarBeneficiarioID(Integer id) {
//        DtBeneficiario dt = null;
//        Beneficiario beneficiario = null;
//        boolean encontrado = false;
//        int i = 0;
//
//        while (i < usuarios.size() && !encontrado) {
//            if (usuarios.get(i).getId().equals(id)) {
//                beneficiario = usuarios.get(i);
//                encontrado = true;
//            }
//            i++;
//        }
//        if (encontrado) {
//            if (usuarios instanceof Articulo) {
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
