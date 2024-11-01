package es.guillearana.ejercicioe.controlador;

import es.guillearana.ejercicioe.model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la gestión de la ventana modal de entrada de datos de una persona.
 * Este controlador maneja la lógica de negocio y la interacción con la interfaz de usuario
 * para la creación o modificación de objetos {@link Persona}.
 */
public class ControllerModalEjerE {

    /** Campo de texto para el nombre de la persona. */
    @FXML
    private TextField txtNombre;

    /** Campo de texto para los apellidos de la persona. */
    @FXML
    private TextField txtApellidos;

    /** Campo de texto para la edad de la persona. */
    @FXML
    private TextField txtEdad;

    /** Botón para guardar la persona. */
    @FXML
    private Button btnGuardar;

    /** Botón para cancelar la operación. */
    @FXML
    private Button btnCancelar;

    /** Objeto persona que se está creando o modificando. */
    private Persona persona;

    /** Indica si se está modificando una persona existente. */
    private boolean isEditing = false;

    /**
     * Constructor por defecto.
     */
    public ControllerModalEjerE() {
        // Constructor por defecto vacío
    }

    /**
     * Maneja la acción de guardar una persona.
     * Valida los campos de entrada y crea o modifica un objeto {@link Persona} si los campos son válidos.
     * Si los datos son incorrectos, muestra una alerta con el mensaje de error.
     *
     * @param event el evento de acción del botón "Guardar"
     */
    @FXML
    void guardarPersona(ActionEvent event) {
        String errores = validarCampos();
        if (errores.isEmpty()) {
            if (isEditing) {
                // Modificar la persona existente
                persona.setNombre(txtNombre.getText());
                persona.setApellidos(txtApellidos.getText());
                persona.setEdad(Integer.parseInt(txtEdad.getText()));
            } else {
                // Crear nueva persona
                persona = new Persona(txtNombre.getText(), txtApellidos.getText(), Integer.parseInt(txtEdad.getText()));
            }
            cerrarVentana();
        } else {
            mostrarAlertaError(errores);
        }
    }

    /**
     * Maneja la acción de cancelar la operación.
     * Cierra la ventana modal sin realizar cambios.
     *
     * @param event el evento de acción del botón "Cancelar"
     */
    @FXML
    void cancelarPersona(ActionEvent event) {
        cerrarVentana();
    }

    /**
     * Devuelve el objeto {@link Persona} creado o modificado.
     *
     * @return el objeto {@link Persona} creado o modificado, o {@code null} si no se ha creado
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Establece los datos de una persona existente para editarla.
     * Rellena los campos de texto con los datos de la persona.
     *
     * @param persona la persona a editar
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
        this.isEditing = true;

        // Rellenar los campos de texto con los datos de la persona
        txtNombre.setText(persona.getNombre());
        txtApellidos.setText(persona.getApellidos());
        txtEdad.setText(String.valueOf(persona.getEdad()));
    }

    /**
     * Cierra la ventana modal actual.
     */
    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    /**
     * Valida los campos de entrada y devuelve un mensaje de error si hay campos inválidos.
     * Verifica que el nombre, apellidos y edad estén correctamente ingresados.
     * La edad debe ser un número entre 0 y 120.
     *
     * @return un mensaje de error si hay errores; de lo contrario, una cadena vacía
     */
    private String validarCampos() {
        StringBuilder errores = new StringBuilder();

        if (txtNombre.getText().isEmpty()) {
            errores.append("Debe ingresar un nombre.\n");
        }
        if (txtApellidos.getText().isEmpty()) {
            errores.append("Debe ingresar apellidos.\n");
        }
        if (txtEdad.getText().isEmpty()) {
            errores.append("Debe ingresar una edad.\n");
        } else {
            try {
                int edad = Integer.parseInt(txtEdad.getText());
                if (edad < 0 || edad > 120) {
                    errores.append("La edad debe estar entre 0 y 120 años.\n");
                }
            } catch (NumberFormatException e) {
                errores.append("La edad debe ser un número.\n");
            }
        }
        return errores.toString();
    }

    /**
     * Muestra un mensaje de alerta de error con el contenido especificado.
     *
     * @param mensaje el mensaje a mostrar en la alerta
     */
    private void mostrarAlertaError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
