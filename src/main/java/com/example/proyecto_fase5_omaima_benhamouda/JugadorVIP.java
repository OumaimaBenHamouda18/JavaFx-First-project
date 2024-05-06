package com.example.proyecto_fase5_omaima_benhamouda;

public class JugadorVIP extends Jugadores {
    private int nivelVIP;
    private boolean accesoExclusivo;

    public JugadorVIP(String nombre,String nivel, int edad) {
        super(nombre,nivel,edad);
        this.accesoExclusivo = true; // Los jugadores VIP tienen acceso exclusivo por defecto
    }
    public JugadorVIP(String nombre,int edad) {
        super(nombre,edad);
        this.accesoExclusivo = true; // Los jugadores VIP tienen acceso exclusivo por defecto
    }
    public JugadorVIP() {
    }

    public int getNivelVIP() {
        return nivelVIP;
    }

    public void setNivelVIP(int nivelVIP) {
        this.nivelVIP = nivelVIP;
    }

    public boolean isAccesoExclusivo() {
        return accesoExclusivo;
    }

    public void setAccesoExclusivo(boolean accesoExclusivo) {
        this.accesoExclusivo = accesoExclusivo;
    }
    public void obtenerBonificacion() {
        if (nivelVIP >= 3) {
            System.out.println("¡Felicidades, has obtenido una bonificación exclusiva!");
            // Aquí podrías implementar la lógica para otorgar la bonificación
        } else {
            System.out.println("Lo siento, tu nivel VIP no es lo suficientemente alto para obtener una bonificación.");
        }
    }
}