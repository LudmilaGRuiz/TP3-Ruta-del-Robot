package test;

import model.Tablero;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {
    Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = Tablero.aleatorio(5, 4);
    }

    @Test
    void testInicializacion() {
        assertEquals(5, tablero.getFilas());
        assertEquals(4, tablero.getColumnas());
        assertNotNull(tablero.getCeldas());
    }

    @Test
    void testSetYGetCeldas() {
        Boolean[][] celdas = {{true, false}, {false, true}};
        tablero.setCeldas(celdas);
        assertArrayEquals(celdas, tablero.getCeldas());
    }

    @Test
    void testTableroAleatorio() {
        Boolean[][] celdas = tablero.getCeldas();
        assertNotNull(celdas[0][0]);
    }

    @Test
    void testGetValorCasillero() {
        tablero.setCeldas(new Boolean[][]{{true, false}, {false, true}});
        assertEquals(1, tablero.getValorCasillero(0, 0));
        assertEquals(-1, tablero.getValorCasillero(0, 1));
    }

    @Test
    void testGetValorCasilleroFueraDeLimite() {
        assertThrows(IndexOutOfBoundsException.class, () -> tablero.getValorCasillero(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> tablero.getValorCasillero(0, 7));
    }

    @Test
    void testEqualsYHashCode() {
        Tablero t2 = Tablero.aleatorio(5, 4);
        assertEquals(tablero, t2);
        assertEquals(tablero.hashCode(), t2.hashCode());
    }
}