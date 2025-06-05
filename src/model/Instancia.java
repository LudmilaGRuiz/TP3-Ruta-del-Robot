package model;

import java.util.ArrayList;

public class Instancia {
    private Tablero tablero;
    private int contadorIntentos;

    public Instancia(Tablero tablero) {
        this.tablero = tablero;
        this.contadorIntentos = 0;
    }

    public Instancia(int filas, int columnas) {
        this.tablero = new Tablero(filas, columnas);
        this.tablero.tableroAleatorio();
        this.contadorIntentos = 0;
        System.out.println("Instancia creada con un tablero de " + filas + " filas y " + columnas + " columnas.");
    }

    public ArrayList<Boolean> encontrarCaminoValido(boolean conPoda) {
        Robot robotAux = new Robot(tablero.getValorCasillero(0, 0));
        robotAux = moverRobotHastaElFinal(robotAux, conPoda);

        if (verificarValidezRobot(robotAux)) {
            if (robotAux.getSumaDeCasilleros() == 0) {
                System.out.print(
                        "Camino valido encontrado en " + contadorIntentos + " intentos, con suma de casilleros 0");
                return robotAux.getMovimientos();
            } else if (contadorIntentos > 5000) {
                System.out.println("No se encontro un camino valido en " + contadorIntentos + " intentos");
                return new ArrayList<Boolean>();
            }
        }
        contadorIntentos++;
        return encontrarCaminoValido(conPoda);
    }

    public Robot moverRobotHastaElFinal(Robot robotMov, boolean conPoda) {
        int cantidad = tablero.getColumnas() + tablero.getFilas() - 2;
        try {
            for (int i = 0; i < cantidad; i++) {
                if (conPoda && !siguientePasoEsValido(robotMov.getSumaDeCasilleros(), cantidad + 1))
                    return robotMov;

                robotMov.movimientoAleatorio();
                robotMov.sumaDeCasilleros(tablero.getValorCasillero(robotMov.getX(), robotMov.getY()));
            }
            return robotMov;
        } catch (Exception e) {
            return robotMov;
        }
    }

    public Boolean verificarValidezRobot(Robot robot) {
        if (robot.getSumaDeCasilleros() == 0 && tablero.getFilas() - 1 == robot.getX()
                && tablero.getColumnas() - 1 == robot.getY()) {
            return true;
        }
        return false;
    }

    private boolean siguientePasoEsValido(int sumaDeCargas, int cantCasilleros) {
    	return -cantCasilleros/2 <= sumaDeCargas &&  sumaDeCargas <= cantCasilleros/2;
	}


    public Tablero getTablero() {
        return tablero;
    }

    public int getFilas() {
        return tablero.getFilas();
    }

    public int getColumnas() {
        return tablero.getColumnas();
    }

    public Boolean[][] getCeldas() {
        return tablero.getCeldas();
    }
}
