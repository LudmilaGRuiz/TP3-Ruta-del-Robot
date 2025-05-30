import java.awt.EventQueue;

import controller.Controlador;
import vista.MainWindow;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int filas = 9; // Número de filas
                    int columnas = 10; // Número de columnas
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                    Controlador controlador = new Controlador();
                    controlador.crearTablero(filas, columnas);
                    window.crearTabla(filas, columnas, controlador.getTablero().getCeldas());
                    // Ejecutar encontrarCaminoValido en un hilo separado
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            controlador.encontrarCaminoValido();
                        }
                    }).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}