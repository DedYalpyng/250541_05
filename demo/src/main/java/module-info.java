module sample.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens sample.demo to javafx.fxml;
    exports sample.demo;
}