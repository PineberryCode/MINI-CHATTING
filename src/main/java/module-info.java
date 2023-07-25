module com.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;

    opens com.project.controller to javafx.fxml;

    exports com.project.controller to javafx.fxml;
    exports com.project;
}
