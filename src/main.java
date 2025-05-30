import controller.Controlador;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador(); // Crear un controlador
        controlador.crearTablero(99, 100); // Crear un tablero de 10x9
        // System.out.println(controlador.getTablero());
        controlador.encontrarCaminoValido(); // Encontrar un camino válido desde (0,0) hasta (ancho-1, alto-1)
    }
}