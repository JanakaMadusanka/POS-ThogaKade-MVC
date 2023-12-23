package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderDetailFormController {
    public JFXButton btnBack;
    public AnchorPane OrderDetailFormPane;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage)OrderDetailFormPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        stage.show();
    }
}
