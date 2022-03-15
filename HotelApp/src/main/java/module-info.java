module com.lqv.hotelapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;

    opens com.lqv.hotelapp to javafx.fxml;
    exports com.lqv.hotelapp;
    exports com.lqv.pojo;
}
