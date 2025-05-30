package controller;

import model.Robot;
import model.Tablero;

/**
 * Controlador de la aplicación que maneja la lógica del juego.
 * Este controlador interactúa con el modelo y puede ser utilizado para
 * gestionar eventos, movimientos del robot, y otras acciones del juego.
 */
public class Controlador {
    private Tablero tablero;

    public Controlador(int ancho, int alto) {
        this.tablero = new Tablero(ancho, alto);
        tablero.tableroAleatorio(); // Genera un tablero aleatorio al iniciar
    }

    public Robot moverRobotHastaElFinal(Robot robotMov) {
        int cantidad = tablero.getAlto() + tablero.getAncho() - 2;
        for (int i = 0; i < cantidad; i++) {
            robotMov.movimientoAleatorio(); // Mueve el robot aleatoriamente hasta alcanzar el final
        }
        return robotMov; // Retorna el robot con su posición final
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Boolean verificarMovimientoRobot(int x, int y) {
        // Verifica si el movimiento del robot es válido dentro del tablero
        if (x < 0 || x >= tablero.getAncho() || y < 0 || y >= tablero.getAlto()) {
            return false; // Movimiento fuera de los límites del tablero
        }
        return true; // Movimiento válido
    }

    public void encontrarCaminoValido() {
        // Método para encontrar un camino válido desde (0,0) hasta (ancho-1, alto-1)
        encontrarCaminoValido(tablero, 0);
    }

    public void encontrarCaminoValido(Tablero tablero, int contador) {
        Robot robotAux = new Robot();
        robotAux = moverRobotHastaElFinal(robotAux);

        if (tablero.getAncho() - 1 == robotAux.getX() && tablero.getAlto() - 1 == robotAux.getY()) {
            System.out.println("Camino encontrado en " + contador + " movimientos.");
        } else {
            System.out.println("Intento nro: " + contador);
            encontrarCaminoValido(tablero, contador + 1);
        }
    }
}
