package gui.componentes;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

public class ColorUtil {
    private static Properties properties = new Properties();
    private static final Random random = new Random();


    static {
        try (InputStream input = ColorUtil.class.getResourceAsStream("/styles/colors.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find colors.properties");
                throw new RuntimeException("Sorry, unable to find colors.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Color getColor(String key) {
        String colorHex = properties.getProperty(key);
        if (colorHex != null) {
            try {
                return Color.decode(colorHex);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return Color.BLACK; // Default color
    }

    public static Color generateRandomColor() {
        Random random = new Random();
        float hue = random.nextFloat(); // Hue (0.0 to 1.0)
        float saturation = 0.5f + (0.5f * random.nextFloat()); // Saturation (0.5 to 1.0)
        float brightness = 0.5f + (0.5f * random.nextFloat()); // Brightness (0.5 to 1.0)
        return Color.getHSBColor(hue, saturation, brightness);
    }
}