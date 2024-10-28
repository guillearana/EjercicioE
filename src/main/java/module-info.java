module es.guillearana.ejercicioe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens es.guillearana.ejercicioe to javafx.fxml;
    exports es.guillearana.ejercicioe;
    exports es.guillearana.ejercicioe.controlador;
    opens es.guillearana.ejercicioe.controlador to javafx.fxml;
    opens es.guillearana.ejercicioe.model to javafx.fxml, javafx.base; // Permite acceso a clases del paquete model desde javafx.fxml y javafx.base.
}