package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {

    @FXML
    private JFXButton btnBooks;

    @FXML
    private JFXButton btnBranches;

    @FXML
    private JFXButton btnDashboard;

    @FXML
    private JFXButton btnMembers;

    @FXML
    private JFXButton btnTransactions;

    @FXML
    private AnchorPane mainNode;

    public void initialize() throws IOException {
        selectCss(btnDashboard);
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        this.mainNode.getChildren().clear();
        this.mainNode.getChildren().add(anchorPane);
    }

    private void selectCss(JFXButton mainFormBtn){
        this.btnDashboard.getStyleClass().remove("select_button");
        this.btnTransactions.getStyleClass().remove("select_button");
        this.btnBooks.getStyleClass().remove("select_button");
        this.btnMembers.getStyleClass().remove("select_button");
        this.btnBranches.getStyleClass().remove("select_button");

        this.btnDashboard.getStyleClass().add("default_button");
        this.btnTransactions.getStyleClass().add("default_button");
        this.btnBooks.getStyleClass().add("default_button");
        this.btnMembers.getStyleClass().add("default_button");
        this.btnBranches.getStyleClass().add("default_button");

        mainFormBtn.getStyleClass().remove("default_button");
        mainFormBtn.getStyleClass().add("select_button");
    }

    @FXML
    void btnBooksOnAction(ActionEvent event) throws IOException {
        selectCss(btnBooks);
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/books_form.fxml"));
        this.mainNode.getChildren().clear();
        this.mainNode.getChildren().add(anchorPane);
    }

    @FXML
    void btnBranchesOnAction(ActionEvent event) throws IOException {
        selectCss(btnBranches);
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/branches_form.fxml"));
        this.mainNode.getChildren().clear();
        this.mainNode.getChildren().add(anchorPane);
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) throws IOException {
        selectCss(btnMembers);
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/members_form.fxml"));
        this.mainNode.getChildren().clear();
        this.mainNode.getChildren().add(anchorPane);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        selectCss(btnDashboard);
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        this.mainNode.getChildren().clear();
        this.mainNode.getChildren().add(anchorPane);
    }

    @FXML
    void btnTransactionsOnAction(ActionEvent event) throws IOException {
        selectCss(btnTransactions);
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/transaction_form.fxml"));
        this.mainNode.getChildren().clear();
        this.mainNode.getChildren().add(anchorPane);
    }
}
