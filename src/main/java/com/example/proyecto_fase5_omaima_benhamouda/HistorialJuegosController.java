package com.example.proyecto_fase5_omaima_benhamouda;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistorialJuegosController implements Initializable {
    @FXML
    Button flechaBtn;
    @FXML
    TableView<String[]> historialTableView;

    @FXML
    TableColumn<String[], String> nombreJuego,nombreJugador,score;
    ArrayList<String []> historial;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("file:images/flecha.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setX(30);
        imageView.setY(300);
        flechaBtn.setGraphic(imageView);

        try {
            historial = ManejarFicheros.mostrarJugadoresTabla("quiz/registros.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Convert ArrayList to ObservableList of arrays
        ObservableList<String[]> observableDataList = FXCollections.observableArrayList(historial);


        // Set the ObservableList as the items of the TableView
        historialTableView.setItems(observableDataList);

        // Bind each TableColumn to the appropriate index of the array
        nombreJuego.setCellValueFactory(cellData -> {
            String[] row = cellData.getValue();
            return new SimpleStringProperty(row[0]); // Assuming index 0 contains the game name
        });

        nombreJugador.setCellValueFactory(cellData -> {
            String[] row = cellData.getValue();
            return new SimpleStringProperty(row[1]); // Assuming index 1 contains the player name
        });

        score.setCellValueFactory(cellData -> {
            String[] row = cellData.getValue();
            return new SimpleStringProperty(row[2]); // Assuming index 2 contains the score
        });

        }
    @FXML
    protected void onFlechaClick() throws IOException {
        Stage stage = (Stage) historialTableView.getScene().getWindow();
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

