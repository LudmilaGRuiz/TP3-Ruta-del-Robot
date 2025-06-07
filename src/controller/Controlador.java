package controller;

import java.lang.reflect.Array;
import java.sql.Time;
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
        if ((filas + columnas) % 2 == 0) {
            throw new NumberFormatException("Las filas y columnas del tablero no deben ser ambas par o impar");
        }
        instancias.add(new Instancia(filas, columnas));
    }

    public void dibujarTabla() {
        // Ejecutar crearTabla en un hilo separado para evitar bloquear la interfaz de
        // usuario
        new Thread(new Runnable() {
            @Override
            public void run() {
                window.dibujarTabla(instancias.get(instancias.size() - 1).getFilas(),
                        instancias.get(instancias.size() - 1).getColumnas(),
                        instancias.get(instancias.size() - 1).getCeldas());
                window.repintar();
            }
        }).start();
    }

    public void encontrarCaminosValidos() {
        // Ejecutar encontrarCaminoValido en un hilo separado
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                	Instancia instancia = instancias.get(instancias.size() - 1);
                    Instancia.Resultado resultadoSinPoda =  instancia.encontrarCaminoConResultado(false);
                    Instancia.Resultado resultadoConPoda =  instancia.encontrarCaminoConResultado(true);
                    
                    window.pintarTablero(resultadoSinPoda.camino, false);
                    window.pintarTablero(resultadoConPoda.camino, true);
                    window.mostrarResultados(instancia.getFilas(), instancia.getColumnas(), 
                    						resultadoSinPoda.tiempo, resultadoConPoda.tiempo, 
                    						resultadoSinPoda.caminosExplorados, resultadoConPoda.caminosExplorados);     
                    window.repintar();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void cargarTablero(String ruta) {
        try {
            Tablero tablero = JsonManager.cargarTablero(ruta);
            instancias.add(new Instancia(tablero));
            dibujarTabla();
            window.repintar();
        } catch (Exception e) {
            e.printStackTrace();
            MainWindow.lanzarError("Error al cargar el tablero: " + e.getMessage());
        }
    }
    public Tablero getTableroActual() {
    	return instancias.get(instancias.size() -1 ).getTablero();
    }

    public Tablero getTablero(int id) {
        return instancias.get(id).getTablero();
    }
}
