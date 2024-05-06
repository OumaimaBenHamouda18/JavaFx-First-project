package com.example.proyecto_fase5_omaima_benhamouda;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class QuizMathController implements Initializable{
    @FXML
    private Label resultadoFinalLabel;
    @FXML
    private Label textContentLabel,resultadoPregunta;
    ToggleGroup  toggleGroup;
    @FXML
    private RadioButton option1,option2,option3;
    @FXML
    private Button siguientePreguntaBtn,resultadoJuegoBtn,exit,menu,reload;
    @FXML
    private TextField nombreJugadorTextField;
    Quiz_Mathematica qm;
    JugadorRegular jr;
    int i=0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultadoJuegoBtn.setVisible(false);
        // TOGGLEGROUP permite agroupar las opciones para que el jugador solo pueda seleccionar una opcion.
        toggleGroup = new ToggleGroup();
        option1.setToggleGroup(toggleGroup);
        option2.setToggleGroup(toggleGroup);
        option3.setToggleGroup(toggleGroup);

        Image reloadIcon = new Image("file:images/reload.png");
        ImageView imageViewReload = new ImageView(reloadIcon);

        reload.setGraphic(imageViewReload);
        imageViewReload.setFitWidth(25);
        imageViewReload.setFitHeight(25);
        Image menuIcon = new Image("file:images/menu.png");
        Image exitIcon = new Image("file:images/exit.png");

        ImageView imageViewExit = new ImageView(exitIcon);

        ImageView imageViewMenu = new ImageView(menuIcon);

        imageViewExit.setFitWidth(25);
        imageViewExit.setFitHeight(25);

        imageViewMenu.setFitWidth(25);
        imageViewMenu.setFitHeight(25);

        menu.setGraphic(imageViewMenu);
        exit.setGraphic(imageViewExit);
    }
    @FXML
    public void recibirDatos(Quiz_Mathematica quizM, JugadorRegular j){
        qm=quizM ;
        jr=j;
        textContentLabel.setText(qm.getPreguntas().get(i));
        actualizarOpciones(i);
        i++;
    }
    @FXML
    protected void onNextButtonClick() throws IOException, InterruptedException {

        imprimirResultadoPregunta(getSelectedOption(),i);

        delay(1000, () ->{

            if(!detectarFinalJuego(i)) {
                resultadoPregunta.setVisible(false);
                textContentLabel.setText(qm.getPreguntas().get(i));
                actualizarOpciones(i);
                i++;
            }
            else {
                siguientePreguntaBtn.setDisable(true);

                resultadoJuegoBtn.setVisible(true);
                try {
                    ManejarFicheros.guardar_jugador(qm.getNombre(),jr.getNombre(),qm.getScore(),"quiz/registros.txt");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
                }
        );
    }
    @FXML
    public static void delay(long millis, Runnable continuation) {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }
    @FXML
    protected void onResultadoButtonClick() throws IOException, InterruptedException {
        //resultado final
        resultadoFinalLabel.setText(qm.getScore()+"/10");
    }
    int getSelectedOption(){
        if (option1.isSelected()) {
           return 1;
        }
        else if(option2.isSelected()){
            return 2;
        }
        else {
            return 3;
        }
    }
    void imprimirResultadoPregunta(int selectedOption, int numPregunta ) throws InterruptedException {

        if(qm.comprovarOpcion(selectedOption-1,numPregunta-1)){
            resultadoPregunta.setText(": )");
            resultadoPregunta.setVisible(true);
            qm.setScore(qm.getScore()+1);
        }
        else {
            resultadoPregunta.setText(": (");
            resultadoPregunta.setVisible(true);
        }
    }
    void actualizarOpciones(int i){
        option1.setText(qm.getOpciones().get(i)[0]);
        option2.setText(qm.getOpciones().get(i)[1]);
        option3.setText(qm.getOpciones().get(i)[2]);
    }
    boolean detectarFinalJuego(int i){
        if(i==10) return true;
        else return false;
    }
    @FXML
    protected void onExitClick() throws IOException{

        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    @FXML
    protected void onReloadClick() throws IOException{
        Stage stage = (Stage) reload.getScene().getWindow();
        stage.close();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("quizMathInit.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root,800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }
    @FXML
    protected void onMenuClick() throws IOException{
        Stage stage = (Stage) menu.getScene().getWindow();
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
}


