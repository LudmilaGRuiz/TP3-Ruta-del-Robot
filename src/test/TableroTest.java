package test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;

import model.Tablero;

public class TableroTest {
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        tablero = new Tablero(3, 3);
    }

    @Test
    public void testInicializacionTablero() {
        assertEquals(3, tablero.getFilas());
        assertEquals(3, tablero.getColumnas());
        Boolean[][] celdas = tablero.getCeldas();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertFalse(celdas[i][j]);
            }
        }
    }

    @Test
    public void testTableroAleatorio() {
        tablero.tableroAleatorio();
        Boolean[][] celdas = tablero.getCeldas();
        boolean hayTrue = false;
        boolean hayFalse = false;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (celdas[i][j]) hayTrue = true;
                else hayFalse = true;
            }
        }
        assertTrue(hayTrue || hayFalse); // Al menos un valor cambiado
    }

    @Test
    public void testGetValorCasilleroDentroDeLimites() {
        tablero.setCeldas(new Boolean[][]{
            {true, false, true},
            {false, true, false},
            {true, true, false}
        });
        assertEquals(1, tablero.getValorCasillero(0, 0));
        assertEquals(-1, tablero.getValorCasillero(0, 1));
        assertEquals(1, tablero.getValorCasillero(1, 1));
        assertEquals(-1, tablero.getValorCasillero(2, 2));
    }

    @Test
    public void testGetValorCasilleroFueraDeLimites() {
        assertEquals(0, tablero.getValorCasillero(-1, 0));
        assertEquals(0, tablero.getValorCasillero(0, -1));
        assertEquals(0, tablero.getValorCasillero(3, 0));
        assertEquals(0, tablero.getValorCasillero(0, 3));
    }
}
