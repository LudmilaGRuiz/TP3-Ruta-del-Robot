package test;

import model.Instancia;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class InstanciaTest {
    Instancia instancia;

    @BeforeEach
    void setUp() {
        instancia = new Instancia(2, 2);
    }

    @Test
    void testGetFilasYColumnas() {
        assertEquals(2, instancia.getFilas());
        assertEquals(2, instancia.getColumnas());
    }

    @Test
    void testGetCeldas() {
        assertNotNull(instancia.getCeldas());
    }

    @Test
    void testGetTablero() {
        assertNotNull(instancia.getTablero());
    }

    @Test
    void testVerificarValidezRobot() {
        // Robot en la posición final y suma 0
        var robot = new model.Robot(0);
        robot.setX(instancia.getFilas() - 1);
        robot.setY(instancia.getColumnas() - 1);
        robot.setSumaDeCasilleros(0);
        assertTrue(instancia.verificarValidezRobot(robot));
    }
}