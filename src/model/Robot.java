package model;

import java.util.ArrayList;

public class Robot {
    private int x;
    private int y;
    private int sumaDeCasilleros;
    private ArrayList<Boolean> movimientos; // Lista de movimientos del robot (true = derecha, false = abajo)
    private int cantidadDeMovimientos;

    public Robot() {
        this.x = 0;
        this.y = 0;
        this.sumaDeCasilleros = 0;
        this.movimientos = new ArrayList<Boolean>();
        this.cantidadDeMovimientos = 0;
    }

    public void movimientoAleatorio() {
        // Genera un movimiento aleatorio y lo agrega a la lista de movimientos
        boolean movimiento = Math.random() < 0.5; // true o false aleatorio
        movimientos.add(movimiento);
        sumaDeMovimiento();
        // Actualiza las coordenadas del robot según el movimiento
        if (movimiento) {
            x++; // Mover a la derecha
        } else {
            y++; // Mover hacia abajo
        }
    }

    public void sumaDeMovimiento(){
        this.cantidadDeMovimientos++;
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
