package controller;

import java.util.ArrayList;

import model.Robot;
import model.Tablero;

public class Controlador {
    private Tablero tablero;

    public Controlador() {
    }

    public void crearTablero(int filas, int columnas) {
        this.tablero = new Tablero(filas, columnas);
        tablero.tableroAleatorio();
    }

    public Robot moverRobotHastaElFinal(Robot robotMov) {
        int cantidad = tablero.getColumnas() + tablero.getFilas() - 2;
        try {
            for (int i = 0; i < cantidad; i++) {
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

    public ArrayList<Boolean> encontrarCaminoValido() {
        return encontrarCaminoValido(tablero, 1);
    }

    public ArrayList<Boolean> encontrarCaminoValido(Tablero tablero, int contador) {
        Robot robotAux = new Robot(tablero.getValorCasillero(0, 0));
        robotAux = moverRobotHastaElFinal(robotAux);

        if (verificarValidezRobot(robotAux) || contador == 10000) {
            if (robotAux.getSumaDeCasilleros() == 0) {
                System.out.print("Camino valido encontrado en " + contador + " intentos, con suma de casilleros 0");
                return robotAux.getMovimientos();
            } else {
                System.out.println("No se encontro un camino valido en " + contador + " intentos");
                return new ArrayList<Boolean>();
            }
        }
        return encontrarCaminoValido(tablero, contador + 1);
    }

    public Tablero getTablero() {
        return tablero;
    }
}
