package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BooksDAO;
import lk.ijse.dao.custom.MembersDAO;
import lk.ijse.dto.TransactionDto;
import lk.ijse.dto.tm.TransactionTm;

import java.sql.SQLException;
import java.util.List;

public class DashboardFormController {

    @FXML
    private TableColumn<?, ?> colBBook;

    @FXML
    private TableColumn<?, ?> colBMember;

    @FXML
    private TableColumn<?, ?> colBMemberID;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colMember;

    @FXML
    private TableColumn<?, ?> colMemberID;

    @FXML
    private TableColumn<?, ?> colTID;

    @FXML
    private Label lblBorrowedBooks;

    @FXML
    private Label lblMembers;

    @FXML
    private Label lblOverdueBooks;

    @FXML
    private Label lblTotalBooks;

    @FXML
    private TableView<TransactionTm> tblBorrowedList;

    @FXML
    private TableView<TransactionTm> tblOverdueList;

    private ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

    TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.TRANSACTION);

    MembersDAO membersDAO = (MembersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERS);

    BooksDAO booksDAO = (BooksDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKS);


    public void initialize() {
        loadAllOverdueBooks();
        loadAllBorrowedBooks();
        setCellValueFactory();
        borrowedBooks();
        overdueBooks();
        totalMembers();
        totalBooks();
    }

    private void totalBooks() {
        String books = booksDAO.getTotalBooks();
        lblTotalBooks.setText(books);
    }

    private void totalMembers() {
        String members = membersDAO.getTotalMembers();
        lblMembers.setText(members);
    }

    private void overdueBooks() {

    }

    private void borrowedBooks() {

    }

    private void loadAllBorrowedBooks() {
        try {
            obList.clear();
            List<TransactionDto> dtoList = transactionBO.getAllTransaction();
            for (TransactionDto dto : dtoList) {
                obList.add(new TransactionTm(
                        dto.getBorrowingID(), dto.getMemberID(), dto.getMemberName(), dto.getBook(),
                        dto.getGenre(), dto.getBorrowingDate(), dto.getReturningDate(), dto.getBooks().getBookID()));
            }
            tblBorrowedList.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colMemberID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        colMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("book"));
        tblOverdueList.setId("my-table");

        colTID.setCellValueFactory(new PropertyValueFactory<>("borrowingID"));
        colBMemberID.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        colBMember.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colBBook.setCellValueFactory(new PropertyValueFactory<>("book"));
        tblBorrowedList.setId("my-table");
    }

    private void loadAllOverdueBooks() {
        obList.clear();
        List<TransactionDto> dtoList = transactionBO.getAllOverDueBooks();
        for (TransactionDto dto : dtoList) {
            obList.add(new TransactionTm(
                    dto.getBorrowingID(), dto.getMemberID(), dto.getMemberName(), dto.getBook(),
                    dto.getGenre(), dto.getBorrowingDate(), dto.getReturningDate(), dto.getBooks().getBookID()));
        }
        tblOverdueList.setItems(obList);
    }
}
