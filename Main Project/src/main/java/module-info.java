module com.intellichurras {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.net.http;
    requires org.json;

    opens com.intellichurras to javafx.fxml;
    exports com.intellichurras;

    opens com.helper to javafx.fxml;
    exports com.helper;
}
