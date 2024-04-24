module ru.nsu.primakova {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.primakova to javafx.fxml;
    exports ru.nsu.primakova;
}