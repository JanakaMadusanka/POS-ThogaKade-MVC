package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {
    public AnchorPane dashboardFormPane;
    public Button btnCustomer;
    public Button btnItem;
    public Button btnOrders;
    public Button btnOrderDetail;
    public Label lblTime;


    public void initialize(){
        calculateTime();
    }

    private void calculateTime() {

        Timeline timeLine = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")))
        ), new KeyFrame(Duration.seconds(1)));
        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)dashboardFormPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"))));
        stage.setTitle("Customer Form");
        stage.show();
    }

    public void btnItemOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)dashboardFormPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"))));
        stage.setTitle("Item Form");
        stage.show();
    }

    public void btnOrderDetailOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)dashboardFormPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrderDetailForm.fxml"))));
        stage.setTitle("Order Detail Form");
        stage.show();
    }

    public void btnOrdersOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)dashboardFormPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrdersForm.fxml"))));
        stage.setTitle("Orders Form");
        stage.show();
    }
}
