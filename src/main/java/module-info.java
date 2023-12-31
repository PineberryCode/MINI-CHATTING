module com.project {
    opens com.project.model;

    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;

    //Spring requirements
    requires spring.beans;
    requires spring.context;
    requires spring.data.mongodb;
    requires spring.security.crypto;

    opens com.project.controller to javafx.fxml;

    exports com.project.controller to javafx.fxml;
    exports com.project;
}
