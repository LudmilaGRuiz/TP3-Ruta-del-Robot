package model;

import java.util.ArrayList;

public class Instancia {
    private Tablero tablero;
    private int contadorIntentos;

    public Instancia(Tablero tablero) {
        this.tablero = tablero;
    }

    public Instancia(int filas, int columnas) {
        this.tablero = Tablero.aleatorio(filas, columnas);
    }

    public Resultado encontrarCaminoConResultado(boolean conPoda) {
        contadorIntentos = 0;
        Long startTime = System.currentTimeMillis();
        ArrayList<Boolean> camino = encontrarCaminoValido(conPoda);
        Long endTime = System.currentTimeMillis();
        return new Resultado(camino, (endTime - startTime), contadorIntentos);
    }

    public ArrayList<Boolean> encontrarCaminoValido(boolean conPoda) {
        contadorIntentos++;
        Robot robotAux = new Robot(tablero.getValorCasillero(0, 0));
        robotAux = moverRobotHastaElFinal(robotAux, conPoda);

        if (verificarValidezRobot(robotAux)) {
            if (robotAux.getSumaDeCasilleros() == 0) {
                return robotAux.getMovimientos();
            } else if (contadorIntentos > 5000) {
                return new ArrayList<Boolean>();
            }
        }
        return encontrarCaminoValido(conPoda);
    }

    public Robot moverRobotHastaElFinal(Robot robotMov, boolean conPoda) {
        int cantidad = tablero.getColumnas() + tablero.getFilas() - 2;
        try {
            for (int i = 0; i < cantidad; i++) {
                int casillerosRestantes = cantidad + 1 - robotMov.getCantidadDeMovimientos();
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

    private boolean siguientePasoEsValido(int sumaCasilleros, int casillerosRestantes) {
        return Math.abs(sumaCasilleros) <= casillerosRestantes;
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
