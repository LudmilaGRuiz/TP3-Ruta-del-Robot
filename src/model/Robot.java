package model;

import java.util.ArrayList;
import java.util.Random;

public class Robot {
    private int x;
    private int y;
    private int sumaDeCasilleros;
    private ArrayList<Boolean> movimientos; // Lista de movimientos del robot (true = derecha, false = abajo)
    private int cantidadDeMovimientos;

    public Robot(int ValorCasillero) {
        this.x = 0;
        this.y = 0;
        this.sumaDeCasilleros = ValorCasillero;
        this.movimientos = new ArrayList<Boolean>();
        this.cantidadDeMovimientos = 0;
    }

    public void movimientoAleatorio() {
        Random random = new Random();
        boolean movimiento = random.nextBoolean();
        movimientos.add(movimiento);
        sumaDeMovimiento();
        if (movimiento) {
            y++;
        } else {
            x++;
        }
    }

    public void sumaDeMovimiento() {
        this.cantidadDeMovimientos++;
    }

    public void sumaDeCasilleros(int casillero) {
        this.sumaDeCasilleros += casillero;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Boolean> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Boolean> movimientos) {
        this.movimientos = movimientos;
    }

    public int getSumaDeCasilleros() {
        return sumaDeCasilleros;
    }

    public void setSumaDeCasilleros(int sumaDeCasilleros) {
        this.sumaDeCasilleros = sumaDeCasilleros;
    }

    public int getCantidadDeMovimientos() {
        return cantidadDeMovimientos;
    }

    public void setCantidadDeMovimientos(int cantidadDeMovimientos) {
        this.cantidadDeMovimientos = cantidadDeMovimientos;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "x=" + x +
                ", y=" + y +
                ", movimientos=" + movimientos +
                '}';
    }

}
