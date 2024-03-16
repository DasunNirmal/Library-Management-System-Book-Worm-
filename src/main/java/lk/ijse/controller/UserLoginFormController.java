package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.RegExPatterns.RegExPatterns;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dao.custom.impl.UserDAOImpl;

import java.io.IOException;

public class UserLoginFormController {

    @FXML
    private CheckBox checkBox;

    @FXML
    private ImageView imgLock;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPasswordField;

    @FXML
    private TextField txtPasswordTextField;

    @FXML
    private TextField txtUserName;

    UserDAO userDAO = new UserDAOImpl();

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

    @FXML
    void btnLoginOnAction(ActionEvent event)  {
        String name = txtUserName.getText();
        String password = txtPasswordField.getText();

        boolean isUserValid = RegExPatterns.getValidName().matcher(name).matches();
        boolean isPasswordValid = RegExPatterns.getValidPassword().matcher(password).matches();

        if (!isUserValid){
            new Alert(Alert.AlertType.ERROR,"Can Not Leave Name Empty").showAndWait();
            return;
        }if (!isPasswordValid){
            new Alert(Alert.AlertType.ERROR,"Can Not Leave Password Empty").showAndWait();
        } else {
            try {
                boolean isValid = userDAO.isValidUser(name,password);
                if (isValid) {
                    goToMainForm();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
                }
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }
    }

    private void goToMainForm() throws IOException {
        /*rootNode.getScene().getWindow().hide();*/
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_form.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void txtBtnLoginOnAction(ActionEvent event) {
        btnLoginOnAction(new ActionEvent());
    }

    @FXML
    void txtGoToPasswordOnAction(ActionEvent event) {
        txtPasswordField.requestFocus();
    }
    @FXML
    void txtGoToLoginBtnOnAction(ActionEvent event) {
        txtPasswordField.requestFocus();
    }
}
