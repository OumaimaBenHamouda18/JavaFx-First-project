package com.example.proyecto_fase5_omaima_benhamouda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private Button quizMathBtn,quizAdivinanzaBtn,quizCulturaBtn,historialJuegosBtn;
    @FXML
    private Label welcomeText;
    @FXML
    protected void onQuizCulturaButtonClick() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) quizCulturaBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("quizCulturaInit.fxml"));

        Scene scene = new Scene(root,800, 600);
        stage.getIcons().add(new Image("file:images/icono2.png"));
        stage.setScene(scene);
        stage.setTitle("Quiz Cultura");
        stage.show();

    }

    @FXML
    protected void onQuizMathButtonClick() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) quizMathBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("quizMathInit.fxml"));
        Scene scene = new Scene(root,800, 600);
        stage.getIcons().add(new Image("file:images/icono2.png"));
        stage.setScene(scene);
        stage.setTitle("Quiz Math");
        stage.show();
    }
    @FXML
    protected void onAdivinanzaButtonClick() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) quizAdivinanzaBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("quizAdivinanzaInit.fxml"));

        Scene scene = new Scene(root,800, 600);
        stage.getIcons().add(new Image("file:images/icono2.png"));
        stage.setScene(scene);
        stage.setTitle("Juego Adivinanza");
        stage.show();
    }
    @FXML
    protected void onHistorialJuegosButtonClick() throws IOException {

        Stage stage;
        Parent root;
        stage = (Stage) historialJuegosBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("historialJuegos.fxml"));
        Scene scene = new Scene(root,800, 600);
        stage.getIcons().add(new Image("file:images/icono2.png"));
        stage.setScene(scene);
        stage.setTitle("Historial Juegos");
        stage.show();

    }
    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        stage.close();
    }

}