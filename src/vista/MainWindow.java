package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

public class MainWindow {

	public JFrame frame;
	private JTable table;

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
		
		table = new JTable();
		frame.getContentPane().add(table, BorderLayout.CENTER);
	}
	public DefaultTableModel crearTabla (int filas, int columnas, Boolean[][] celdas) {
		DefaultTableModel model = new DefaultTableModel(filas, columnas);
		table.setModel(model);
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (celdas[i][j]) {
					model.setValueAt("1", i, j); // Asignar "1" para celdas activas
				} else {
					model.setValueAt("-1", i, j); // Asignar "-1" para celdas inactivas
				}
			}
		}
		return model;
	}
}
