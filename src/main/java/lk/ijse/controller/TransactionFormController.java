package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BooksBO;
import lk.ijse.bo.custom.MembersBO;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dto.BooksDto;
import lk.ijse.dto.MemberDto;
import lk.ijse.dto.TransactionDto;
import lk.ijse.dto.tm.TransactionTm;
import lk.ijse.entity.Books;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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
    private TableView<TransactionTm> tblTransaction;

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

    private ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

    TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.TRANSACTION);

    MembersBO membersBO = (MembersBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.MEMBERS);

    BooksBO booksBO = (BooksBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.BOOKS);

    Books books = new Books();

    public void initialize() {
        lblDate.setText(String.valueOf(LocalDate.now()));
        generateNextID();
        autoCompleteMembers();
        autoCompleteBooks();
        loadAllTransactions();
        setCellValueFactory();
        tableListener();
    }

    private void tableListener() {
        tblTransaction.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(TransactionTm row) {
        if (row != null) {
            lblBorrowingID.setText(row.getBorrowingID());
            lblMemberID.setText(row.getMemberID());
            lblMemberName.setText(row.getMemberName());
            lblBookName.setText(row.getBook());
            lblGenre.setText(row.getGenre());
            lblDate.setText(row.getBorrowingDate());
            dpReturningDate.setValue(LocalDate.parse(row.getReturningDate()));
            lblBookID.setText(row.getBookID());
        }
    }

    private void setCellValueFactory() {
        colMemberID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        colMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colBook.setCellValueFactory(new PropertyValueFactory<>("book"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("borrowingDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returningDate"));
        tblTransaction.setId("my-table");
    }

    private void loadAllTransactions() {
        try {
            obList.clear();
            List<TransactionDto> dtoList = transactionBO.getAllTransaction();
            for (TransactionDto dto : dtoList) {
                obList.add(new TransactionTm(
                        dto.getBorrowingID(), dto.getMemberID(), dto.getMemberName(), dto.getBook(),
                        dto.getGenre(), dto.getBorrowingDate(), dto.getReturningDate(), dto.getBooks().getBookID()));
            }
            tblTransaction.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String borrowingID = lblBorrowingID.getText();
        String memberID = lblMemberID.getText();
        String memberName = lblMemberName.getText();
        String bookName = lblBookName.getText();
        String genre = lblGenre.getText();
        String bDate = lblDate.getText();
        String retuningDate = String.valueOf(dpReturningDate.getValue());
        String bookID = lblBookID.getText();
        books.setBookID(bookID);

        try {
            boolean isSaved = transactionBO.saveTransaction(
                    new TransactionDto(borrowingID,memberID,memberName,bookName,genre,bDate,retuningDate,books));
            if (isSaved) {
                boolean isUpdated = booksBO.updateQty(bookID);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Successful").show();
                    loadAllTransactions();
                    generateNextID();
                }
            } else {
                new Alert(Alert.AlertType.ERROR,"Not Saved").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String borrowingID = lblBorrowingID.getText();
        String retuningDate = String.valueOf(dpReturningDate.getValue());
        String bookID = lblBookID.getText();
        books.setBookID(bookID);

        try {
            boolean isDeleted = transactionBO.deleteTransaction(borrowingID);
            if (isDeleted) {
                boolean isUpdated = booksBO.upDateQtyIfDeleted(bookID);
                if (isUpdated) {
                    generateNextID();
                    loadAllTransactions();
                    new Alert(Alert.AlertType.CONFIRMATION,"Successful").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR,"Not Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {
        btnDeleteOnAction(new ActionEvent());
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
        searchTableFilter();
    }

    private void searchTableFilter() {
        FilteredList<TransactionTm> filteredBooList = new FilteredList<>(obList, b -> true);
        txtSearchTransaction.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredBooList.setPredicate(transactionTm -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();

                if (transactionTm.getBorrowingID().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getBorrowingID().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getMemberID().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getMemberID().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getMemberName().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getMemberName().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getBook().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getBook().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getGenre().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getGenre().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getBorrowingDate().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getBorrowingDate().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getReturningDate().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getReturningDate().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getBookID().toLowerCase().contains(search)) {
                    return true;
                } else if (transactionTm.getBookID().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<TransactionTm> sortedList = new SortedList<>(filteredBooList);
        sortedList.comparatorProperty().bind(tblTransaction.comparatorProperty());
        tblTransaction.setItems(sortedList);
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
}
