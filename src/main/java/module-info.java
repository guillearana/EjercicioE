module es.guillearana.ejercicioe {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.guillearana.ejercicioe to javafx.fxml;
    exports es.guillearana.ejercicioe;
}