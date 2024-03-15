package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class TransactionFormController {

    @FXML
    private TableColumn<?, ?> colBook;

    @FXML
    private TableColumn<?, ?> colBorrowingDate;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colMember;

    @FXML
    private TableColumn<?, ?> colMemberID;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private DatePicker dpReturningDate;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblBorrowingID;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblGenre;

    @FXML
    private Label lblMemberID;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblBookID;

    @FXML
    private TextField txtSearchBooks;

    @FXML
    private TextField txtSearchMembers;

    @FXML
    private TextField txtSearchTransaction;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpDateOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToDatePicker(ActionEvent event) {

    }

    @FXML
    void txtGoToSearchBooks(ActionEvent event) {

    }

    @FXML
    void txtSearchTransactionOnAction(KeyEvent event) {

    }
}
