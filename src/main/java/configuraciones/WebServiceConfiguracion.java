package configuraciones;

import java.util.HashMap;

public class WebServiceConfiguracion {
    private HashMap<String, String> configs;

    // Constructor con configuraciones hardcodeadas
    public WebServiceConfiguracion() {
        configs = new HashMap<>();

        // Aquí defines directamente las configuraciones
        configs.put("#WS_IP", "localhost");  // Dirección IP hardcodeada como localhost
        configs.put("#WS_PORT", "3000");      // Puerto hardcodeado
    }

    // Método para obtener la configuración deseada
    public String getConfigOf(String nombre) {
        return configs.get(nombre);
    }
}