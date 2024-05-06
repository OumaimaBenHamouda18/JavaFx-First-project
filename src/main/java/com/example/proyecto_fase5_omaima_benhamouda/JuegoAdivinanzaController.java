package com.example.proyecto_fase5_omaima_benhamouda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class JuegoAdivinanzaController implements Initializable {
    JuegoAdivinanza jAdivinanza;
    JugadorRegular jr;
   char[][] palabraOculta;
    String palabra;
    @FXML
    Button exit,reload,menu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    private ImageView felicidades;
    Button clickedButton;
    @FXML
    Label letraAdivinadas;

    @FXML
    protected void onLetraClick(ActionEvent event) throws IOException

         {
             clickedButton = (Button) event.getSource();
            String buttonText = clickedButton.getText();

             if(jAdivinanza.comprovar_letra(palabra, clickedButton.getText().charAt(0),palabraOculta)){

                 clickedButton.setStyle("-fx-background-color: green; -fx-border-color: green;");

                 letraAdivinadas.setText(jAdivinanza.mostrarEstado(palabraOculta));
             }
             if(jAdivinanza.esPalabraCompleta(palabraOculta)){

                 Image image = new Image("file:images/party.gif");
                 // Asignar la imagen al ImageView
                 felicidades.setImage(image);
                 ManejarFicheros.guardar_jugador(jAdivinanza.getNombre(),jr.getNombre(),jAdivinanza.getScore(),"quiz/registros.txt");
             }
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("quizAdivinanzaInit.fxml"));
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
    @FXML
    public void recibirDatos(JuegoAdivinanza jA, JugadorRegular j){
        jAdivinanza=jA ;
        jr=j;
        palabra=jAdivinanza.seleccionar_palabra();
        palabraOculta = jAdivinanza.inicializar_palabra_oculta(palabra);
    }
}
