package model;

public class Tablero {
    private int ancho;
    private int alto;
    private Boolean[][] celdas;


    public Tablero(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.celdas = new Boolean[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                celdas[i][j] = false; // Inicializar todas las celdas como no ocupadas
            }
        }

    }
    public Boolean[][] getCeldas() {
        return celdas;
    }

    public void setCeldas(Boolean[][] celdas) {
        this.celdas = celdas;
    }
    
    public void tableroAleatorio() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                celdas[i][j] = Math.random() < 0.5; // Asigna true o false aleatoriamente
            }
        }
    

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
    
    @Override
    public String toString() {
        return "Tablero{" +
                "ancho=" + ancho +
                ", alto=" + alto +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tablero)) return false;

        Tablero tablero = (Tablero) o;

        if (ancho != tablero.ancho) return false;
        return alto == tablero.alto;
    }

    @Override
    public int hashCode() {
        int result = ancho;
        result = 31 * result + alto;
        return result;
    }
    
}
