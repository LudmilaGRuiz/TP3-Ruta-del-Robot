package model;

public class Tablero {
    private int ancho;
    private int alto;

    public Tablero(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
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
