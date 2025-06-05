package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;

public class MainWindow {

	public JFrame frame;
	private Controlador controlador;
	private JTextField[][] textField;
	private JPanel panelTablero;
	private JScrollPane scrollPaneTablero;

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
		
        JButton btnCargarTablero = new JButton("Seleccionar tablero");
		JButton btnCrearTablero = new JButton("Crear tablero");
        JButton btnResolver = new JButton("Resolver");

        JPanel topPanel = new JPanel();
        topPanel.add(btnCargarTablero);
		topPanel.add(btnCrearTablero);
        topPanel.add(btnResolver);

//        btnCargarTablero.addActionListener(this::seleccionarTablero);
        btnResolver.addActionListener(this::encontrarCaminoValido);
        
		btnCargarTablero.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser("TablerosPredeterminados/");
			fileChooser.setDialogTitle("Seleccionar tablero");
			
			int userSelection = fileChooser.showOpenDialog(frame);
			if (userSelection == JFileChooser.APPROVE_OPTION) {
			File archivoSeleccionado = fileChooser.getSelectedFile();
			String rutaArchivo = archivoSeleccionado.getAbsolutePath();
			try {
				controlador.cargarTablero(rutaArchivo);
			} catch (Exception ex) {
				lanzarError("Error al cargar el grafo: " + ex.getMessage());
			}
		}});

        frame.add(topPanel, BorderLayout.NORTH);
        
		JButton btnResultados = new JButton("Resultados");
		btnResultados.addActionListener(e -> controlador.guardarTablero());
		btnCrearTablero.addActionListener(e -> {
			String filasStr = JOptionPane.showInputDialog(frame, "Ingrese el número de filas:");
			String columnasStr = JOptionPane.showInputDialog(frame, "Ingrese el número de columnas:");
			if (filasStr != null && columnasStr != null) {
				try {
					int filas = Integer.parseInt(filasStr);
					int columnas = Integer.parseInt(columnasStr);
					controlador.crearTablero(filas, columnas);
					controlador.dibujarTabla();
				} catch (NumberFormatException ex) {
					lanzarError("Por favor, ingrese números válidos.");
				}
			}
		});
		
		frame.add(btnResultados, BorderLayout.SOUTH);
        
/* 		controlador.crearTablero(29, 30);
		controlador.dibujarTabla(); */
	}

	public void dibujarTabla(int filas, int columnas, Boolean[][] celdas) {
		textField = new JTextField[filas][columnas];
	    // Si ya había una tabla previa, la removemos
	    if (panelTablero != null && scrollPaneTablero != null) {
	        frame.remove(scrollPaneTablero);
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
		scrollPaneTablero = new JScrollPane(panelTablero);
		frame.add(scrollPaneTablero, BorderLayout.CENTER);
		repintar();
	}

	private void encontrarCaminoValido(ActionEvent e) {
		pintarTableroBlanco();
		controlador.encontrarCaminoValido(true);
	}

	private void pintarTableroBlanco() {
		for (int i = 0; i < textField.length; i++) {
			for (int j = 0; j < textField[i].length; j++) {
				textField[i][j].setBackground(Color.WHITE); // Pintar de blanco
			}
		}
		repintar();
	}

	public void pintarTablero(ArrayList<Boolean> movimientos) {
		int x = 0, y = 0;
		textField[x][y].setBackground(Color.GREEN); // Pintar de verde primer casillero
		for (Boolean movimiento : movimientos) {
			if (movimiento)
				y++; // Movimiento a la derecha
			else 
				x++; // Movimiento hacia abajo
			textField[x][y].setBackground(Color.GREEN); // Pintar de verde
		}
		repintar();
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
}
