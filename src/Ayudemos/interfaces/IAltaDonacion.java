package Ayudemos.interfaces;

import Ayudemos.datatypes.DTDonacion;

public interface IAltaDonacion {
    boolean crearDonacion(DTDonacion dtDonacion);

    DTDonacion buscarDonacionID(DTDonacion dtDonacion);

    void editarDonacion(DTDonacion dtDonacion, Integer id);
}