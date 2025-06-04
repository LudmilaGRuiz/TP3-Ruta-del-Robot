package vista;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
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
        JButton btnResolver = new JButton("Resolver");

        JPanel topPanel = new JPanel();
        topPanel.add(btnCargarTablero);
        topPanel.add(btnResolver);

//        btnCargarTablero.addActionListener(this::seleccionarTablero);
        btnResolver.addActionListener(this::encontrarCaminoValido);
        
        frame.add(topPanel, BorderLayout.NORTH);
        
        JButton btnResultados = new JButton("Resultados");
//        btnResultados.addActionListener(this::obtenerResultados);
        
        frame.add(btnResultados, BorderLayout.SOUTH);
        

		controlador.crearTablero(9, 10);
		controlador.dibujarTabla();
	}

	public void dibujarTabla(int filas, int columnas, Boolean[][] celdas) {
		textField = new JTextField[filas][columnas];
	    // Si ya había una tabla previa, la removemos
	    if (panelTablero != null) frame.remove(panelTablero);
	    
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
		frame.add(new JScrollPane(panelTablero), BorderLayout.CENTER);
		repintar();
	}

	private void encontrarCaminoValido(ActionEvent e) {
		controlador.encontrarCaminoValido(true);
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
}
