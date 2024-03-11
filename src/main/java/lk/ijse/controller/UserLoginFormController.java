package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginFormController {

    @FXML
    private PasswordField txtPasswordField;

    @FXML
    private TextField txtPasswordTextField;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView imgLock;

    @FXML
    private AnchorPane rootNode;


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
    void hypRegistrationOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/registration_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.setTitle("Register");
    }
}
