package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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

    public void initialize() {
        selectCss(btnDashboard);
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
    void btnBooksOnAction(ActionEvent event) {
        selectCss(btnBooks);
    }

    @FXML
    void btnBranchesOnAction(ActionEvent event) {
        selectCss(btnBranches);
    }

    @FXML
    void btnMembersOnAction(ActionEvent event) {
        selectCss(btnMembers);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {
        selectCss(btnDashboard);
    }

    @FXML
    void btnTransactionsOnAction(ActionEvent event) {
        selectCss(btnTransactions);
    }
}
