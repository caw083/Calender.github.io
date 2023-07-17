module com.tutorial {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tutorial to javafx.fxml;
    exports com.tutorial;
}
