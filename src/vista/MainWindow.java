package vista;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class MainWindow {

	public JFrame frame;
	private Controlador controlador;
	private JTextField[][] textField;
	private JPanel panelTablero;
	private JScrollPane scrollPaneTablero;
	private PanelBotones topPanel;
	private JTable tablaResultados;
	private FondoConImagen fondoPanel;
	private JPanel panelContenedor;

	public MainWindow(Controlador controlador) {
		this.controlador = controlador;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1000, 800);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Buscador de caminos en un tablero");
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.red);

		topPanel = new PanelBotones(this);
		fondoPanel = new FondoConImagen("homer.png");
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(fondoPanel, BorderLayout.CENTER);

		/*
		 * controlador.crearTablero(29, 30);
		 * controlador.dibujarTabla();
		 */
		tablaResultados = new JTable(new DefaultTableModel(
				new Object[] { "Tamaño", "Tiempo sin poda (ms)", "Tiempo con poda (ms)", "Caminos explorados" }, 0));
		tablaResultados.setEnabled(false);
		JPanel panelTablaResultados = new JPanel(new BorderLayout());
		panelTablaResultados.setPreferredSize(new Dimension(1000, 150)); // Limita la altura de la tabla
		panelTablaResultados.add(new JScrollPane(tablaResultados));

		frame.add(panelTablaResultados, BorderLayout.SOUTH);

	}

	public void dibujarTabla(int filas, int columnas, Boolean[][] celdas) {
		textField = new JTextField[filas][columnas];
		// Si ya había una tabla previa, la removemos
		frame.remove(fondoPanel);
		if (panelTablero != null && scrollPaneTablero != null) {
			frame.remove(panelContenedor);
		}
		panelTablero = new JPanel(new GridLayout(filas, columnas, 2, 2));

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				textField[i][j] = new JTextField(); // Inicializar el botón
				textField[i][j].setHorizontalAlignment(JTextField.CENTER);
				textField[i][j].setEditable(false);
				textField[i][j].setText(celdas[i][j] ? "1" : "-1");
				panelTablero.add(textField[i][j]); // Agregar el botón al frame
			}
		}
		// Crear contenedor y asignarle tamaño preferido
		panelContenedor = new JPanel(new BorderLayout());
		panelContenedor.setMinimumSize(new Dimension(900, 600));
		panelContenedor.setMaximumSize(new Dimension(1000, 700));

		scrollPaneTablero = new JScrollPane(panelTablero);
		panelContenedor.add(scrollPaneTablero, BorderLayout.CENTER);

		frame.add(panelContenedor, BorderLayout.CENTER);
		repintar();
	}

	public void pintarTablero(ArrayList<Boolean> movimientos, boolean conPoda) {
		int x = 0, y = 0;
		textField[x][y].setBackground(Color.YELLOW); // Pintar primera celda de amarillo

		for (Boolean movimiento : movimientos) {
			if (movimiento)
				y++; // Movimiento a la derecha
			else
				x++; // Movimiento hacia abajo
			if (conPoda && textField[x][y].getBackground() == Color.WHITE) {
				textField[x][y].setBackground(Color.GREEN); // Pintar de verde primer casillero
			} else if (textField[x][y].getBackground() == Color.WHITE) {
				textField[x][y].setBackground(Color.RED); // Pintar de rojo primer casillero
			} else {
				textField[x][y].setBackground(Color.YELLOW); // Pintar de amarillo los demás casilleros
			}
		}
		repintar();
	}

	public void mostrarResultados(int filas, int columnas, Long tiempoSinPoda, Long tiempoConPoda, int intentosSinPoda,
			int intentosConPoda) {
		DefaultTableModel model = (DefaultTableModel) tablaResultados.getModel();
//		model.setRowCount(0);
		Object[] datos = new Object[] {
				filas + "x" + columnas,
				tiempoSinPoda,
				tiempoConPoda,
				intentosSinPoda + " / " + intentosConPoda
		};
		model.addRow(datos);
		
	}

	public void repintar() {
		frame.revalidate();
		frame.repaint();
	}

	public static void lanzarError(String mensaje) {
		JOptionPane.showMessageDialog(
				null,
				mensaje,
				"Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JPanel getPanelTablero() {
		return panelTablero;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public JTextField[][] getTextField() {
		return textField;
	}
}
