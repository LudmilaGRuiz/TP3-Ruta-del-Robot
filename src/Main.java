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

                    // Ejecutar crearTabla en un hilo separado para evitar bloquear la interfaz de
                    // usuario
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            window.crearTabla(filas, columnas, controlador.getTablero().getCeldas());
                            window.repintar();
                        }
                    }).start();

                    // Ejecutar encontrarCaminoValido en un hilo separado
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000); // Esperar un segundo antes de buscar el camino
                                window.pintarTablero(controlador.encontrarCaminoValido());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}