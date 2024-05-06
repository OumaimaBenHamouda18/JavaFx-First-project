package com.example.proyecto_fase5_omaima_benhamouda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class JuegoAdivinanzaInitController implements Initializable {
    @FXML
    private Button startJuego,flechaBtn;
    @FXML
    private Label descripcionJuego,errorEdad,errorNombre;
    @FXML
    private TextField nombreJugador, edadJugador;

    @FXML
    private ImageView jugador;

    //cargar preguntas Quiz(textos, opciones, respuestas correctas) desde ficheros

    String [] palabras;

    JuegoAdivinanza juegoAdivinanza= new JuegoAdivinanza();
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        nombreJugador.setPrefWidth(100);
        edadJugador.setPrefWidth(100);
        String lista = ManejarFicheros.cargarArchivoLinea("quiz/palabrasAdivinanza/palabras.txt");
        palabras=lista.split(",");

        juegoAdivinanza.setNombre("Adivinar Plabras");
        juegoAdivinanza.setDificultad("Facil");
        juegoAdivinanza.setTipo("adivinanza");
        juegoAdivinanza.setTiempo(30);
        String[] palabras = {"java", "programacion", "hilos", "adivinanza", "juego", "palabra"};
        juegoAdivinanza.setPalabras(palabras);
        descripcionJuego.setText(juegoAdivinanza.toString());
        Image imageJugador = new Image("file:images/pregunta.png");
        // Asignar la imagen al ImageView
        jugador.setImage(imageJugador);
        Image image = new Image("file:images/flecha.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);

        flechaBtn.setGraphic(imageView);
    }

    @FXML
    protected void onStartButtonClick() throws IOException {
        boolean nombreValido = DataControl.validarNombre(nombreJugador.getText());
        boolean edadValida = DataControl.validarEdad(edadJugador.getText());

        // Restablecer estilos
        nombreJugador.setStyle("-fx-border-color: " + (nombreValido ? "white" : "red"));
        edadJugador.setStyle("-fx-border-color: " + (edadValida ? "white" : "red"));
        errorNombre.setVisible(!nombreValido);
        errorEdad.setVisible(!edadValida);

        // Mostrar mensajes de error
        if (!nombreValido) {
            errorNombre.setText("Nombre inválido! Debe empezar con una letra");
            errorNombre.setStyle("-fx-text-fill: red;");
        }
        if (!edadValida) {
            errorEdad.setText("Edad inválida! Debe ser un entero");
            errorEdad.setStyle("-fx-text-fill: red;");
        }

        // Enviar datos si todo es válido
        if (nombreValido && edadValida) {
            sendData();
        }
    }
    @FXML
    protected void onFlechaClick() throws IOException {
        Stage stage = (Stage) startJuego.getScene().getWindow();
        stage.close();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }

    }
        @FXML
        private void sendData() {

            JugadorRegular j1=new JugadorRegular();
            j1.setNombre(nombreJugador.getText());
            j1.setEdad(Integer.parseInt(edadJugador.getText()));

            Stage stage = (Stage) startJuego.getScene().getWindow();
            stage.close();
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("juegoAdivinanza.fxml"));
                Parent root = loader.load();

                JuegoAdivinanzaController adivinanzaController=loader.getController();

                adivinanzaController.recibirDatos(juegoAdivinanza,j1);

                Scene scene = new Scene(root,800, 600);
                stage.getIcons().add(new Image("file:images/icono2.png"));
                stage.setScene(scene);
                stage.setTitle("Jugador: "+j1.getNombre()+j1.getEdad());

                stage.show();
            } catch (IOException e) {
                System.err.println(String.format("Error: %s", e.getMessage()));
            }
        }

    }

