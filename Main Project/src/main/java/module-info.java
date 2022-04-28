module com.intellichurras {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.intellichurras to javafx.fxml;
    exports com.intellichurras;
}
