package Ayudemos.interfaces;

import Ayudemos.datatypes.DTDistribucion;
import Ayudemos.objects.Beneficiario;
import Ayudemos.objects.Donacion;

import java.util.List;

public interface IModificarDistribucion {
    DTDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion);
    void modificarDistribucion(DTDistribucion dtDistribucion);
    Beneficiario[] obtenerBeneficiarios();
    Donacion[] obtenerDonaciones();
}
