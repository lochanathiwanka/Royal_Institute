package com.royalinstitute.controller;

import animatefx.animation.Flash;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.royalinstitute.bo.BOFactory;
import com.royalinstitute.bo.custom.ProgramBO;
import com.royalinstitute.bo.custom.RegistrationBO;
import com.royalinstitute.bo.custom.StudentBO;
import com.royalinstitute.dto.*;
import com.royalinstitute.stage.StageList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MakeRegistrationFormController extends StageList {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXComboBox cmbId;
    public JFXButton btnRemove;
    public JFXButton btnClear;
    public JFXButton btnRegister;
    public JFXButton btnAdd;
    public TextField txtRegId;
    public TextField txtDate;
    public TextField txtTime;
    public AnchorPane titlePane;

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

    @FXML
    private TableView<CustomeDTO> tblCart;

    @FXML
    private TableColumn<CustomeDTO, String> clmPid;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    RegistrationBO registrationBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.REGISTRATION);
    ProgramBO programBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);

    public void initialize() {
        generateSid();
        generateRegId();
        generateDateTime();
        addValuesToCmbStudent();
        getAllPrograms();
        btnAdd.setDisable(true);
        btnClear.setDisable(true);
        btnRegister.setDisable(true);
        btnRemove.setDisable(true);
        new ZoomIn(titlePane).setSpeed(0.4).play();
    }

    private void generateDateTime(){
        txtDate.setText(LocalDate.now().toString());

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            txtTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
    }

    private void generateSid() {
        try {
            String lastPid = studentBO.getLastId();
            int newId = Integer.parseInt(lastPid.substring(1, 4))+1;
            if (newId < 10) {
                txtId.setText("S00"+newId);
            }else if (newId < 100) {
                txtId.setText("S0"+newId);
            }else {
                txtId.setText("S"+newId);
            }
        } catch (Exception e) {
            txtId.setText("S001");
        }
    }

    private void generateRegId() {
        try {
            String lastPid = registrationBO.getLastId();
            int newId = Integer.parseInt(lastPid.substring(1, 4))+1;
            if (newId < 10) {
                txtRegId.setText("R00"+newId);
            }else if (newId < 100) {
                txtRegId.setText("R0"+newId);
            }else {
                txtRegId.setText("R"+newId);
            }
        } catch (Exception e) {
            txtRegId.setText("R001");
        }
    }

    private void addValuesToCmbStudent() {
        try {
            cmbId.getItems().clear();
            List<StudentDTO> list = studentBO.getAll();
            for (StudentDTO studentDTO : list) {
                cmbId.getItems().add(studentDTO.getSid());
            }
        } catch (Exception e) {
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

    private void setTblCartCellValue() {
        clmPid.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("pid"));
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        tblCart.getItems().remove(tblCart.getSelectionModel().getSelectedIndex());
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        tblCart.getItems().clear();
        btnRemove.setDisable(true);
        btnRegister.setDisable(true);
        btnClear.setDisable(true);
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try {
            StudentDTO student = new StudentDTO(txtId.getText(), txtName.getText(), txtAddress.getText());

            List<RegistrationDetailDTO> registrationDetailList = new ArrayList<>();
            for (int i = 0; i < tblCart.getItems().size(); i++) {
                registrationDetailList.add(new RegistrationDetailDTO(txtRegId.getText(), tblCart.getItems().get(i).getPid()));
            }

            RegistrationDTO registration = new RegistrationDTO(txtRegId.getText(), txtId.getText(), txtDate.getText(), registrationDetailList);

            registrationBO.makeRegistration(student, registration);
            new Alert(Alert.AlertType.CONFIRMATION, "Registration Success!", ButtonType.OK).show();
            generateRegId();
            generateSid();
            txtName.clear();
            txtAddress.clear();
            addValuesToCmbStudent();
            getAllPrograms();
            btnAdd.setDisable(true);
            tblCart.getItems().clear();
            btnRemove.setDisable(true);
            btnClear.setDisable(true);
            btnRegister.setDisable(true);
        } catch (Exception e) {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Registration");
            notification.setMessage("Failed to register..try again!");
            notification.setAnimationType(AnimationType.POPUP);
            notification.showAndDismiss(Duration.millis(2000));
        }
    }

    public void tblProgramOnMouseClicked(MouseEvent mouseEvent) {
        try {
            if (!tblProgram.getItems().isEmpty()) {
                btnAdd.setDisable(false);
            }
        } catch (NullPointerException ex) {

        }
    }

    private int isExist(String code) {
        for (int i = 0; i < tblCart.getItems().size(); i++) {
            if (code.equals(tblCart.getItems().get(i).getPid())) {
                return i;
            }
        }
        return -1;
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        btnClear.setDisable(false);
        btnRegister.setDisable(false);
        int count = isExist(tblProgram.getSelectionModel().getSelectedItem().getPid());
        if (count == -1) {
            tblCart.getItems().add(new CustomeDTO(tblProgram.getSelectionModel().getSelectedItem().getPid()));
        }
        setTblCartCellValue();
    }

    public void tblCartOnMouseClicked(MouseEvent mouseEvent) {
        try {
            if (!tblCart.getItems().isEmpty()) {
                btnRemove.setDisable(false);
            }

        } catch (NullPointerException ex) {
        }
    }

    public void cmbIdOnAction(ActionEvent actionEvent) {
        try {
            StudentDTO student = studentBO.search(cmbId.getSelectionModel().getSelectedItem().toString());
            txtId.setText(student.getSid());
            txtName.setText(student.getName());
            txtAddress.setText(student.getAddress());
        } catch (Exception ex) {
        }
    }
}
