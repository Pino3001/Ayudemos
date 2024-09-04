package objects;

import datatypes.DtBeneficiario;
import interfaces.IListarBeneficiariosZona;
import types.Barrio;

import java.util.List;

public class ListarBeneficiariosZona implements IListarBeneficiariosZona {

    private ManejadorUsuario manejadorUsuario;

    public ListarBeneficiariosZona() {
        // Asegúrate de que esta línea esté presente para inicializar el manejador.
        this.manejadorUsuario = ManejadorUsuario.getInstance();
    }

    @Override
    public List<DtBeneficiario> listarBeneficiariosPorZona(Barrio barrio) {
        // Verifica si el manejadorUsuario no es null
        if (manejadorUsuario == null) {
            throw new IllegalStateException("ManejadorUsuario no ha sido inicializado.");
        }
        return manejadorUsuario.obtenerBeneficiariosPorZona(barrio);
    }
}