module com.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;

    opens com.project.controller to javafx.fxml;

    exports com.project.controller to javafx.fxml;
    exports com.project;
}
