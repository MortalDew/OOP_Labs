module com.example.lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires FigureLib;
    requires LibForLab2;


    opens com.example.lab2 to javafx.fxml;
    exports com.example.lab2;
}