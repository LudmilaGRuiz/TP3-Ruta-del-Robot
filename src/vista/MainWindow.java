package vista;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;

public class MainWindow {

	public JFrame frame;
	private JTextField[][] textField;

	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void crearTabla(int filas, int columnas, Boolean[][] celdas) {
		textField = new JTextField[filas][columnas];
		frame.setLayout(new GridLayout(filas, columnas));
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (celdas[i][j]) {
					textField[i][j] = new JTextField(); // Inicializar el botón
					textField[i][j].setText(" 1");
					frame.add(textField[i][j]); // Agregar el botón al frame
				} else {
					textField[i][j] = new JTextField(); // Inicializar el botón
					textField[i][j].setText("-1");
					frame.add(textField[i][j]); // Agregar el botón al frame
				}
			}
		}
	}

	public void pintarTablero(ArrayList<Boolean> movimientos) {
		int x = 0, y = 0;
		for (int i = 0; i < movimientos.size(); i++) {
			if (movimientos.get(i)) {
				textField[x][y].setBackground(Color.GREEN); // Pintar de verde si se mueve a la derecha
				y++; // Movimiento a la derecha

			} else {
				textField[x][y].setBackground(Color.GREEN); // Pintar de verde si se mueve abajo
				x++; // Movimiento hacia abajo
			}
		}
		textField[x][y].setBackground(Color.GREEN); // Pintar de verde ultimo casillero
		repintar();
	}

	public void repintar() {
		frame.revalidate();
		frame.repaint();
	}
}
