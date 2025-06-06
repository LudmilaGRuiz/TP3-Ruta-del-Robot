package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBotones extends JPanel {
    private JButton btnCargarTablero;
    private JButton btnCrearTablero;
    private JButton btnResolver;
    private JButton btnResultados;
    private JButton btnStressTest;
    private MainWindow window;

    public PanelBotones(MainWindow windowM) {
        this.window = windowM;
        btnCargarTablero = new JButton("Seleccionar tablero");
        btnCrearTablero = new JButton("Crear tablero");
        btnResolver = new JButton("Resolver");
        btnResultados = new JButton("Resultados");
        btnStressTest = new JButton("Stress Test");
        //this.setForeground(Color.TRANSLUCENT);
        this.add(btnCargarTablero);
        this.add(btnCrearTablero);
        this.add(btnStressTest);
        this.add(btnResolver);
        this.add(btnResultados);

        btnStressTest.addActionListener((ActionEvent e) -> {
			MainWindow.lanzarError("No hace nada por ahora");
		});

        btnResultados.addActionListener((ActionEvent e) -> {
			MainWindow.lanzarError("No hace nada por ahora");
		});

        btnCargarTablero.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser("TablerosPredeterminados/");
            fileChooser.setDialogTitle("Seleccionar tablero");

            int userSelection = fileChooser.showOpenDialog(window.getFrame());
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = fileChooser.getSelectedFile();
                String rutaArchivo = archivoSeleccionado.getAbsolutePath();
                try {
                    window.getControlador().cargarTablero(rutaArchivo);
                } catch (Exception ex) {
                    MainWindow.lanzarError("Error al cargar el grafo: " + ex.getMessage());
                }
            }
        });

        btnCrearTablero.addActionListener(e -> {
            String filasStr = JOptionPane.showInputDialog(window.getFrame(), "Ingrese el número de filas:");
            String columnasStr = JOptionPane.showInputDialog(window.getFrame(), "Ingrese el número de columnas:");
            if (filasStr != null && columnasStr != null) {
                try {
                    int filas = Integer.parseInt(filasStr);
                    int columnas = Integer.parseInt(columnasStr);
                    window.getControlador().crearTablero(filas, columnas);
                    window.getControlador().dibujarTabla();
                } catch (NumberFormatException ex) {
                    MainWindow.lanzarError(ex.getMessage());
                }
            }
        });

        btnResolver.addActionListener(e -> {
            if (window.getTextField() == null || window.getTextField().length == 0) {
                MainWindow.lanzarError("Debe crear o cargar un tablero primero.");
            } else {
                encontrarCaminoValido();
            }
        });
    }

    private void encontrarCaminoValido() {
		pintarTableroBlanco();
		window.getControlador().encontrarCaminosValidos();
	}

	private void pintarTableroBlanco() {
		for (int i = 0; i < window.getTextField().length; i++) {
			for (int j = 0; j < window.getTextField()[i].length; j++) {
				window.getTextField()[i][j].setBackground(Color.WHITE); // Pintar de blanco
			}
		}
		window.repintar();
	}

}
