package com.example.proyecto_fase5_omaima_benhamouda;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class ManejarFicheros {

    /**
     * Guardar jugador en el fichero
     * @param nombre
     * @param score
     * @param path
     * @throws IOException
     */
    public static void guardar_jugador(String nombreJuego,String nombre,int score,String path) throws IOException {

        String line =nombreJuego+","+nombre+","+ score;
        escribirEnArchivo(path, line,true);
    }

    /**
     * escribe una linea en un fichero
     * @param path
     * @param linea
     */
    public static void escribirEnArchivo(String path, String linea,boolean subescribir) {
        try (FileWriter fw = new FileWriter(path,subescribir);
             BufferedWriter bw = new BufferedWriter(fw)) {
             bw.write(linea);
             bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

        public static ArrayList<String> cargarArchivoLineas(String path) {
            ArrayList<String> lineas=new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    lineas.add(linea);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lineas;
        }

    static int elejirOpcion(int max) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        while (true) {
            System.out.println("Ingrese su opción:");
            try {
                opcion = scanner.nextInt();
                if (opcion < 1 || opcion > max) {
                    System.err.println("Opción inválida (no existe)");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.err.println("Opción inválida (debe ser un numero del menu)");
                scanner.nextLine(); // Limpiar el búfer del scanner
            }
        }
        return opcion;
    }
    public static String cargarArchivoLinea(String path) {
        String linea="";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            linea = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linea;
    }

    public static ArrayList<String []> mostrarJugadoresTabla(String path) throws FileNotFoundException {
        File fileRef =new File(path);
        ArrayList<String []> historial=new ArrayList<>();
        if(fileRef.exists()){
            Scanner lector=new Scanner(fileRef);
            lector.useDelimiter(",");
            while(lector.hasNextLine()){
                String line=lector.nextLine();
                String[] parts = line.split(",");
                historial.add(parts);
            }
        }
        else System.out.println("does not exist");
        return historial;
    }

}
