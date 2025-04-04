module org.example.csc311_capstoneproj {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.csc311_capstoneproj to javafx.fxml;
    exports org.example.csc311_capstoneproj;
}