package controller;

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
        for (int i = 0; i < cantidad; i++) {
            robotMov.movimientoAleatorio();
            robotMov.sumaDeCasilleros(tablero.getValorCasillero(robotMov.getX(), robotMov.getY()));
        }
        return robotMov;
    }

    public Boolean verificarValidezRobot(Robot robot) {
        if (robot.getSumaDeCasilleros() == 0 && tablero.getFilas() - 1 == robot.getX()
                && tablero.getColumnas() - 1 == robot.getY()) {
            return true;
        }
        return false;
    }

    public void encontrarCaminoValido() {
        encontrarCaminoValido(tablero, 0, 0);
    }

    public void encontrarCaminoValido(Tablero tablero, int contador, int contadorCorrectos) {
        Robot robotAux = new Robot(tablero.getValorCasillero(0, 0));
        robotAux = moverRobotHastaElFinal(robotAux);

        if (verificarValidezRobot(robotAux) || contadorCorrectos == 100) {
            if (robotAux.getSumaDeCasilleros() == 0) {
                int contadorDerecha = 0;
                int contadorAbajo = 0;
                for (Boolean movimiento : robotAux.getMovimientos()) {
                    if (movimiento) {
                        contadorDerecha++;
                    } else {
                        contadorAbajo++;
                    }
                }
                System.out.print("Camino valido encontrado en " + contador + " movimientos");
                System.out.println(
                        "\nMovimientos realizados: " + contadorDerecha + " Derecha, " + contadorAbajo + " Abajo");
            } else {
                System.out.println("No se encontro un camino valido en " + contador + " movimientos");
            }
            return;
        }

        if (tablero.getFilas() - 1 == robotAux.getX() && tablero.getColumnas() - 1 == robotAux.getY()) {
            System.out.print("Camino valido encontrado en " + contador + " movimientos");
            System.out.println("- Suma de casilleros: " + robotAux.getSumaDeCasilleros());
            encontrarCaminoValido(tablero, contador + 1, contadorCorrectos + 1);
        } else {
            encontrarCaminoValido(tablero, contador + 1, contadorCorrectos);
        }
    }

    public Tablero getTablero() {
        return tablero;
    }
}
