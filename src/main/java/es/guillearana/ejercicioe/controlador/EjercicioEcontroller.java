package es.guillearana.ejercicioe.controlador;

import es.guillearana.ejercicioe.model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador para la gestión de personas.
 * Este controlador maneja la lógica de negocio y la interacción con la interfaz de usuario
 * para la creación y gestión de objetos {@link Persona}.
 */
public class EjercicioEcontroller {

    /** Botón para agregar una nueva persona. */
    @FXML
    private Button btnAgregar;

    /** Columna de apellidos en la tabla. */
    @FXML
    private TableColumn<Persona, String> colApellidos;

    /** Lista observable de personas. */
    private ObservableList<Persona> personas;

    /** Columna de edad en la tabla. */
    @FXML
    private TableColumn<Persona, Integer> colEdad;

    /** Columna de nombre en la tabla. */
    @FXML
    private TableColumn<Persona, String> colNombre;

    /** Tabla que muestra la información de las personas. */
    @FXML
    private TableView<Persona> tableInfo;

    /** Campo de texto para ingresar apellidos. */
    @FXML
    private TextField tfApellidos;

    /** Campo de texto para ingresar edad. */
    @FXML
    private TextField tfEdad;

    /** Campo de texto para ingresar nombre. */
    @FXML
    private TextField tfNombre;

    /**
     * Inicializa el controlador. Este método se llama después de que se haya
     * cargado el archivo FXML.
     * Se inicializa la lista de personas y se configuran las columnas de la tabla.
     */
    @FXML
    void initialize() {
        personas = FXCollections.observableArrayList();
        // Asigna las propiedades de las columnas a los atributos de la clase Persona
        colNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
        colEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    }

    /**
     * Maneja la acción de agregar una nueva persona.
     * Abre una ventana emergente donde se pueden ingresar los detalles de la persona.
     *
     * @param event el evento de acción que provoca la apertura de la ventana emergente
     */
    @FXML
    void accionAgregar(ActionEvent event) {
        // Crea la ventana emergente y el contenedor
        Stage ventanaEmergente = new Stage();
        VBox contenedorRaiz = new VBox();

        // Contenedor para el Nombre
        HBox contenedorNombre = new HBox();
        contenedorNombre.setSpacing(10);
        tfNombre = new TextField();
        contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);

        // Contenedor para los Apellidos
        HBox contenedorApellidos = new HBox();
        contenedorApellidos.setSpacing(10);
        tfApellidos = new TextField();
        contenedorApellidos.getChildren().addAll(new javafx.scene.control.Label("Apellidos"), tfApellidos);

