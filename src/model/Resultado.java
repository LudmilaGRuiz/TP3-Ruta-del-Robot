package model;

import java.util.ArrayList;

public class Resultado {
    public ArrayList<Boolean> camino;
    public long tiempo;
    public int caminosExplorados;

    public Resultado(ArrayList<Boolean> camino, long tiempo, int caminosExplorados) {
        this.camino = camino;
        this.tiempo = tiempo;
        this.caminosExplorados = caminosExplorados;
    }
}
