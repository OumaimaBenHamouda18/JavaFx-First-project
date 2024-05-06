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
import java.util.ArrayList;
import java.util.ResourceBundle;
public class QuizMathInitController implements Initializable {

    @FXML
    private Button startJuego,flechaBtn;
    @FXML
    private ImageView jugador;
    @FXML
    private Label descripcionJuego,errorEdad,errorNombre;
    @FXML
    private TextField nombreJugador, edadJugador;
    //cargar preguntas Quiz(textos, opciones, respuestas correctas) desde ficheros
    ArrayList<String> preguntas = ManejarFicheros.cargarArchivoLineas("quiz/math/preguntas.txt");
    ArrayList<String> respuestas = ManejarFicheros.cargarArchivoLineas("quiz/math/respuestas.txt");
    ArrayList<String[]> opciones = new ArrayList<>();
    Quiz_Mathematica quizMath;
    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<String> lista = ManejarFicheros.cargarArchivoLineas("quiz/math/opciones.txt");
        for (int i = 0; i < lista.size(); i++) {
            opciones.add(lista.get(i).split(","));
        }
         quizMath=new Quiz_Mathematica("Quiz Mathematica","Mathematica","Moderada",40,preguntas,respuestas,opciones);

         descripcionJuego.setText(quizMath.toString());
        Image imageJugador = new Image("file:images/math.png");
        // Asignar la imagen al ImageView
        jugador.setImage(imageJugador);

        Image image = new Image("file:images/flecha.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setX(30);
        imageView.setY(300);
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

                FXMLLoader loader = new FXMLLoader(getClass().getResource("quizMath.fxml"));
                Parent root = loader.load();

                QuizMathController quiz2Controller=loader.getController();
                quiz2Controller.recibirDatos(quizMath,j1);

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

