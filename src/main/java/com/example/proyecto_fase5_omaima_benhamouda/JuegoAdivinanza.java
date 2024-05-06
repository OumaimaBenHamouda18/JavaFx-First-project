package com.example.proyecto_fase5_omaima_benhamouda;

import java.io.IOException;
import java.util.*;

/**
 * @author Omaima Ben Hamouda
 */
public class JuegoAdivinanza extends Juegos {
    private  String[] palabras;
    public JuegoAdivinanza(String nombre, String tipo, String dificultad, int tiempo, int score) {
        super(nombre, tipo, dificultad, tiempo, score);
        this.palabras=palabras;
    }
    public JuegoAdivinanza(){

    }

    public void setPalabras(String[] palabras) {
        this.palabras = palabras;
    }

    static Scanner sc=new Scanner(System.in);
    //Initialializar un array de palabras para adivinar

    //declarar un array paraa guardar las letras adivinadas y las que aun quedan para adivinar "_" de la palabra oculta
    private static char[][] palabraOculta;
    static int intentosJugador;
    /**
     * selecciona una plabara de la lista, de una manera aleatoria ,para adivinar
     * @return
     */
    public String seleccionar_palabra() {
        return palabras[new Random().nextInt(palabras.length)];
    }

    /**
     * inicializa la palabra oculta en una array de caracteres con el caracter "_"
     * @param palabra
     * @return
     */
    public static char[][] inicializar_palabra_oculta(String palabra) {
        char[][] oculta = new char[palabra.length()][1];
        for (char[] fila : oculta) {
            fila[0] = '_';
        }
        return oculta;
    }

    /**
     * muestra el estado de la palabara oculta, letras advinadas y letras aun ocultas
     */
    public static String mostrarEstado(char[][] palabraOculta) {
        String letrasAdivinadas="";
        for (char[] fila : palabraOculta) {
            if(fila[0]!='_'){
                letrasAdivinadas+=fila[0] + " ";
            }
            else  letrasAdivinadas+=fila[0] + " ";
        }
  return letrasAdivinadas;
    }

    /**
     * adivinar letra
     * @param letrasAdivinadas
     * @return
     */
    public static char adivinar_letra(String letrasAdivinadas) {
        Scanner sc = new Scanner(System.in);
        char letra='0';
        boolean invalid;
        do {
            invalid=false;
            System.out.print("\nIngresa una letra: ");
            try{
                letra = sc.nextLine().charAt(0);
                String letterString = new String(new char[]{letra});
                if( !letterString.matches("[a-zA-Z]")){
                    System.err.println("\nDebe ser una letra!");
                    invalid=true;
                }
                if (letrasAdivinadas.indexOf(letra) != -1) {
                    System.out.println("\nLetra ya adivinada. ¡Intenta con otra!");
                    invalid=true;
                }
            }
            catch(StringIndexOutOfBoundsException e){
                System.err.println("Letra vacía");
                invalid=true;
            }
        } while (invalid);
        return letra;
    }

    /**
     * comprobar si ha adivinado una letra o no
     * @param palabra
     * @param letra
     * @return
     */
    public static boolean comprovar_letra(String palabra, char letra,char[][] palabraOculta) {
        boolean acierto = false;
        for (int i = 0; i < palabra.length(); i++) {
            String letraString = new String(new char[]{letra});
            String letraPalabra = new String(new char[]{palabra.charAt(i)});

            if (letraPalabra.equalsIgnoreCase(letraString)) {
                palabraOculta[i][0] = letra;
                acierto = true;
            }
        }
        return acierto;
    }

    /**
     * comprovar si ha adivinado toda la palabra
     * @return
     */
    public static boolean esPalabraCompleta(char[][] palabraOculta) {
        for (char[] fila : palabraOculta) {
            if (fila[0] == '_') {
                return false;
            }
        }
        return true;
    }

    /**
     * muestra toda la palabra en el caso de perdida
     * @param palabra
     */
    public static void mostrarEstadoFinal(String palabra,char[][] palabraOculta) {
        if (!esPalabraCompleta(palabraOculta)) {
            System.out.println("La palabra era: "+ palabra);
        }
    }
    public static int calculateStringLength(String input) {
        // Assuming ASCII characters (0-127)
        boolean[] seen = new boolean[128];
        int count = 0;
        // Iterate through each character of the string
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // Check if the character has been seen before
            if (!seen[c]) {
                // If not, mark it as seen and increment the count
                seen[c] = true;
                count++;
            }
        }
        // Count represents the length of the string without counting occurrences
        return count;
    }
    public void jugar(Jugadores jugador) throws IOException {
        String palabra = seleccionar_palabra();
        palabraOculta = JuegoAdivinanza.inicializar_palabra_oculta(palabra);
        int maxIntentos= JuegoAdivinanza.calculateStringLength(palabra) + 2;
        final int[] intentos = {maxIntentos};
        //un array para las letras adivinadas, para no repetirlas más que una vez
        final String[] letrasAdivinadas = {""};

        Thread juegoThread = new Thread(() -> {
            while (intentos[0] > 0 && !JuegoAdivinanza.esPalabraCompleta(palabraOculta)) {
                JuegoAdivinanza.mostrarEstado(palabraOculta);
                char letra = JuegoAdivinanza.adivinar_letra(letrasAdivinadas[0]);
                letrasAdivinadas[0] += letra;
                intentosJugador++;
                intentos[0]--;
                if (!JuegoAdivinanza.comprovar_letra(palabra, letra,palabraOculta)) {
                    System.err.println("Incorrecto. Te quedan " + intentos[0] + " intento(s).");
                }
            }
            if(JuegoAdivinanza.esPalabraCompleta(palabraOculta)) {
                System.out.println("\n!Felicidades¡ Has adivinado la plabra!");
                System.out.println("\nEspera unos segundos(los hilos se están ejecutando)");
                System.out.println("\nSi no deseas esperar, siempre puedes ejecutar el programa\nde nuevo para probar las otras opciónes del menu.\n\nAviso: Al parar la ejecución puede ser que el jugador no se guarda.");
            }
            if( intentos[0]==0) {
                System.out.println("\n!Lo siento¡ Has acabado todos los intentos");
                JuegoAdivinanza.mostrarEstadoFinal(palabra,palabraOculta);
            }
        });


        //un hilo llamado tiempoThread que duerme durante 30 segundos y,
        // si el hilo juegoThread aún está en ejecución después de ese tiempo,
        // interrumpe el hilo juegoThread y muestra un mensaje de que se agotó
        // el tiempo.
        Thread tiempoThread = new Thread(() -> {
            try {
                Thread.sleep(30000);  // 30 segundos de tiempo límite
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (juegoThread.isAlive()) {
                juegoThread.interrupt();
                System.err.println("\n\n¡Tiempo agotado! El juego ha terminado.");
            }
        });
        System.out.println("\nTienes 30 segundos para adivinar la palabra.");
        System.out.println("\nLa palabra tiene " + palabra.length() + " letras.");
        System.out.println("\nTienes " + intentos[0] + " intentos.\n");

        //Iniciar la ejecucion de los hilos
        juegoThread.start();
        tiempoThread.start();
        //esperar a que ambos hilos terminen su ejecución antes de que el hilo principal (main) continúe.
        try {
            juegoThread.join();
            tiempoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JuegoAdivinanza.mostrarEstadoFinal(palabra,palabraOculta);
        setScore(maxIntentos-intentosJugador);
        ManejarFicheros.guardar_jugador(getNombre(),jugador.getNombre(), getScore(),"ficheros_juego/registros.txt");
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