        // Contenedor para la Edad
        HBox contenedorEdad = new HBox();
        contenedorEdad.setSpacing(10);
        tfEdad = new TextField();
        contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);

        // Contenedor para los botones
        HBox contenedorBotones = new HBox();
        contenedorBotones.setSpacing(10);
        Button guardarBtn = new Button("Guardar");
        guardarBtn.setOnAction(e -> guardar(e));
        Button cerrarBtn = new Button("Cerrar");
        cerrarBtn.setOnAction(e -> ventanaEmergente.close());

        contenedorBotones.getChildren().addAll(guardarBtn, cerrarBtn);

        // Añade los contenedores al contenedor raíz
        contenedorRaiz.getChildren().addAll(contenedorNombre, contenedorApellidos, contenedorEdad, contenedorBotones);
        // Establece propiedades para el contenedor raíz
        contenedorRaiz.setPadding(new Insets(20));
        contenedorRaiz.setSpacing(20);
        // Crea la escena y establece la ventana emergente
        Scene escena = new Scene(contenedorRaiz);
        ventanaEmergente.setScene(escena);
        ventanaEmergente.setTitle("Nueva Persona");
        ventanaEmergente.setResizable(false);
        ventanaEmergente.show();
    }

    /**
     * Maneja la acción de modificar una persona seleccionada.
     * Abre una ventana emergente donde se pueden editar los detalles de la persona.
     *
     * @param event el evento de acción que provoca la apertura de la ventana emergente
     */
    @FXML
    void accionModificar(ActionEvent event) {
        Persona personaSeleccionada = tableInfo.getSelectionModel().getSelectedItem();
        if (personaSeleccionada != null) {
            // Crea la ventana emergente y el contenedor
            Stage ventanaEmergente = new Stage();
            VBox contenedorRaiz = new VBox();

            // Contenedor para el Nombre
            HBox contenedorNombre = new HBox();
            contenedorNombre.setSpacing(10);
            tfNombre = new TextField(personaSeleccionada.getNombre());
            contenedorNombre.getChildren().addAll(new javafx.scene.control.Label("Nombre"), tfNombre);

            // Contenedor para los Apellidos
            HBox contenedorApellidos = new HBox();
            contenedorApellidos.setSpacing(10);
            tfApellidos = new TextField(personaSeleccionada.getApellidos());
            contenedorApellidos.getChildren().addAll(new javafx.scene.control.Label("Apellidos"), tfApellidos);

            // Contenedor para la Edad
            HBox contenedorEdad = new HBox();
            contenedorEdad.setSpacing(10);
            tfEdad = new TextField(String.valueOf(personaSeleccionada.getEdad()));
            contenedorEdad.getChildren().addAll(new javafx.scene.control.Label("Edad"), tfEdad);

            // Contenedor para los botones
            HBox contenedorBotones = new HBox();
            contenedorBotones.setSpacing(10);
            Button modificarBtn = new Button("Modificar");
            modificarBtn.setOnAction(e -> modificar(personaSeleccionada));
            Button cerrarBtn = new Button("Cerrar");
            cerrarBtn.setOnAction(e -> ventanaEmergente.close());

            contenedorBotones.getChildren().addAll(modificarBtn, cerrarBtn);

            // Añade los contenedores al contenedor raíz
            contenedorRaiz.getChildren().addAll(contenedorNombre, contenedorApellidos, contenedorEdad, contenedorBotones);
            // Establece propiedades para el contenedor raíz
            contenedorRaiz.setPadding(new Insets(20));
            contenedorRaiz.setSpacing(20);
            // Crea la escena y establece la ventana emergente
            Scene escena = new Scene(contenedorRaiz);
            ventanaEmergente.setScene(escena);
            ventanaEmergente.setTitle("Modificar Persona");
            ventanaEmergente.setResizable(false);
            ventanaEmergente.show();
        } else {
            alertaError("Debes seleccionar una persona para modificar.");
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
            alertaInformacion("Persona eliminada correctamente.");
        } else {
            alertaError("Debes seleccionar una persona para eliminar.");
        }
    }

    /**
     * Guarda la nueva persona en la lista y actualiza la tabla.
     * Valida los campos antes de crear la persona y muestra alertas según sea necesario.
     *
     * @param event el evento de acción del botón "Guardar"
     */
    void guardar(ActionEvent event) {
        // Valida que los campos sean correctos
        String errores = validarCampos();

        if (errores.isEmpty()) {
            // Crea la persona
            Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
            // La añade a la tabla
            aniadirPersona(p);
            alertaInformacion("Se ha añadido a la persona correctamente");
        } else {
            alertaError(errores);
        }
    }

    /**
     * Modifica los detalles de la persona seleccionada.
     *
     * @param persona la persona a modificar
     */
    private void modificar(Persona persona) {
        String errores = validarCampos();

        if (errores.isEmpty()) {
            persona.setNombre(tfNombre.getText());
            persona.setApellidos(tfApellidos.getText());
            persona.setEdad(Integer.parseInt(tfEdad.getText()));
            tableInfo.refresh(); // Actualiza la tabla
            alertaInformacion("Persona modificada correctamente.");
        } else {
            alertaError(errores);
        }
    }

    /**
     * Agrega una persona a la lista y actualiza la tabla.
     *
     * @param persona la persona a añadir
     */
    private void aniadirPersona(Persona persona) {
        personas.add(persona);
        tableInfo.setItems(personas);
    }

    /**
     * Valida los campos de entrada y devuelve un mensaje de error si hay campos inválidos.
     *
     * @return un mensaje de error si hay errores, de lo contrario, una cadena vacía
     */
    private String validarCampos() {
        StringBuilder errores = new StringBuilder();

        if (tfNombre.getText().isEmpty()) {
            errores.append("El campo Nombre no puede estar vacío.\n");
        }
        if (tfApellidos.getText().isEmpty()) {
            errores.append("El campo Apellidos no puede estar vacío.\n");
        }
        if (tfEdad.getText().isEmpty()) {
            errores.append("El campo Edad no puede estar vacío.\n");
        } else {
            try {
                Integer.parseInt(tfEdad.getText());
            } catch (NumberFormatException e) {
                errores.append("El campo Edad debe ser un número.\n");
            }
        }

        return errores.toString();
    }

    /**
     * Muestra un mensaje de alerta de error.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void alertaError(String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de alerta de información.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void alertaInformacion(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
