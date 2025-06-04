import java.awt.EventQueue;

import controller.Controlador;
import vista.MainWindow;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Controlador controlador = new Controlador();
                    controlador.abrirVista();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}