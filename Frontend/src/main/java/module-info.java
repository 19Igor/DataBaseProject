module com.nsu.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires ojdbc8;

    opens com.nsu.App to javafx.fxml;
    exports com.nsu.App;
    exports com.nsu.App.Controllers;
    opens com.nsu.App.Controllers to javafx.fxml;
    exports com.nsu.App.Viewies;
    opens com.nsu.App.Viewies to javafx.fxml;
    exports com.nsu.App.Model.TableOutput;
    opens com.nsu.App.Model.TableOutput to javafx.fxml;
}