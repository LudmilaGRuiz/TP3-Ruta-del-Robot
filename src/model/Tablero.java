package model;

import java.util.Random;

public class Tablero {
    private int filas;
    private int columnas;
    private Integer[][] celdas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new Integer[filas][columnas];
    }

    public Integer[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Integer[][] celdas) {
        this.celdas = celdas;
    }

    public void tableroAleatorio() {
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = (random.nextBoolean()) ? 1 : -1;
            }
        }
    }

    public Integer getValorCasillero(int x, int y) {
        if (x < 0 || x >= filas || y < 0 || y >= columnas) {
            throw new IndexOutOfBoundsException("Índices fuera de los límites del tablero.");
        }
        return celdas[x][y];
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tablero de ").append(filas).append("x").append(columnas).append(":\n");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                sb.append(celdas[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Tablero))
            return false;

        Tablero tablero = (Tablero) o;

        if (filas != tablero.filas)
            return false;
        return columnas == tablero.columnas;
    }

    @Override
    public int hashCode() {
        int result = filas;
        result = 31 * result + columnas;
        return result;
    }
}
