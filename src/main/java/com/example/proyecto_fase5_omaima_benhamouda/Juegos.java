package com.example.proyecto_fase5_omaima_benhamouda;

public class Juegos {

    private String nombre,tipo,dificultad;
    private int tiempo,score=0;
    private static String amarillo= "\u001B[33m";
    private static String reset = "\u001B[0m";

    public Juegos(String nombre, String tipo, String dificultad, int tiempo,int score) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dificultad = dificultad;
        this.tiempo = tiempo;
        this.score= score;
    }
    public Juegos(String nombre, String tipo, String dificultad, int tiempo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.dificultad = dificultad;
        this.tiempo = tiempo;
    }
    public Juegos() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return amarillo+"****************************Juego****************************" +reset+
                "\nnombre=" + nombre +
                "\ntipo=" + tipo +
                "\ndificultad=" + dificultad  +
                "\ntiempo=" + tiempo+"s" ;
    }
}
