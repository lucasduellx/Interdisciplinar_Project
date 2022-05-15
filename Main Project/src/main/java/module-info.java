module com.intellichurras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.intellichurras to javafx.fxml;
    exports com.intellichurras;

    opens helper to javafx.fxml;
    exports helper;
}
