import model.Robot;
import model.Tablero;

public class main {
    public static void main(String[] args) {
        // Crear un robot en la posición (0,0)
        Robot robot = new Robot(0, 0);
        Tablero tablero = new Tablero(10, 9);
        tablero.tableroAleatorio(); // Generar un tablero aleatorio


        // Realizar algunos movimientos aleatorios
        for (int i = 0; i < tablero.getAlto()+ tablero.getAncho()-1; i++) {
            robot.movimientoAleatorio();
            System.out.println("Movimiento " + (i + 1) + ": " + robot);
            try {
                Thread.sleep(500); // Solo para ver los cambios con pausa
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}