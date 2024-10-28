package es.guillearana.ejercicioe.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EjercicioEcontroller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}