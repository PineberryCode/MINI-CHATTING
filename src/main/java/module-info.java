module com.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.project.view to javafx.fxml;

    exports com.project.view to javafx.fxml;

    exports com.project to javafx.graphics;
}
