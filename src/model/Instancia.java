package model;

import java.util.ArrayList;

public class Instancia {
    private Tablero tablero;
    private int contadorIntentos;

    public Instancia(Tablero tablero) {
        this.tablero = tablero;
    }

    public Instancia(int filas, int columnas) {
        this.tablero = new Tablero(filas, columnas);
        this.tablero.tableroAleatorio();
        System.out.println("Instancia creada con un tablero de " + filas + " filas y " + columnas + " columnas.");
    }

    public ArrayList<Boolean> encontrarCaminoValido(boolean conPoda){
        contadorIntentos = 0;
        return encontrarCaminoValido(conPoda, contadorIntentos);
    }

    public ArrayList<Boolean> encontrarCaminoValido(boolean conPoda, int contador) {
        int contadorIntentos = contador+1;
        Robot robotAux = new Robot(tablero.getValorCasillero(0, 0));
        robotAux = moverRobotHastaElFinal(robotAux, conPoda);

        if (verificarValidezRobot(robotAux)) {
            if (robotAux.getSumaDeCasilleros() == 0) {
                System.out.println(
                        "Camino valido encontrado en " + contadorIntentos + " intentos, con suma de casilleros 0");
                return robotAux.getMovimientos();
            } else if (contadorIntentos > 5000) {
                System.out.println("No se encontro un camino valido en " + contadorIntentos + " intentos");
                return new ArrayList<Boolean>();
            }
        }
        return encontrarCaminoValido(conPoda, contadorIntentos);
    }

    public Robot moverRobotHastaElFinal(Robot robotMov, boolean conPoda) {
        int cantidad = tablero.getColumnas() + tablero.getFilas() - 2;
        try {
            for (int i = 0; i < cantidad; i++) {
                int casillerosRestantes = cantidad+1 - robotMov.getCantidadDeMovimientos();
                if (conPoda && !siguientePasoEsValido(robotMov.getSumaDeCasilleros(), casillerosRestantes))
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

    private boolean siguientePasoEsValido(int suma, int casillerosRestantes) {
        return Math.abs(suma) <= casillerosRestantes;
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
