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
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BooksDto;
import lk.ijse.dto.BranchesDto;
import lk.ijse.dto.tm.BooksTm;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;

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
    private TableColumn<?, ?> colBranchID;

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
    private TableView<BooksTm> tblBooks;

    @FXML
    private ToggleGroup tgAvailability;

    @FXML
    private Label lblBranchID;

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
        loadAllBooks();
        setCellValueFactory();
        tableListener();
        autoComplete();
    }

    private void tableListener() {
        tblBooks.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void clearFields() {
        lblBookID.setText("");
        txtTitle.setText("");
        txtGenre.setText("");
        txtAuthor.setText("");
        txtBookQty.setText("");
        lblBranchName.setText("");
        txtSearchBranch.setText("");
        rbYes.setSelected(false);
        rbNo.setSelected(false);
    }

    private void setData(BooksTm row) {
        if (row != null) {
            lblBookID.setText(row.getBookID());
            txtTitle.setText(row.getTitle());
            txtGenre.setText(row.getGenre());
            txtAuthor.setText(row.getAuthor());
            txtBookQty.setText(row.getQty());
            lblBranchName.setText(row.getBranchName());
            if (row.getAvailability().startsWith("Yes") || row.getAvailability().startsWith("yes")) {
                rbYes.setSelected(true);
                rbNo.setSelected(false);
            } else if (row.getAvailability().startsWith("No") || row.getAvailability().startsWith("no")) {
                rbYes.setSelected(false);
                rbNo.setSelected(true);
            }
        }
    }

    private void setCellValueFactory() {
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colBranchID.setCellValueFactory(new PropertyValueFactory<>("branchID"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblBooks.setId("my-table");
    }

    private void loadAllBooks() {
        try {
            obList.clear();
            List<BooksDto> dtoList = booksBO.getAllBooks();
            for (BooksDto dto : dtoList) {
                obList.add(new BooksTm(
                        dto.getBookID(),
                        dto.getTitle(),
                        dto.getGenre(), dto.getAuthor(), dto.getBranchID(), dto.getBranchName(), dto.getAvailability(), dto.getQty()));
            }
            tblBooks.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        String branchID = lblBranchID.getText();
        String qty = txtBookQty.getText();
        String branchName = lblBranchName.getText();
        String available = null;
        if (rbYes.isSelected()) {
             available = rbYes.getText();
        } else if (rbNo.isSelected()) {
             available = rbNo.getText();
        }

        try {
            boolean isSaved = booksBO.saveBooks(new BooksDto(id,title,genre,author,branchID,branchName,available,qty));
            if (isSaved) {
                clearFields();
                generateNextID();
                loadAllBooks();
                new Alert(Alert.AlertType.CONFIRMATION,"Book is Added").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblBookID.getText();
        String title = txtTitle.getText();
        String genre = txtGenre.getText();
        String author = txtAuthor.getText();
        String qty = txtBookQty.getText();
        String branchName = lblBranchName.getText();
        if (!rbYes.isSelected() && !rbNo.isSelected()) {
            new Alert(Alert.AlertType.ERROR,"Cannot Leave Unselected ! (Yes || No)").show();
        }
        try {
            boolean isDeleted = booksBO.deleteBooks(id);
            if (isDeleted) {
                clearFields();
                generateNextID();
                loadAllBooks();
                new Alert(Alert.AlertType.CONFIRMATION,"Book Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = lblBookID.getText();
        String title = txtTitle.getText();
        String genre = txtGenre.getText();
        String author = txtAuthor.getText();
        String branchID = lblBranchID.getText();
        String qty = txtBookQty.getText();
        String branchName = lblBranchName.getText();
        String available = null;
        if (rbYes.isSelected()) {
            available = rbYes.getText();
        } else if (rbNo.isSelected()) {
            available = rbNo.getText();
        }

        try {
            boolean isUpdated = booksBO.updateBooks(new BooksDto(id,title,genre,author,branchID,branchName,available,qty));
            if (isUpdated) {
                clearFields();
                loadAllBooks();
                generateNextID();
                new Alert(Alert.AlertType.CONFIRMATION,"Book Is Updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchBranchOnAction(ActionEvent event) {
        String searchInput = txtSearchBranch.getText();
        try {
            BranchesDto branchesDto;
            branchesDto = branchBO.searchBranch(searchInput);
            if (branchesDto != null) {
                txtSearchBranch.setText("");
                lblBranchID.setText(branchesDto.getId());
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
        searchTableFilter();
    }

    private void searchTableFilter() {
        FilteredList<BooksTm> filteredBooList = new FilteredList<>(obList, b -> true);
        txtSearchBooks.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredBooList.setPredicate(booksTm -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();

                if (booksTm.getBookID().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getBookID().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getTitle().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getTitle().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getGenre().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getGenre().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getAuthor().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getAuthor().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getBranchID().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getBranchID().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getBranchName().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getBranchName().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getAvailability().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getAvailability().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getQty().toLowerCase().contains(search)) {
                    return true;
                } else if (booksTm.getQty().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<BooksTm> sortedList = new SortedList<>(filteredBooList);
        sortedList.comparatorProperty().bind(tblBooks.comparatorProperty());
        tblBooks.setItems(sortedList);
    }

    private void autoComplete() {
        String[] id = branchBO.searchBranchID(txtSearchBranch.getText());
        TextFields.bindAutoCompletion(txtSearchBranch, id);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextID();
    }

    @FXML
    void txtGoToAuthorOnAction(ActionEvent event) {
        txtAuthor.requestFocus();
    }

    @FXML
    void txtGoToBookCountOnAction(ActionEvent event) {
        txtBookQty.requestFocus();
    }

    @FXML
    void txtGoToGenreOnAction(ActionEvent event) {
        txtGenre.requestFocus();
    }

    @FXML
    void txtGoToSearchBranchOnAction(ActionEvent event) {
        txtSearchBranch.requestFocus();
    }
}
