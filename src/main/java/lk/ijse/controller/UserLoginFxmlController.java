package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class UserLoginFxmlController {

    @FXML
    private PasswordField txtPasswordField;

    @FXML
    private TextField txtPasswordTextField;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView imgLock;


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
}
