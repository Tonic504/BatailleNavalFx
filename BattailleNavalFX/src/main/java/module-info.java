module com.example.battaillenavalfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.battaillenavalfx to javafx.fxml;
    exports com.example.battaillenavalfx;
}