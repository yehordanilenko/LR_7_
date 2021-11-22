module com.example.demo1lr7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.fasterxml.jackson.annotation;
    requires json.simple;

    opens com.example.demo1lr7 to javafx.fxml;
    exports com.example.demo1lr7;
}