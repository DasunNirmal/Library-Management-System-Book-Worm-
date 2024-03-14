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
import lk.ijse.bo.custom.MembersBO;
import lk.ijse.dto.MemberDto;
import lk.ijse.dto.tm.MemberTm;

import java.sql.SQLException;
import java.util.List;


public class MembersFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colMemberID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableView<MemberTm> tblMembers;

    @FXML
    private TextField txtSearchMembers;

    @FXML
    private Label lblMemberID;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    private ObservableList<MemberTm> obList = FXCollections.observableArrayList();

    MembersBO membersBO = (MembersBO) BOFactory.getBoFactory().grtBo(BOFactory.BOTypes.MEMBERS);

    public void initialize() {
        generateNextID();
        LoadAllMembers();
        setCellValueFactory();
        tableListener();
    }

    private void clearFields() {
        lblMemberID.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtSearchMembers.setText("");
    }

    private void tableListener() {
        tblMembers.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);
        });
    }

    private void setData(MemberTm row) {
        if (row != null) {
            lblMemberID.setText(row.getId());
            txtName.setText(row.getName());
            txtPhoneNumber.setText(String.valueOf(row.getPhoneNumber()));
            txtEmail.setText(row.getEmail());
            txtAddress.setText(row.getAddress());
        }
    }


    private void setCellValueFactory() {
        colMemberID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblMembers.setId("my-table");
    }

    private void LoadAllMembers() {
        try {
            obList.clear();
            List<MemberDto> dtoList = membersBO.getAllMembers();
            for (MemberDto dto : dtoList) {
                obList.add(
                        new MemberTm(
                                dto.getId(), dto.getName(), dto.getPhoneNumber(), dto.getEmail(), dto.getAddress()));
            }
            tblMembers.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextID() {
        String memberID = membersBO.generateNextMemberID();
        lblMemberID.setText(memberID);
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = lblMemberID.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        try {
            boolean isSaved = membersBO.saveMember(new MemberDto(id,name,phoneNumber,email,address));
            if (isSaved) {
                clearFields();
                LoadAllMembers();
                generateNextID();
                new Alert(Alert.AlertType.INFORMATION,"Member Saved").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = lblMemberID.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        try {
            boolean isUpdated = membersBO.updateMember(new MemberDto(id,name,phoneNumber,email,address));
            if (isUpdated) {
                LoadAllMembers();
                clearFields();
                generateNextID();
                new Alert(Alert.AlertType.INFORMATION,"Member Updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = lblMemberID.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();

        try {
            boolean isDeleted = membersBO.deleteMember(id);
            if (isDeleted) {
                LoadAllMembers();
                clearFields();
                generateNextID();
                new Alert(Alert.AlertType.INFORMATION,"Member Deleted").show();
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
        FilteredList<MemberTm> filteredMemberList = new FilteredList<>(obList, b -> true);
        txtSearchMembers.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredMemberList.setPredicate(memberTm -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String search = newValue.toLowerCase();

                if (memberTm.getId().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getId().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getName().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getName().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getPhoneNumber().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getPhoneNumber().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getEmail().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getEmail().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getAddress().toLowerCase().contains(search)) {
                    return true;
                } else if (memberTm.getAddress().toLowerCase().contains(search)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
        SortedList<MemberTm> sortedList = new SortedList<>(filteredMemberList);
        sortedList.comparatorProperty().bind(tblMembers.comparatorProperty());
        tblMembers.setItems(sortedList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextID();
    }

    @FXML
    void txtBtnSaveOnAction(ActionEvent event) {
        btnSaveOnAction(new ActionEvent());
    }

    @FXML
    void txtGoToAddressOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    void txtGoToEmailOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtGoToPhoneNumberOnAction(ActionEvent event) {
        txtPhoneNumber.requestFocus();
    }
}
