package com.example.proyecto_fase5_omaima_benhamouda;

public class DataControl {
   static boolean validarNombre(String nombre){
        do {
            // Verifica si el nombre comienza con una letra y puede tener dígitos
            if (!nombre.matches("[a-zA-Z][a-zA-Z0-9]*")) {
                return false;
            }
        } while (!nombre.matches("[a-zA-Z][a-zA-Z0-9]*"));
        return true;
    }
    static boolean validarEdad(String edad){
        // Validación de la edad
        do {

            if (!edad.matches("\\d+")) {
                return false;
            }
        } while (!edad.matches("\\d+"));
        return true;

    }
}
