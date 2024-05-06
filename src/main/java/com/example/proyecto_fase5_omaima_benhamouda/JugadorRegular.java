package com.example.proyecto_fase5_omaima_benhamouda;

public class JugadorRegular extends Jugadores {
    private int puntuacion;

    public JugadorRegular(String nombre, String nivel, int edad) {
        super(nombre, nivel, edad);
        this.puntuacion = 0; // Inicializa la puntuación del jugador regular
    }
    public JugadorRegular(String nombre, int edad) {
        super(nombre, edad);
        this.puntuacion = 0; // Inicializa la puntuación del jugador regular
    }
    public JugadorRegular() {
    }

    public int getPuntuacion() {
        return puntuacion;
    }
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void aumentarPuntuacion(int puntos) {
        this.puntuacion += puntos;
    }
}
