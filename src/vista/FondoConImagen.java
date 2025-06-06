package vista;
import javax.swing.*;
import java.awt.*;

public class FondoConImagen extends JPanel {
    private Image imagenFondo;

    public FondoConImagen(String rutaImagen) {
        // Cargar la imagen desde un archivo o recurso
        imagenFondo = new ImageIcon(rutaImagen).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibuja la imagen escalada al tamaño del panel
        g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
    }
}
