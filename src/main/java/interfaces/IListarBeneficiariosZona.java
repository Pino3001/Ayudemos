package interfaces;

import datatypes.DtBeneficiario;
import types.Barrio;

import java.util.List;

public interface IListarBeneficiariosZona {
    /**
     * Lista los beneficiarios que pertenecen a una zona específica.
     *
     * @param barrio La zona o barrio por el cual se filtrarán los beneficiarios.
     * @return Una lista de `DtBeneficiario` que pertenecen al barrio especificado.
     */
    List<DtBeneficiario> listarBeneficiariosPorZona(Barrio barrio);
}
