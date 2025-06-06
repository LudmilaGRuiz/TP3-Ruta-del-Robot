package model;

import java.util.ArrayList;

public class Solver {
	private Tablero tablero;
	private int contadorIntentos;

	public void crearTablero(int filas, int columnas) {
		this.contadorIntentos=0;
        this.tablero = new Tablero(filas, columnas);
        tablero.tableroAleatorio();
	}

    public ArrayList<Boolean> encontrarCaminoValido(boolean conPoda) {
    	contadorIntentos=0;
        return encontrarCaminoValido(tablero, conPoda);             
    }
    private ArrayList<Boolean> encontrarCaminoValido(Tablero tablero, boolean conPoda) {
        Robot robotAux = new Robot(tablero.getValorCasillero(0, 0));
        robotAux = moverRobotHastaElFinal(robotAux, conPoda);

        if (verificarValidezRobot(robotAux)) {
            if (robotAux.getSumaDeCasilleros() == 0) {
                System.out.println("Camino valido encontrado en " + contadorIntentos + " intentos, con suma de casilleros 0");
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
        int casillerosRestantes = cantidad + 1 - robotMov.getCantidadDeMovimientos();
        try {
            for (int i = 0; i < cantidad; i++) {
            	if (conPoda && !siguientePasoEsValido(robotMov.getSumaDeCasilleros(),casillerosRestantes)) 
            		return robotMov; 
            	
                robotMov.movimientoAleatorio();
                robotMov.sumaDeCasilleros(tablero.getValorCasillero(robotMov.getX(), robotMov.getY()));
            }
            return robotMov;
        } catch (Exception e) {
            return robotMov;
        }
    }

    private boolean siguientePasoEsValido(int sumaDeCargas, int casillerosRestantes) {
    	return Math.abs(sumaDeCargas) <= casillerosRestantes;
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
	public Boolean[][] getCeldas() {
		return tablero.getCeldas();
	}


}
