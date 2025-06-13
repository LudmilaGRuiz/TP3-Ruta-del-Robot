package test;

import model.Resultado;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ResultadoTest {

    @Test
    void testConstructorAndFields() {
        ArrayList<Boolean> camino = new ArrayList<>();
        camino.add(true);
        camino.add(false);

        long tiempo = 123L;
        int caminosExplorados = 42;

        Resultado resultado = new Resultado(camino, tiempo, caminosExplorados);

        assertEquals(camino, resultado.camino);
        assertEquals(tiempo, resultado.tiempo);
        assertEquals(caminosExplorados, resultado.caminosExplorados);
    }

    @Test
    void testEmptyCamino() {
        ArrayList<Boolean> camino = new ArrayList<>();
        Resultado resultado = new Resultado(camino, 0L, 0);

        assertTrue(resultado.camino.isEmpty());
        assertEquals(0L, resultado.tiempo);
        assertEquals(0, resultado.caminosExplorados);
    }
}