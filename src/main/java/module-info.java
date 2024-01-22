module com.softwareengineering.labtasks12 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.softwareengineering.labtasks12 to javafx.fxml;
    exports com.softwareengineering.labtasks12;
}