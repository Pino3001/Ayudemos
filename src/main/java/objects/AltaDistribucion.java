package objects;

import datatypes.DTDonacion;
import interfaces.IAltaDistribucion;
import types.DTFechaHora;
import datatypes.DtBeneficiario;
import types.EstadoDistribucion;

import java.util.Date;
import java.util.List;

// Controlador Alta Distribución.
public class AltaDistribucion implements IAltaDistribucion {
    public AltaDistribucion() {
        super();//
    }

    // Crea una nueva distribución.
    @Override
    public void crearDistribucion(Beneficiario beneficiario,
                                  Donacion donacion,
                                  Date fechaPreparacion,
                                  Date fechaEntrega,
                                  EstadoDistribucion estado) {
        // Creamos la nueva distribución, al crearse ya apunta al beneficiario y a la donacion pasados por parámetro.
        Distribucion nuevaDist = new Distribucion(fechaPreparacion, fechaEntrega, estado, donacion, beneficiario);
        // Vinculamos la nueva distribución a la lista de distribuciones de la donación y el beneficario.
        donacion.addDistribucion(nuevaDist);
        beneficiario.addDistribucion(nuevaDist);
        // !!!!! SI IMPLEMENTAMOS UN MANEJADOR DE DISTRIBUCIONES ACA TENDRIAMOS QUE HACER UN PUSH A ESE MANEJADOR.
    }

    @Override
    public void crearDistribucion(Beneficiario beneficiario, Donacion donacion, DTFechaHora fechaPreparacion, DTFechaHora fechaEntrega, EstadoDistribucion estado) {

    }

    // Retornar lista de todos los beneficiarios del sistema para cargar el combobox.
    @Override
    public List<DtBeneficiario> obtenerListaDtBeneficiarios() {
        List<DtBeneficiario> beneficiarios;
        ManejadorUsuario mu = ManejadorUsuario.getInstance();
        beneficiarios = mu.obtenerBeneficiarios();
        return beneficiarios;
    }

    // Retornar lista de todas las donaciones del sistema para cargar el combobox.
    @Override
    public List<DTDonacion> obtenerListaDtDonaciones() {
        List<DTDonacion> donaciones;
        // Obtenemos los datatypes de todas las donaciones del sistema.
        ManejadorDonacion md = ManejadorDonacion.getInstance();
        donaciones = md.obtenerDonaciones();
        return donaciones;
    }
}
