package interfaces;

import datatypes.DTAlimento;
import datatypes.DTArticulo;
import datatypes.DTDonacion;

import java.util.List;

public interface IControladorDonacion {
    boolean crearDonacion(DTDonacion dtDonacion);

    DTDonacion buscarDonacionID(DTDonacion dtDonacion);

    void editarDonacion(DTDonacion dtDonacion, Integer id);

    List<DTAlimento> listarAlimentos();

    List<DTArticulo> listarArticulos();

    List<DTDonacion> listarDonaciones();
}