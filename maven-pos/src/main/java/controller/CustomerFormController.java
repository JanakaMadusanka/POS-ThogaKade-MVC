package controller;

import com.jfoenix.controls.JFXButton;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dto.CustomerDto;
import dto.tm.CustomerTm;
import model.CustomerModel;
import model.CustomerModelImpl;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class CustomerFormController {


    @FXML
    private AnchorPane CustomerFormPane;

    @FXML
    private JFXButton btnBack;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReload;

    @FXML
    private TableView<Object> tblCustomer;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableColumn colOption;
    private CustomerModel customerModel = new CustomerModelImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadCustomerTable();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            setData((CustomerTm) newValue);
        });
    }

    private void setData(CustomerTm newValue) {
        if (newValue != null) {
            txtId.setEditable(false);
            txtId.setText(newValue.getId());
            txtName.setText(newValue.getName());
            txtAddress.setText(newValue.getAddress());
            txtSalary.setText(String.valueOf(newValue.getSalary()));
        }
    }

    private void clearFields() {
        tblCustomer.refresh();
        txtSalary.clear();
        txtAddress.clear();
        txtName.clear();
        txtId.clear();
        txtId.setEditable(true);
    }

    private void loadCustomerTable() throws SQLException, ClassNotFoundException {
        ObservableList<Object> tmList = FXCollections.observableArrayList();

        List<CustomerDto> customerDtoList = customerModel.allCustomers();

        Button btn = null;
        for (CustomerDto customerDto : customerDtoList) {
            btn = new Button("Delete");

            CustomerTm c = new CustomerTm(
                    customerDto.getId(),
                    customerDto.getName(),
                    customerDto.getAddress(),
                    customerDto.getSalary(),
                    btn
            );
            btn.setOnAction(actionEvent -> {
                try {
                    deleteCustomer(c.getId());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            tmList.add(c);
        }
        tblCustomer.setItems(tmList);
    }

    private void deleteCustomer(String id) throws ClassNotFoundException, SQLException {

        boolean isDelete = customerModel.deleteCustomer(txtId.getText());
        if(isDelete){
            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted").show();
            loadCustomerTable();
            clearFields();
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Something went wrong").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage)CustomerFormPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        stage.show();
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        loadCustomerTable();
        tblCustomer.refresh();
        clearFields();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        try{
        CustomerDto customerDto = new CustomerDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText()));

        boolean isSaved = customerModel.saveCustomer(customerDto);
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Saved").show();
                clearFields();
            }
        }catch (SQLIntegrityConstraintViolationException ex){
            new Alert(Alert.AlertType.INFORMATION, "Duplicate entry !...").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        CustomerDto customerDto = new CustomerDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText()));

        boolean isUpdate = customerModel.updateCustomer(customerDto);
        if(isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Customer Updated").show();
            loadCustomerTable();
            clearFields();
        }
    }
}

