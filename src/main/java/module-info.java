module com.example.demo10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo10 to javafx.fxml;
    opens com.example.demo10.connect4_classes to javafx.fxml;
    exports com.example.demo10;
    exports com.example.demo10.connect4_classes;
}