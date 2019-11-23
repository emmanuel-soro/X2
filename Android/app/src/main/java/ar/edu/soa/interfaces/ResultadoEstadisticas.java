package ar.edu.soa.interfaces;

public class ResultadoEstadisticas {
    private int nombre;
    private int valor;

    public ResultadoEstadisticas(int nombre, int valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
