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
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class ForgotPasswordFormController {

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView imgLock;

    @FXML
    private TextField txtEmail;

    @FXML
    private AnchorPane node;

    @FXML
    private PasswordField txtPasswordField;

    @FXML
    private TextField txtPasswordTextField;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.USER);

    public void initialize() {
        txtUserName.setDisable(true);
        clearFields();
    }

    private void clearFields() {
        txtEmail.setText("");
        txtUserName.setText("");
        txtPasswordField.setText("");
    }

    @FXML
    void btnChangeOnAction(ActionEvent event) {
        String email = txtEmail.getText();
        String name = txtUserName.getText();
        String pw = txtPasswordField.getText();

        try {
            boolean isUpdated = userBO.updateUser(new UserDto(email,pw,name));
            if (isUpdated) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION,"Your Password Updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

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
    void txtGoToBtnChangeOnAction(ActionEvent event) {
        btnChangeOnAction(new ActionEvent());
    }

    @FXML
    void txtSearchUser(ActionEvent event) {
        String email = txtEmail.getText();
        try {
            UserDto userDto;
            userDto = userBO.searchUser(email);
            if (userDto != null) {
                txtUserName.setText(userDto.getUserName());
            } else {
                new Alert(Alert.AlertType.ERROR,"Sorry No Such an Account Found Try again").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
