package model;

import java.util.Random;

public class Tablero {
    private int filas;
    private int columnas;
    private Boolean[][] celdas;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new Boolean[filas][columnas];
    }
    public Boolean[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Boolean[][] celdas) {
        this.celdas = celdas;
    }

    public void tableroAleatorio() {
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = random.nextBoolean();
            }
        }
    }
    

    public int getValorCasillero(int x, int y) {
        if (x < 0 || x >= filas || y < 0 || y >= columnas) {
            throw new IndexOutOfBoundsException("Índices fuera de los límites del tablero.");
        }
        return celdas[x][y] ? 1 : -1;
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
                sb.append(celdas[i][j] ? " 1 " : "-1 ");
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
