package com.example.proyecto_fase5_omaima_benhamouda;

public class Jugadores {

    private String nombre,nivel;
    private int edad;
    private String [][] historial;
    public Jugadores(String nombre, String nivel, int edad) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.edad = edad;
    }
    public Jugadores(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public Jugadores() {
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nive) {
        this.nivel = nive;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String[][] getHistorial() {
        return historial;
    }

    public void setHistorial(String[][] historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        return "Jugadores{" +
                "nombre='" + nombre + '\'' +
                ", nivel='" + nivel + '\'' +
                ", edad=" + edad +
                '}';
    }
}
