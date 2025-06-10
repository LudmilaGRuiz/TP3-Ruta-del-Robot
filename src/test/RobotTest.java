package test;

import model.Robot;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    Robot robot;

    @BeforeEach
    void setUp() {
        robot = new Robot(1);
    }

    @Test
    void testInicializacion() {
        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        assertEquals(1, robot.getSumaDeCasilleros());
        assertEquals(1, robot.getCantidadDeMovimientos());
        assertNotNull(robot.getMovimientos());
    }

    @Test
    void testMovimientoAleatorio() {
        robot.movimientoAleatorio();
        assertEquals(2, robot.getCantidadDeMovimientos());
        assertEquals(1, robot.getMovimientos().size());
        // x o y debe haber cambiado
        assertTrue(robot.getX() == 1 || robot.getY() == 1);
    }

    @Test
    void testSumaDeCasilleros() {
        robot.sumaDeCasilleros(5);
        assertEquals(6, robot.getSumaDeCasilleros());
    }

    @Test
    void testSettersYGetters() {
        robot.setX(2);
        robot.setY(3);
        robot.setSumaDeCasilleros(10);
        robot.setCantidadDeMovimientos(7);
        assertEquals(2, robot.getX());
        assertEquals(3, robot.getY());
        assertEquals(10, robot.getSumaDeCasilleros());
        assertEquals(7, robot.getCantidadDeMovimientos());
    }

    @Test
    void testToString() {
        assertTrue(robot.toString().contains("Robot{"));
    }
}