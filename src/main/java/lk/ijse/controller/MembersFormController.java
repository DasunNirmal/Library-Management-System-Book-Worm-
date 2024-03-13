package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MembersBO;
import lk.ijse.dto.MemberDto;

import java.sql.SQLException;


public class MembersFormController {

    @FXML
    private Label lblMemberID;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    MembersBO membersBO = (MembersBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.MEMBERS);

    public void initialize() {
        generateNextID();
    }

    private void generateNextID() {
        String previousID = lblMemberID.getText();
        String memberID = membersBO.generateNextMemberID();
        lblMemberID.setText(memberID);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblMemberID.getText();
        String name = txtName.getText();
        int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        try {
            boolean isSaved = membersBO.saveMember(new MemberDto(id,name,phoneNumber,email,address));
            if (isSaved) {
                generateNextID();
                new Alert(Alert.AlertType.INFORMATION,"Member Saved").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void txtBtnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToAddressOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToPhoneNumberOnAction(ActionEvent event) {

    }
}
