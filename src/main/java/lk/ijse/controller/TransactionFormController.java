package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BooksBO;
import lk.ijse.bo.custom.MembersBO;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dto.BooksDto;
import lk.ijse.dto.MemberDto;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.time.LocalDate;

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

    TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.TRANSACTION);

    MembersBO membersBO = (MembersBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.MEMBERS);

    BooksBO booksBO = (BooksBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.BOOKS);

    public void initialize() {
        lblDate.setText(String.valueOf(LocalDate.now()));
        generateNextID();
        autoCompleteMembers();
        autoCompleteBooks();
    }

    private void autoCompleteBooks() {
        String[] id = booksBO.searchBooksID(txtSearchBooks.getText());
        TextFields.bindAutoCompletion(txtSearchBooks, id);
        String[] name = booksBO.searchBooksName(txtSearchBooks.getText());
        TextFields.bindAutoCompletion(txtSearchBooks, name);
    }

    private void autoCompleteMembers() {
        String[] id = membersBO.searchMemberPhoneNumber(txtSearchMembers.getText());
        TextFields.bindAutoCompletion(txtSearchMembers, id);
    }

    private void generateNextID() {
        String transactionID = transactionBO.generateTransactionID();
        lblBorrowingID.setText(transactionID);
    }

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
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchBooks(ActionEvent event) {
        String searchInput = txtSearchBooks.getText();
        try {
            BooksDto books;
            if (searchInput.matches("[B][0-9]{3,}")) {
                books = booksBO.searchBooksByID(searchInput);
            } else {
                books = booksBO.searchBooks(searchInput);
            }
            if (books != null) {
                lblBookID.setText(books.getBookID());
                lblBookName.setText(books.getTitle());
                lblGenre.setText(books.getGenre());
            } else {
                new Alert(Alert.AlertType.ERROR,"Book Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchMembers(ActionEvent event) {
        String phoneNumber = txtSearchMembers.getText();
        try {
            MemberDto memberDto;
            memberDto = membersBO.searchMember(phoneNumber);
            if (memberDto != null) {
                lblMemberID.setText(memberDto.getId());
                lblMemberName.setText(memberDto.getName());
            } else {
                new Alert(Alert.AlertType.ERROR,"Member Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchTransactionOnAction(KeyEvent event) {

    }
}
