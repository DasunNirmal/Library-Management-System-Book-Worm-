package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BooksBO;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BooksDto;
import lk.ijse.dto.BranchesDto;
import lk.ijse.dto.tm.BooksTm;
import lk.ijse.dto.tm.MemberTm;
import lk.ijse.entity.Branches;

import java.sql.SQLException;

public class BooksFormController {

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colBranch;

    @FXML
    private TableColumn<?, ?> colGenre;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Label lblBookID;

    @FXML
    private Label lblBranchName;

    @FXML
    private RadioButton rbNo;

    @FXML
    private RadioButton rbYes;

    @FXML
    private TableView<?> tblBooks;

    @FXML
    private ToggleGroup tgAvailability;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookQty;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtSearchBooks;

    @FXML
    private TextField txtSearchBranch;

    @FXML
    private TextField txtTitle;

    private ObservableList<BooksTm> obList = FXCollections.observableArrayList();

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.BRANCHES);

    BooksBO booksBO = (BooksBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.BOOKS);

    public void initialize() {
        generateNextID();
    }

    private void generateNextID() {
        String bookID = booksBO.generateBookID();
        lblBookID.setText(bookID);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblBookID.getText();
        String title = txtTitle.getText();
        String genre = txtGenre.getText();
        String author = txtAuthor.getText();
        String qty = txtBookQty.getText();
        String branchName = lblBranchName.getText();
        String available = null;
        if (rbYes.isSelected()) {
             available = rbYes.getText();
        } else if (rbNo.isSelected()) {
             available = rbNo.getText();
        }

        try {
            boolean isSaved = booksBO.saveBooks(new BooksDto(id,title,genre,author,qty,available,branchName));
            if (isSaved) {
                generateNextID();
                new Alert(Alert.AlertType.CONFIRMATION,"Book is Added").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchBranchOnAction(ActionEvent event) {
        String searchInput = txtSearchBranch.getText();
        try {
            BranchesDto branchesDto;
            branchesDto = branchBO.searchBranch(searchInput);
            if (branchesDto != null) {
                lblBranchName.setText(branchesDto.getBranchName());
            } else {
                new Alert(Alert.AlertType.ERROR,"Branch Doesn't Exist").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchBooksOnAction(KeyEvent event) {

    }

    @FXML
    void txtGoToAuthorOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToBookCountOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToGenreOnAction(ActionEvent event) {

    }

    @FXML
    void txtGoToSearchBranchOnAction(ActionEvent event) {

    }
}
