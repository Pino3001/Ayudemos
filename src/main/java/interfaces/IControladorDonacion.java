package interfaces;

import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;
import excepciones.IngresoIncorrectoExeption;
import excepciones.NoEncontradoExeption;

import java.util.List;

public interface IControladorDonacion {
    boolean crearDonacion(DTDonacion dtDonacion);

    DTDonacion buscarDonacionID(Integer id)throws NoEncontradoExeption;

    void editarDonacion(DTDonacion dtDonacion, Integer id)throws IngresoIncorrectoExeption;

    List<DTAlimento> listarAlimentos();

    List<DTArticulo> listarArticulos();

    List<DTDonacion> listarDonaciones();
}