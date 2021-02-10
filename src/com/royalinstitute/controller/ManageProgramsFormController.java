package com.royalinstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.royalinstitute.bo.BOFactory;
import com.royalinstitute.bo.custom.ProgramBO;
import com.royalinstitute.dto.ProgramDTO;
import com.royalinstitute.stage.StageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageProgramsFormController extends StageList {
    public TextField txtID;
    public TextField txtProgram;
    public TextField txtDuration;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnAdd;
    public TextField txtFee;
    public JFXButton btnClearSelection;
    public JFXTextField txtFind;

    @FXML
    private TableView<ProgramDTO> tblProgram;

    @FXML
    private TableColumn<ProgramDTO, String> clmID;

    @FXML
    private TableColumn<ProgramDTO, String> clmProgram;

    @FXML
    private TableColumn<ProgramDTO, String> clmDuration;

    @FXML
    private TableColumn<ProgramDTO, String> clmFee;

    ProgramBO programBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);

    public void initialize() {
        generateId();
        getAllPrograms();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void generateId() {
        try {
            String lastPid = programBO.getLastId();
            int newId = Integer.parseInt(lastPid.substring(1, 4))+1;
            if (newId < 10) {
                txtID.setText("P00"+newId);
            }else if (newId < 100) {
                txtID.setText("P0"+newId);
            }else {
                txtID.setText("P"+newId);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            txtID.setText("P001");
        }
    }

    private void getAllPrograms() {
        try {
            List<ProgramDTO> list = programBO.getAll();
            ObservableList<ProgramDTO> rows = FXCollections.observableArrayList();
            rows.addAll(list);
            tblProgram.setItems(rows);
            setTblProgramCellValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTblProgramCellValue() {
        clmID.setCellValueFactory(new PropertyValueFactory<ProgramDTO, String>("pid"));
        clmProgram.setCellValueFactory(new PropertyValueFactory<ProgramDTO, String>("program"));
        clmDuration.setCellValueFactory(new PropertyValueFactory<ProgramDTO, String>("duration"));
        clmFee.setCellValueFactory(new PropertyValueFactory<ProgramDTO, String>("fee"));
    }

    public void txtFindOnKeyReleased(KeyEvent keyEvent) {
        try {
            List<ProgramDTO> programList = programBO.findProgram(txtFind.getText());
            ObservableList<ProgramDTO> list = FXCollections.observableArrayList();
            list.addAll(programList);
            tblProgram.setItems(list);
            setTblProgramCellValue();
            generateId();
            txtProgram.clear();
            txtDuration.clear();
            txtFee.clear();
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (Exception e) {
//            e.printStackTrace();
            getAllPrograms();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            programBO.update(new ProgramDTO(txtID.getText(), txtProgram.getText(), txtDuration.getText(), Double.parseDouble(txtFee.getText())));
            getAllPrograms();
            generateId();
            txtProgram.clear();
            txtDuration.clear();
            txtFee.clear();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Program Adding");
            notification.setMessage("Successfully Updated!!");
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.showAndDismiss(Duration.millis(2000));
        } catch (Exception e) {
//            e.printStackTrace();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Program Adding");;
            notification.setMessage("Error!");
            notification.setNotificationType(NotificationType.ERROR);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            programBO.delete(txtID.getText());
            getAllPrograms();
            generateId();
            txtProgram.clear();
            txtDuration.clear();
            txtFee.clear();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Program Delete");
            notification.setMessage("Successfully Deleted!!");
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.showAndDismiss(Duration.millis(2000));
        } catch (Exception e) {
//            e.printStackTrace();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("Program Delete");
            notification.setMessage("Error!");
            notification.setNotificationType(NotificationType.ERROR);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public boolean checkRegEx(String pattern, String text) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(text);
        return matcher.matches();
    }

    private void addProgram() {
        try {
            programBO.add(new ProgramDTO(txtID.getText(), txtProgram.getText(), txtDuration.getText(), Double.parseDouble(txtFee.getText())));
            getAllPrograms();
            generateId();
            txtProgram.clear();
            txtDuration.clear();
            txtFee.clear();
            new Alert(Alert.AlertType.CONFIRMATION, "Program added!", ButtonType.OK).show();
        } catch (Exception e) {
//            e.printStackTrace();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("RegEX");
            notification.setMessage("Error!");
            notification.setNotificationType(NotificationType.ERROR);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        if (checkRegEx("^[A-z\\s0-9]{1,}$", txtProgram.getText())) {
            txtDuration.requestFocus();
            if (checkRegEx("^[A-z\\s0-9]{1,}$", txtDuration.getText())) {
                txtFee.requestFocus();
                if (checkRegEx("^[0-9\\.]{1,}$", txtFee.getText())) {
                    addProgram();
                } else {
                    txtFee.requestFocus();
                    errorNotificationForAddProgram("Please enter a correct decimal value for the Fee!");
                }
            } else {
                txtDuration.requestFocus();
                errorNotificationForAddProgram("Cannot add symbols..please check \" Duration \" field again!");
            }
        } else {
            txtProgram.requestFocus();
            errorNotificationForAddProgram("Cannot add symbols..please check \" Program \" field again!");
        }
    }

    private void errorNotificationForAddProgram(String message) {
        TrayNotification notification = new TrayNotification();
        notification.setAnimationType(AnimationType.FADE);
        notification.setTitle("RegEX");
        notification.setMessage(message);
        notification.setNotificationType(NotificationType.ERROR);
        notification.showAndDismiss(Duration.millis(2000));
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        mainMenuFormStage.show();
        manageProgramsFormStage.close();
    }

    public void txtProgramOnAction(ActionEvent actionEvent) {
        if (checkRegEx("^[A-z\\s0-9]{1,}$", txtProgram.getText())) {
            txtDuration.requestFocus();
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("RegEX");
            notification.setMessage("Cannot add symbols..please check again!");
            notification.setNotificationType(NotificationType.ERROR);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public void txtDurationOnAction(ActionEvent actionEvent) {
        if (checkRegEx("^[A-z\\s0-9]{1,}$", txtDuration.getText())) {
            txtFee.requestFocus();
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("RegEX");
            notification.setMessage("Cannot add symbols..please check again!");
            notification.setNotificationType(NotificationType.ERROR);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public void txtFeeOnAction(ActionEvent actionEvent) {
        if (checkRegEx("^[A-z\\s0-9]{1,}$", txtProgram.getText())) {
            if (checkRegEx("^[A-z\\s0-9]{1,}$", txtDuration.getText())) {
                if (checkRegEx("^[0-9\\.]{1,}$", txtFee.getText())) {
                    addProgram();
                } else {
                    TrayNotification notification = new TrayNotification();
                    notification.setAnimationType(AnimationType.POPUP);
                    notification.setTitle("RegEX");
                    notification.setMessage("Please enter a correct decimal value for the Fee!");
                    notification.setNotificationType(NotificationType.ERROR);
                    notification.showAndDismiss(Duration.millis(2000));
                }
            } else {
                errorNotificationForAddProgram("Cannot add symbols..please check \" Duration \" field again!");
            }
        } else {
            errorNotificationForAddProgram("Cannot add symbols..please check \" Program \" field again!");
        }
    }

    public void tblProgramOnAction(MouseEvent mouseEvent) {
        try {
            txtID.setText(tblProgram.getSelectionModel().getSelectedItem().getPid());
            txtProgram.setText(tblProgram.getSelectionModel().getSelectedItem().getProgram());
            txtDuration.setText(tblProgram.getSelectionModel().getSelectedItem().getDuration());
            txtFee.setText(tblProgram.getSelectionModel().getSelectedItem().getFee()+"");
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        } catch (NullPointerException ex) {

        }
    }

    public void btnClearSelectionOnAction(ActionEvent actionEvent) {
        generateId();
        getAllPrograms();
        txtProgram.clear();
        txtDuration.clear();
        txtFee.clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }
}
