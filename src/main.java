import controller.Controlador;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador(); // Crear un controlador
        controlador.crearTablero(10, 9); // Crear un tablero de 10x9
        controlador.encontrarCaminoValido(); // Encontrar un camino válido desde (0,0) hasta (ancho-1, alto-1)
    }
}