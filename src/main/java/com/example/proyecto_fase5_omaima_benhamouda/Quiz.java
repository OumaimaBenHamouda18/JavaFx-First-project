package com.example.proyecto_fase5_omaima_benhamouda;

import java.util.ArrayList;

public class Quiz extends Juegos {
    ArrayList<String> preguntas,respuestas;
    ArrayList<String[]> opciones;

    public Quiz(String nombre, String tipo, String dificultad, int tiempo, int score,ArrayList<String> preguntas, ArrayList<String> respuestas, ArrayList<String[]> opciones) {
        super(nombre, tipo, dificultad, tiempo,score);
        this.preguntas = preguntas;
        this.respuestas = respuestas;
        this.opciones = opciones;
    }
    public Quiz(String nombre, String tipo, String dificultad,int tiempo) {
        super(nombre, tipo, dificultad,tiempo);
    }
    public Quiz() {
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<String> preguntas) {
        this.preguntas = preguntas;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<String> respuestas) {
        this.respuestas = respuestas;
    }

    public ArrayList<String[]> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<String[]> opciones) {
        this.opciones = opciones;
    }



}
