package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelResultados extends JPanel {
    private JTable tablaResultados;

    public PanelResultados() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 150));
        tablaResultados = new JTable(new DefaultTableModel(
                new Object[]{"Tamaño", "Tiempo sin poda (ms)", "Tiempo con poda (ms)", "Caminos explorados"}, 0));
        tablaResultados.setEnabled(false);
        add(new JScrollPane(tablaResultados), BorderLayout.CENTER);
    }

    public void agregarResultado(int filas, int columnas, Long tiempoSinPoda, Long tiempoConPoda, int intentosSinPoda, int intentosConPoda) {
        DefaultTableModel model = (DefaultTableModel) tablaResultados.getModel();
        Object[] datos = new Object[]{
                filas + "x" + columnas,
                tiempoSinPoda,
                tiempoConPoda,
                intentosSinPoda + " / " + intentosConPoda
        };
        model.insertRow(0, datos);
    }

    public void limpiarResultados() {
        DefaultTableModel model = (DefaultTableModel) tablaResultados.getModel();
        model.setRowCount(0);
    }

    public JTable getTablaResultados() {
        return tablaResultados;
    }
}