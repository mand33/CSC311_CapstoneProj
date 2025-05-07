module org.example.csc311_capstoneproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires info.movito.themoviedbapi;
    requires jdk.httpserver;


    opens org.example.csc311_capstoneproj to javafx.fxml;
    exports org.example.csc311_capstoneproj;
    exports org.example.csc311_capstoneproj.controllers;
    opens org.example.csc311_capstoneproj.controllers to javafx.fxml;
}