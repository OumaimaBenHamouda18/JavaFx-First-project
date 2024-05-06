package com.example.proyecto_fase5_omaima_benhamouda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quiz_Mathematica extends Quiz{

    public Quiz_Mathematica(String nombre, String tipo, String dificultad, int tiempo, ArrayList<String> preguntas, ArrayList<String> respuestas, ArrayList<String[]> opciones) {
        super(nombre, tipo, dificultad, tiempo,0, preguntas, respuestas, opciones);
    }
    public Quiz_Mathematica(String nombre, String tipo, String dificultad, int tiempo) {
        super(nombre, tipo, dificultad, tiempo);
    }

    public Quiz_Mathematica() {
    }
    void jugar(Jugadores jugador) throws IOException {
        for(int i=0;i<preguntas.size();i++){
            System.out.println(preguntas.get(i));
            for(int j=0;j < opciones.get(i).length;j++){

                System.out.println(j+1+"."+opciones.get(i)[j]);
            }
            int opcion = ManejarFicheros.elejirOpcion(3);
            if(comprovarOpcion(opcion,i)){
                System.out.println("Correcto");
                setScore(getScore()+1);
            }
            else {
                System.out.println("Incorrecto");
            }
        }

        ManejarFicheros.guardar_jugador(getNombre(),jugador.getNombre(),getScore(),"ficheros_juego/registros.txt");
    }

    boolean comprovarOpcion(int opcion,int numPregunta){
        System.out.println(opciones.get(numPregunta)[opcion]);
        System.out.println(respuestas.get(numPregunta));


        if(respuestas.get(numPregunta).equals(opciones.get(numPregunta)[opcion]))
            return true;
        return false;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre del juego: ").append(getNombre()).append("\n");
        sb.append("Tipo de juego: ").append(getTipo()).append("\n");
        sb.append("Dificultad: ").append(getDificultad()).append("\n");
        sb.append("Tiempo estimado: ").append(getTiempo()).append(" segundos\n");
        return sb.toString();
    }


}
