package controller;

import java.util.ArrayList;

import model.Solver;
import vista.MainWindow;

public class Controlador {
    private Solver solver;
    private MainWindow window;

    public Controlador() {
    	solver = new Solver();
    }
    
    public void abrirVista() {
    	window = new MainWindow(this);
        window.frame.setVisible(true);
    } 

    public void crearTablero(int filas, int columnas) {
    	if((filas+columnas)%2 == 0) {
    		MainWindow.lanzarError("Las filas y columnas del tableros no deben ser ambas par o impar");
    		return;
    	}
    	
        solver.crearTablero(filas,columnas);
        dibujarTabla(filas,columnas,solver.getCeldas());
    }

    public void dibujarTabla(int filas, int columnas, Integer[][] celdas) {
        // Ejecutar crearTabla en un hilo separado para evitar bloquear la interfaz de
        // usuario
        new Thread(new Runnable() {
            @Override
            public void run() {
                window.dibujarTabla(filas, columnas, celdas);
            }
        }).start();
    }
    
 
    public void encontrarCaminoValido(boolean conPoda) {
    	ArrayList<Boolean> camino = solver.encontrarCaminoValido(conPoda);
    	window.pintarTablero(camino);
    }

	protected Solver getSolver() {
		return solver;
	}

	protected void setSolver(Solver solver) {
		this.solver = solver;
	}

	protected MainWindow getWindow() {
		return window;
	}

	protected void setWindow(MainWindow window) {
		this.window = window;
	}
}
