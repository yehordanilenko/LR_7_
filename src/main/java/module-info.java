/**
 *
 */
module com.example.demo1lr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.fasterxml.jackson.annotation;
    requires json.simple;

    opens com.example.demo1lr7 to javafx.fxml;
    exports com.example.demo1lr7;
}