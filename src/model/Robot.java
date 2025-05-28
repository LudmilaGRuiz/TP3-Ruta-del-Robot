package model;

import java.util.List;

public class Robot {
    private int x;
    private int y;
    private List <Boolean> movimientos; 
    
    public Robot(int x, int y) {
        this.x = x;
        this.y = y;
        this.movimientos = new java.util.ArrayList<>();
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

    public List<Boolean> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Boolean> movimientos) {
        this.movimientos = movimientos;
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
