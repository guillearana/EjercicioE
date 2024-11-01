package es.guillearana.ejercicioe.controlador;

import es.guillearana.ejercicioe.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Controlador para la gestión de la vista principal de la aplicación.
 * Este controlador maneja la lógica para agregar, modificar y eliminar personas de la lista
 * y actualiza la vista de la tabla.
 */
public class EjercicioEcontroller {

    /** Botón para agregar una nueva persona. */
    @FXML
    private Button btnAgregar;

    /** Botón para modificar una persona seleccionada. */
    @FXML
    private Button btnModificar;

    /** Botón para eliminar una persona seleccionada. */
    @FXML
    private Button btnEliminar;

    /** Columna para mostrar los apellidos de las personas. */
    @FXML
    private TableColumn<Persona, String> colApellidos;

    /** Columna para mostrar la edad de las personas. */
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    /** Columna para mostrar el nombre de las personas. */
    @FXML
    private TableColumn<Persona, String> colNombre;

    /** Tabla para mostrar la información de las personas. */
    @FXML
    private TableView<Persona> tableInfo;

    /** Lista observable que contiene las personas. */
    private ObservableList<Persona> personas;

    /**
     * Método que se llama al inicializar el controlador.
     * Configura las columnas de la tabla y la lista de personas.
     */
    @FXML
    public void initialize() {
        personas = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    /**
     * Maneja la acción de agregar una nueva persona.
     * Abre una ventana modal para ingresar los datos de la nueva persona
     * y la agrega a la lista si es válida.
     *
     * @param event el evento de acción del botón "Agregar"
     */
    @FXML
    void accionAgregar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/guillearana/ejercicioe/ejerEmodal.fxml"));
            Parent root = loader.load();

            ControllerModalEjerE modalController = loader.getController();

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.setTitle("Agregar Persona");
            modalStage.setScene(new Scene(root));
            modalStage.setResizable(false);
            modalStage.showAndWait();

            Persona nuevaPersona = modalController.getPersona();
            if (nuevaPersona != null && !personas.contains(nuevaPersona)) {
                personas.add(nuevaPersona);
                tableInfo.setItems(personas);
            } else {
                mostrarAlerta("La persona ya existe en la lista");
            }

        } catch (IOException e) {
            mostrarAlerta("Error al abrir la ventana modal: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            mostrarAlerta("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Maneja la acción de modificar una persona seleccionada.
     * Abre una ventana modal para editar los datos de la persona seleccionada.
     *
     * @param event el evento de acción del botón "Modificar"
     */
    @FXML
    void accionModificar(ActionEvent event) {
        Persona personaSeleccionada = tableInfo.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/es/guillearana/ejercicioe/ejerEmodal.fxml"));
                Parent root = loader.load();

                ControllerModalEjerE modalController = loader.getController();
                modalController.setPersona(personaSeleccionada); // Establece la persona a modificar

                Stage modalStage = new Stage();
                modalStage.initModality(Modality.APPLICATION_MODAL);
                modalStage.setTitle("Modificar Persona");
                modalStage.setScene(new Scene(root));
                modalStage.setResizable(false);
                modalStage.showAndWait();

                Persona personaModificada = modalController.getPersona();
                if (personaModificada != null) {
                    int index = personas.indexOf(personaSeleccionada);
                    personas.set(index, personaModificada); // Reemplaza la persona modificada en la lista
                    tableInfo.setItems(personas);
                }

            } catch (IOException e) {
                mostrarAlerta("Error al abrir la ventana modal: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                mostrarAlerta("Ocurrió un error: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Debes seleccionar una persona para modificar.");
        }
    }

    /**
     * Maneja la acción de eliminar una persona seleccionada.
     * Elimina la persona de la lista y actualiza la tabla.
     *
     * @param event el evento de acción del botón "Eliminar"
     */
    @FXML
    void accionEliminar(ActionEvent event) {
        Persona personaSeleccionada = tableInfo.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            personas.remove(personaSeleccionada);
            tableInfo.setItems(personas);
            mostrarAlerta("Persona eliminada correctamente.");
        } else {
            mostrarAlerta("Debes seleccionar una persona para eliminar.");
        }
    }

    /**
     * Muestra un mensaje de alerta con la información proporcionada.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
