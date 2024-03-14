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
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BranchesDto;
import lk.ijse.dto.tm.BranchesTm;
import lk.ijse.dto.tm.MemberTm;

import java.sql.SQLException;
import java.util.List;

public class BranchesFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colBranchID;

    @FXML
    private TableColumn<?, ?> colBranchManager;

    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colStaff;

    @FXML
    private Label lblBranchID;

    @FXML
    private TableView<BranchesTm> tblBranches;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtManager;

    @FXML
    private TextField txtSearchBranches;

    @FXML
    private TextField txtStaff;

    private ObservableList<BranchesTm> obList = FXCollections.observableArrayList();

    BranchBO branchBO = (BranchBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.BRANCHES);

    public void initialize() {
        generateNextID();
        loadAllBranches();
        setCellValueFactory();
        tableListener();
    }

    private void clearFields() {
        lblBranchID.setText("");
        txtBranchName.setText("");
        txtStaff.setText("");
        txtManager.setText("");
        txtAddress.setText("");
        txtSearchBranches.setText("");
    }

    private void tableListener() {
        tblBranches.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(BranchesTm row) {
        if (row != null) {
            lblBranchID.setText(row.getId());
            txtBranchName.setText(row.getBranchName());
            txtStaff.setText(row.getStaff());
            txtManager.setText(row.getManager());
            txtAddress.setText(row.getAddress());
        }
    }

    private void setCellValueFactory() {
        colBranchID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colBranchManager.setCellValueFactory(new PropertyValueFactory<>("manager"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("staff"));
        colStaff.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblBranches.setId("my-table");
    }

    private void loadAllBranches() {
        try {
            obList.clear();
            List<BranchesDto> dtoList = branchBO.getAllBranches();
            for (BranchesDto dto : dtoList) {
                obList.add(new BranchesTm(
                        dto.getId(), dto.getBranchName(), dto.getStaff(), dto.getManager(), dto.getAddress()));
            }
            tblBranches.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextID() {
        String branchID = branchBO.generateBranchID();
        lblBranchID.setText(branchID);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblBranchID.getText();
        String branchName = txtBranchName.getText();
        String staff = txtStaff.getText();
        String manager = txtManager.getText();
        String address = txtAddress.getText();

        try {
            boolean isSaved = branchBO.saveBranch(new BranchesDto(id,branchName,staff,manager,address));
            if (isSaved) {
                clearFields();
                generateNextID();
                new Alert(Alert.AlertType.INFORMATION,"Branch Added Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblBranchID.getText();
        String branchName = txtBranchName.getText();
        String staff = txtStaff.getText();
        String manager = txtManager.getText();
        String address = txtAddress.getText();

        try {
            boolean isDeleted = branchBO.deleteBranch(id);
            if (isDeleted) {
                loadAllBranches();
                clearFields();
                generateNextID();
                new Alert(Alert.AlertType.INFORMATION,"Branch is Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = lblBranchID.getText();
        String branchName = txtBranchName.getText();
        String staff = txtStaff.getText();
        String manager = txtManager.getText();
        String address = txtAddress.getText();

        try {
            boolean isUpdate = branchBO.updateBranch(new BranchesDto(id,branchName,staff,manager,address));
            if (isUpdate) {
                loadAllBranches();
                clearFields();
                generateNextID();
                new Alert(Alert.AlertType.INFORMATION,"Branches Updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void txtSearchOnAction(KeyEvent event) {
        searchTableFilter();
    }

    private void searchTableFilter() {
        FilteredList<BranchesTm> filteredBranchesList = new FilteredList<>(obList, b -> true);
        txtSearchBranches.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBranchesList.setPredicate(branchesTm -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();

                if (branchesTm.getId().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getId().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getBranchName().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getBranchName().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getManager().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getManager().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getAddress().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getAddress().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getStaff().toLowerCase().contains(search)) {
                    return true;
                } else if (branchesTm.getStaff().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<BranchesTm> sortedList = new SortedList<>(filteredBranchesList);
        sortedList.comparatorProperty().bind(tblBranches.comparatorProperty());
        tblBranches.setItems(sortedList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextID();
    }

    @FXML
    void txtGoToAddressOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void txtGoToManagerOnAction(ActionEvent event) {
        txtManager.requestFocus();
    }

    @FXML
    void txtGoToSaveOnAction(ActionEvent event) {
        btnSaveOnAction(new ActionEvent());
    }

    @FXML
    void txtGoToStaffOnAction(ActionEvent event) {
        txtStaff.requestFocus();
    }
}
