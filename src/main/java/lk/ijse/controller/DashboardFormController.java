package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.TransactionBO;
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
    private TableView<TransactionTm> tblOverdueList;

    @FXML
    private TableView<TransactionTm> tblBorrowedList;

    private ObservableList<TransactionTm> obList = FXCollections.observableArrayList();

    TransactionBO transactionBO = (TransactionBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.TRANSACTION);

    public void initialize() {
        loadAllOverdueBooks();
        loadAllBorrowedBooks();
        setCellValueFactory();
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
