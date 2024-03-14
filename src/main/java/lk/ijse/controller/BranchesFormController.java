package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;

public class BranchesFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBranchID;

    @FXML
    private TableColumn<?, ?> colBranchManager;

    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colStaff;

    @FXML
    private Label lblBranchID;

    @FXML
    private TableView<?> tblBranches;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtManager;

    @FXML
    private TextField txtSearchBranches;

    @FXML
    private TextField txtStaff;

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.BRANCHES);

    public void initialize() {
        generateNextID();
    }

    private void generateNextID() {
        String branchID = branchBO.generateBranchID();
        lblBranchID.setText(branchID);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToManagerOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToSaveOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToStaffOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(KeyEvent event) {

    }
}
