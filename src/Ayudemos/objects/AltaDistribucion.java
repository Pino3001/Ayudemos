package Ayudemos.objects;

import Ayudemos.datatypes.DTDonacion;
import Ayudemos.interfaces.IAltaDistribucion;
import Ayudemos.types.DTFechaHora;
import Ayudemos.types.DtBeneficiario;
import Ayudemos.types.EstadoDistribucion;

import java.util.ArrayList;
import java.util.List;

// Controlador Alta Distribución.
public class AltaDistribucion implements IAltaDistribucion {
    public AltaDistribucion() {
        super();
    }

    // Crea una nueva distribución.
    @Override
    public void crearDistribucion(Beneficiario beneficiario,
                                  List<Donacion> donaciones,
                                  DTFechaHora fechaPreparacion,
                                  DTFechaHora fechaEntrega,
                                  EstadoDistribucion estado) {
        // Creamos la nueva distribución, al crearse ya apunta al beneficiario pasado por parámetro.
        Distribucion nuevaDist = new Distribucion(fechaPreparacion, fechaEntrega, estado, beneficiario);
        // Agregamos la nueva distribución a cada una de las lista de distribuciones de las donaciones pasadas por parámetro.
        for (Donacion d : donaciones) {
            // Para cada donación le agregamos la nueva distribución a su colección.
            d.addDistribucion(nuevaDist);
        }
    }

    // Retornar lista de todos los beneficiarios del sistema para cargar el combobox.
    @Override
    public List<DtBeneficiario> obtenerListaDtBeneficiarios() {
        List<DtBeneficiario> beneficiarios = new ArrayList<DtBeneficiario>();
        ManejadorBeneficiario mb = ManejadorBeneficiario.getInstance();
        beneficiarios = mb.obtenerBeneficiarios();
        return beneficiarios;
    }

    // Retornar lista de todas las donaciones del sistema para cargar el combobox.
    @Override
    public List<DTDonacion> obtenerListaDtDonaciones() {
        List<DTDonacion> donaciones = new ArrayList<DTDonacion>();
        // Obtenemos los datatypes de todas las donaciones del sistema.
        ManejadorDonacion md = ManejadorDonacion.getInstance();
        donaciones = md.obtenerDonaciones();
        return donaciones;
    }
}
