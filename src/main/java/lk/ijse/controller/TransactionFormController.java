package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class TransactionFormController {


    @FXML
    private TableColumn<?, ?> colBook;

    @FXML
    private TableColumn<?, ?> colBorrowingDate;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colMemberID;

    @FXML
    private TableColumn<?, ?> colMember;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colRemove;

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
    private TextField txtQty;

    @FXML
    private TextField txtSearchBooks;

    @FXML
    private TextField txtSearchMembers;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

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
    void txtGotoBtnAddOnAction(ActionEvent event) {

    }
}
