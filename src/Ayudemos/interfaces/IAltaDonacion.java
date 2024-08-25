package Ayudemos.interfaces;

import Ayudemos.datatypes.DTAlimento;
import Ayudemos.datatypes.DTArticulo;
import Ayudemos.datatypes.DTDonacion;

import java.util.List;

public interface IAltaDonacion {
    boolean crearDonacion(DTDonacion dtDonacion);

    DTDonacion buscarDonacionID(DTDonacion dtDonacion);

    void editarDonacion(DTDonacion dtDonacion, Integer id);

    List<DTAlimento> listarAlimentos();

    List<DTArticulo> listarArticulos();
}