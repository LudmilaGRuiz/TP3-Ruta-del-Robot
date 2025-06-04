package controller;

import java.util.ArrayList;

import model.Robot;
import model.Solver;
import model.Tablero;
import vista.MainWindow;

public class Controlador {
    private Tablero tablero;
    private MainWindow window;
    private int contadorIntentos;

    public Controlador() {
    }
    
    public void abrirVista() {
    	window = new MainWindow(this);
        window.frame.setVisible(true);
    } 

    public void crearTablero(int filas, int columnas) {
    	if((filas+columnas)%2 == 0) MainWindow.lanzarError("Las filas y columnas del tableros no deben ser ambas par o impar");
    	
        this.tablero = new Tablero(filas, columnas);
        tablero.tableroAleatorio();
    }

    public void dibujarTabla() {
        // Ejecutar crearTabla en un hilo separado para evitar bloquear la interfaz de
        // usuario
        new Thread(new Runnable() {
            @Override
            public void run() {
                window.dibujarTabla(tablero.getFilas(), tablero.getColumnas(), tablero.getCeldas());
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
                    Thread.sleep(1000); // Esperar un segundo antes de buscar el camino
                    window.pintarTablero(encontrarCaminoValido(tablero, conPoda));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    private ArrayList<Boolean> encontrarCaminoValido(Tablero tablero, boolean conPoda) {
        Robot robotAux = new Robot(tablero.getValorCasillero(0, 0));
        robotAux = moverRobotHastaElFinal(robotAux, conPoda);

        if (verificarValidezRobot(robotAux)) {
            if (robotAux.getSumaDeCasilleros() == 0) {
                System.out.print("Camino valido encontrado en " + contadorIntentos + " intentos, con suma de casilleros 0");
                return robotAux.getMovimientos();
            } else if (contadorIntentos > 5000){
                System.out.println("No se encontro un camino valido en " + contadorIntentos + " intentos");
                return new ArrayList<Boolean>();
            }
        }
        contadorIntentos++;
        return encontrarCaminoValido(tablero, conPoda);
    }
    
    public Robot moverRobotHastaElFinal(Robot robotMov, boolean conPoda) {
        int cantidad = tablero.getColumnas() + tablero.getFilas() - 2;
        try {
            for (int i = 0; i < cantidad; i++) {
            	if (conPoda && !siguientePasoEsValido(robotMov.getSumaDeCasilleros(),cantidad+1)) 
            		return robotMov; 
            	
                robotMov.movimientoAleatorio();
                robotMov.sumaDeCasilleros(tablero.getValorCasillero(robotMov.getX(), robotMov.getY()));
            }
            return robotMov;
        } catch (Exception e) {
            return robotMov;
        }
    }

    private boolean siguientePasoEsValido(int sumaDeCargas, int cantCasilleros) {
    	return -cantCasilleros/2 <= sumaDeCargas &&  sumaDeCargas <= cantCasilleros/2;
	}

	public Boolean verificarValidezRobot(Robot robot) {
        if (robot.getSumaDeCasilleros() == 0 && tablero.getFilas() - 1 == robot.getX()
                && tablero.getColumnas() - 1 == robot.getY()) {
            return true;
        }
        return false;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
