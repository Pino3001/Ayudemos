package interfaces;

import datatypes.DTDonacion;
import datatypes.DtBeneficiario;
import datatypes.DtDistribucion;

import java.util.List;

public interface IModificarDistribucion {
    DtDistribucion buscarDistribucion(String emailBeneficiario, int idDonacion);
    void modificarDistribucion(DtDistribucion dtDistribucion);
    DtBeneficiario[] obtenerBeneficiarios();
    DTDonacion[] obtenerDonaciones();
}
