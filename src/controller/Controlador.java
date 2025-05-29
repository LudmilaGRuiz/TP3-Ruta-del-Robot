package controller;
import model.Robot;
import model.Tablero;
import java.util.List;
import java.util.ArrayList;

/**
 * Controlador de la aplicación que maneja la lógica del juego.
 * Este controlador interactúa con el modelo y puede ser utilizado para
 * gestionar eventos, movimientos del robot, y otras acciones del juego.
 */
public class Controlador {
    private Robot robot;
    private Tablero tablero;
    
    
    public Controlador(int ancho, int alto) {
        this.tablero = new Tablero(ancho, alto);
        this.robot = new Robot(0, 0); // Inicializa el robot en la posición (0,0)
        tablero.tableroAleatorio(); // Genera un tablero aleatorio al iniciar
    }
    
    public void moverRobotAleatorio() {
        robot.movimientoAleatorio(); // Realiza un movimiento aleatorio del robot
    }
    
    public Robot getRobot() {
        return robot;
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
    /**
     * Valida si la secuencia de movimientos lleva al robot desde (0,0) hasta (ancho-1, alto-1)
     * Solo se permiten movimientos derecha (true) y abajo (false).
     */
    
    public boolean validarRecorridoHastaFinal() {
        int x = 0;
        int y = 0;
        List<Boolean> movimientos = robot.getMovimientos();

        for (Boolean mov : movimientos) {
            if (mov) {
                x++; // derecha
            } else {
                y++; // abajo
            }
            // Si se sale del tablero, no es válido
            if (x >= tablero.getAncho() || y >= tablero.getAlto()) {
                return false;
            }
        }
        // Debe terminar exactamente en el último casillero
        return x == tablero.getAncho() - 1 && y == tablero.getAlto() - 1;
    }

    
}
