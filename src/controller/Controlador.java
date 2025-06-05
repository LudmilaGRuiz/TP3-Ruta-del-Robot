package controller;

import java.util.ArrayList;

import model.Instancia;
import model.Tablero;
import utilidades.JsonManager;
import vista.MainWindow;

public class Controlador {
    private ArrayList<Instancia> instancias;
    private MainWindow window;

    public Controlador() {
        this.instancias = new ArrayList<Instancia>();
    }

    public void abrirVista() {
        window = new MainWindow(this);
        window.frame.setVisible(true);
    }

    public void crearTablero(int filas, int columnas) {
        if ((filas + columnas) % 2 == 0)
            MainWindow.lanzarError("Las filas y columnas del tableros no deben ser ambas par o impar");

        instancias.add(new Instancia(filas, columnas));
    }

    public void dibujarTabla() {
        // Ejecutar crearTabla en un hilo separado para evitar bloquear la interfaz de
        // usuario
        new Thread(new Runnable() {
            @Override
            public void run() {
                window.dibujarTabla(instancias.get(instancias.size()-1).getFilas(), instancias.get(instancias.size()-1).getColumnas(),
                        instancias.get(instancias.size()-1).getCeldas());
                window.repintar();
            }
        }).start();
    }

    public void encontrarCaminoValido(boolean conPoda) {
        // Ejecutar encontrarCaminoValido en un hilo separado
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10); // Esperar un segundo antes de buscar el camino
                    window.pintarTablero(instancias.get(instancias.size()-1).encontrarCaminoValido(conPoda));
                    window.repintar();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void guardarTablero() {
        try {
            JsonManager.guardarTablero(instancias.get(0).getTablero(), "TablerosPredeterminados/tablero.json");
        } catch (Exception e) {
            e.printStackTrace();
            MainWindow.lanzarError("Error al guardar el tablero: " + e.getMessage());
        }
    }

    public void cargarTablero(String ruta) {
        try {
            Tablero tablero = JsonManager.cargarTablero(ruta);
            instancias.add(new Instancia(tablero));
            this.window.dibujarTabla(tablero.getFilas(), tablero.getColumnas(), tablero.getCeldas());
        } catch (Exception e) {
            e.printStackTrace();
            MainWindow.lanzarError("Error al cargar el tablero: " + e.getMessage());
        }
    }

    public Tablero getTablero(int id) {
        return instancias.get(id).getTablero();
    }
}
