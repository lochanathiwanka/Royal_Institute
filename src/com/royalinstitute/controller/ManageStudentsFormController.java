package com.royalinstitute.controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.royalinstitute.bo.BOFactory;
import com.royalinstitute.bo.custom.StudentBO;
import com.royalinstitute.dto.ProgramDTO;
import com.royalinstitute.dto.StudentDTO;
import com.royalinstitute.stage.StageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.util.List;

public class ManageStudentsFormController extends StageList {
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnRefresh;
    public JFXTextField txtFind;
    public AnchorPane titlePane;
    public TextField txtContact;
    public TextField txtDOB;
    public TextField txtGender;

    @FXML
    private TableView<StudentDTO> tblStudents;

    @FXML
    private TableColumn<StudentDTO, String> clmID;

    @FXML
    private TableColumn<StudentDTO, String> clmName;

    @FXML
    private TableColumn<StudentDTO, String> clmAddress;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize() {
        getAllStudent();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        new ZoomIn(titlePane).setSpeed(0.4).play();
    }

    private void getAllStudent() {
        try {
            List<StudentDTO> list = studentBO.getAll();
            ObservableList<StudentDTO> rows = FXCollections.observableArrayList();
            rows.addAll(list);
            tblStudents.setItems(rows);
            setTblStudentCellValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTblStudentCellValue() {
        clmID.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("sid"));
        clmName.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<StudentDTO, String>("address"));
    }

    public void txtFindOnActionOnKeyReleased(KeyEvent keyEvent) {
        try {
            List<StudentDTO> programList = studentBO.findStudents(txtFind.getText());
            ObservableList<StudentDTO> list = FXCollections.observableArrayList();
            list.addAll(programList);
            tblStudents.setItems(list);
            setTblStudentCellValue();
            txtID.clear();
            txtName.clear();
            txtAddress.clear();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (Exception e) {
            getAllStudent();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            studentBO.update(new StudentDTO(txtID.getText(), txtName.getText(), txtAddress.getText(), txtContact.getText(), txtDOB.getText(), txtGender.getText()));
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Student Update");
            notification.setMessage("Successfully Updated!!");
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.showAndDismiss(Duration.millis(2000));
            getAllStudent();
            refreshFields();
        } catch (Exception e) {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Student");
            notification.setMessage("Update error..No Student found!");
            notification.setAnimationType(AnimationType.POPUP);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            studentBO.delete(txtID.getText());
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Student Delete");
            notification.setMessage("Successfully Deleted!!");
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.showAndDismiss(Duration.millis(2000));
            getAllStudent();
            refreshFields();
        } catch (Exception e) {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Student");
            notification.setMessage("Delete error..No Student found!");
            notification.setAnimationType(AnimationType.POPUP);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    private void refreshFields() {
        tblStudents.getSelectionModel().clearSelection();
        txtFind.clear();
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();
        txtGender.clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        refreshFields();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        mainMenuFormStage.show();
        manageStudentsFormStage.close();
    }

    public void tblStudentsOnMouseClicked(MouseEvent mouseEvent) {
        try {
            if (!tblStudents.getItems().isEmpty()) {
                txtID.setText(tblStudents.getSelectionModel().getSelectedItem().getSid());
                txtName.setText(tblStudents.getSelectionModel().getSelectedItem().getName());
                txtAddress.setText(tblStudents.getSelectionModel().getSelectedItem().getAddress());
                txtContact.setText(tblStudents.getSelectionModel().getSelectedItem().getContact());
                txtDOB.setText(tblStudents.getSelectionModel().getSelectedItem().getDob());
                txtGender.setText(tblStudents.getSelectionModel().getSelectedItem().getGender());
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            }
        } catch (NullPointerException ex) {}
    }
}
