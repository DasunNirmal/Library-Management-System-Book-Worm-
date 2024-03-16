package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.RegExPatterns.RegExPatterns;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationFormController {

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView imgLock;

    @FXML
    private AnchorPane node;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPasswordField;

    @FXML
    private TextField txtPasswordTextField;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.USER);


    @FXML
    void changeVisibilityOnAction(ActionEvent event) {
        if (checkBox.isSelected()) {
            txtPasswordTextField.setText(txtPasswordField.getText());
            txtPasswordTextField.setVisible(true);
            txtPasswordField.setVisible(false);
            imgLock.setVisible(false);
            return;
        }
        txtPasswordField.setText(txtPasswordTextField.getText());
        txtPasswordField.setVisible(true);
        txtPasswordTextField.setVisible(false);
        imgLock.setVisible(true);
    }

    private void clearFields() {
        txtUserName.setText("");
        txtEmail.setText("");
        txtPasswordField.setText("");
        txtPasswordTextField.setText("");
    }

    @FXML
    void imgBackONAction(MouseEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/user_login_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) this.node.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Login");
    }


    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPasswordField.getText();

        boolean isUserValid = RegExPatterns.getValidName().matcher(name).matches();
        boolean isEmailValid = RegExPatterns.getValidEmail().matcher(email).matches();
        boolean isPasswordValid = RegExPatterns.getValidPassword().matcher(password).matches();

        if (!isUserValid){
            new Alert(Alert.AlertType.ERROR,"Can Not Leave Name Empty").showAndWait();
            return;
        }if (!isEmailValid){
            new Alert(Alert.AlertType.ERROR,"Can Not Leave Email Empty").showAndWait();
            return;
        }if (!isPasswordValid){
            new Alert(Alert.AlertType.ERROR,"Can Not Leave Password Empty").showAndWait();
        } else {
            try {
                boolean isSaved = userBO.saveUser(new UserDto(email,password,name));
                if (isSaved) {
                    clearFields();
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    @FXML
    void txtGoToBtnRegistrationOnAction(ActionEvent event) {
        btnRegisterOnAction(new ActionEvent());
    }

    @FXML
    void txtGoToEmailOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtGoToPasswordOnAction(ActionEvent event) {
        txtPasswordField.requestFocus();
    }
}
