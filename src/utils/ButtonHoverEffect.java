package utils;
import javax.swing.JButton;
import javax.swing.event.MouseInputAdapter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;

public class ButtonHoverEffect {

    private static ButtonHoverEffect instance;

    private ButtonHoverEffect() {}

    public static ButtonHoverEffect getInstance() {
        if (instance == null) {
            instance = new ButtonHoverEffect();
        }
        return instance;
    }

    public void applyHoverEffect(JButton boton) {
        boton.addMouseListener(new MouseInputAdapter() {
            Color colorOriginal = boton.getBackground();
            Color colorOscurecido = oscurecerColor(colorOriginal, 0.8);

            private Color oscurecerColor(Color color, double factor) {
                int red = (int) Math.max(0, color.getRed() * factor);
                int green = (int) Math.max(0, color.getGreen() * factor);
                int blue = (int) Math.max(0, color.getBlue() * factor);
                return new Color(red, green, blue);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                boton.setBackground(colorOscurecido);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setCursor(Cursor.getDefaultCursor());
                boton.setBackground(colorOriginal);
            }
        });
    }
}
