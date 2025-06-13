package vista;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class MainWindow {

	public JFrame frame;
	private Controlador controlador;
	private FondoConImagen fondoPanel;
	private PanelBotones topPanelBotones;
	private JTextField[][] tablero;
	private JPanel panelContenedor;
	private PanelResultados panelResultados;
	private ChartPanel chartPanel;

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

		topPanelBotones = new PanelBotones(this);
		fondoPanel = new FondoConImagen("homer.png");

		panelResultados = new PanelResultados();

		frame.add(topPanelBotones, BorderLayout.NORTH);
		frame.add(fondoPanel, BorderLayout.CENTER);
		frame.add(panelResultados, BorderLayout.SOUTH);
	}

	public void stressTest() {
		limpiarResultados();
		frame.remove(fondoPanel);
		if (chartPanel != null) {
			frame.remove(chartPanel);
		}
		if (panelContenedor != null) {
			frame.remove(panelContenedor);
		}

		JFreeChart chart = ChartFactory.createLineChart(
				"Comparación de intentos con y sin poda",
				"Instancia",
				"Intentos",
				controlador.dataGraficoStress(),
				PlotOrientation.VERTICAL, true, false, false);
		chartPanel = new ChartPanel(chart);
		frame.add(chartPanel, BorderLayout.CENTER);
		repintar();
	}

	public void dibujarTabla(int filas, int columnas, Boolean[][] celdas) {
		tablero = new JTextField[filas][columnas];
		verificarComponentes();
		JPanel panelTablero = new JPanel(new GridLayout(filas, columnas, 2, 2));

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tablero[i][j] = new JTextField(); // Inicializar el botón
				tablero[i][j].setHorizontalAlignment(JTextField.CENTER);
				tablero[i][j].setEditable(false);
				tablero[i][j].setText(celdas[i][j] ? "1" : "-1");
				panelTablero.add(tablero[i][j]); // Agregar el botón al frame
			}
		}
		// Crear contenedor y asignarle tamaño preferido
		panelContenedor = new JPanel(new BorderLayout());
		panelContenedor.setMinimumSize(new Dimension(900, 600));
		panelContenedor.setMaximumSize(new Dimension(1000, 700));

		JScrollPane scrollPaneTablero = new JScrollPane(panelTablero);
		panelContenedor.add(scrollPaneTablero, BorderLayout.CENTER);

		frame.add(panelContenedor, BorderLayout.CENTER);
		repintar();
	}

	private void verificarComponentes() {
		frame.remove(fondoPanel);
		if (chartPanel != null)
			frame.remove(chartPanel);
		if (panelContenedor != null)
			frame.remove(panelContenedor);
	}

	public void pintarTablero(ArrayList<Boolean> movimientos, boolean conPoda) {
		int x = 0, y = 0;
		tablero[x][y].setBackground(Color.YELLOW);

		for (Boolean movimiento : movimientos) {
			if (movimiento)
				y++;
			else
				x++;
			if (conPoda && tablero[x][y].getBackground() == Color.WHITE) {
				tablero[x][y].setBackground(Color.GREEN);
			} else if (tablero[x][y].getBackground() == Color.WHITE) {
				tablero[x][y].setBackground(Color.RED);
			} else {
				tablero[x][y].setBackground(Color.YELLOW);
			}
		}
		repintar();
	}

	public void mostrarResultados(int filas, int columnas, Long tiempoSinPoda, Long tiempoConPoda, int intentosSinPoda,
			int intentosConPoda) {
		panelResultados.agregarResultado(filas, columnas, tiempoSinPoda, tiempoConPoda, intentosSinPoda, intentosConPoda);
	}

	public void limpiarResultados() {
		panelResultados.limpiarResultados();
	}

	public JTable getTablaResultados() {
		return panelResultados.getTablaResultados();
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

	public Controlador getControlador() {
		return controlador;
	}

	public JTextField[][] getTextField() {
		return tablero;
	}
}
