package types;

import java.awt.Color;

public enum Barrio {
    CIUDAD_VIEJA(new Color(165, 53, 53)),  // Color rojo
    CORDON(new Color(60, 175, 60)),        // Color verde
    PARQUE_RODO(new Color(62, 62, 211)),   // Color azul
    CENTRO(new Color(216, 216, 37)),      // Color amarillo
    PALERMO(new Color(146, 83, 170));     // Color naranja

    private final Color color;  // Propiedad de color para cada barrio

    // Constructor del enum para inicializar el color
    Barrio(Color color) {
        this.color = color;
    }

    // Método getter para obtener el color del barrio
    public Color getColor() {
        return color;
    }
}