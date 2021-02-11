package com.royalinstitute.controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.royalinstitute.bo.BOFactory;
import com.royalinstitute.bo.custom.ProgramBO;
import com.royalinstitute.bo.custom.RegistrationDetailBO;
import com.royalinstitute.bo.custom.StudentBO;
import com.royalinstitute.dto.CustomeDTO;
import com.royalinstitute.dto.ProgramDTO;
import com.royalinstitute.dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class ViewRegistrationsFormController {
    public AnchorPane programPane;
    public AnchorPane studentPane;
    public AnchorPane titlePane;
    public TextField txtName;
    public TextField txtAddress;
    public JFXComboBox cmbSid;
    public TextField txtSid;
    public TextField txtPid;
    public TextField txtProgram;
    public TextField txtDuration;
    public TextField txtFee;
    public JFXComboBox cmbPid;
    public Label lblSid;
    public Label lblName;
    public Label lblAddress;
    public Label lblPid;
    public Label lblProgram;
    public Label lblDuration;
    public Label lblFee;
    public JFXTextField txtStudents;
    public JFXTextField txtPrograms;

    @FXML
    private TableView<CustomeDTO> tblStudentAndRegistration;

    @FXML
    private TableColumn<CustomeDTO, String> clmSid;

    @FXML
    private TableColumn<CustomeDTO, String> clmRegId2;

    @FXML
    private TableColumn<CustomeDTO, String> clmName;

    @FXML
    private TableColumn<CustomeDTO, String> clmAddress;

    @FXML
    private TableView<CustomeDTO> tblProgramAndRegistration;

    @FXML
    private TableColumn<CustomeDTO, String> clmRegId1;

    @FXML
    private TableColumn<CustomeDTO, String> clmDate;

    @FXML
    private TableColumn<CustomeDTO, String> clmProgram;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.STUDENT);
    ProgramBO programBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);
    RegistrationDetailBO registrationDetailBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.REGISTRATIONDETAIL);

    public void initialize() {
        setProgramPane(true);
        new ZoomIn(titlePane).setSpeed(0.4).play();
        addValuesToCmbStudent();
        addValuesToCmbProgram();
    }

    private void addValuesToCmbStudent() {
        try {
            cmbSid.getItems().clear();
            List<StudentDTO> list = studentBO.getAll();
            for (StudentDTO studentDTO : list) {
                cmbSid.getItems().add(studentDTO.getSid());
            }
        } catch (Exception e) {
        }
    }

    private void addValuesToCmbProgram() {
        try {
            cmbPid.getItems().clear();
            cmbPid.getItems().add("All");
            List<ProgramDTO> list = programBO.getAll();
            for (ProgramDTO programDTO : list) {
                cmbPid.getItems().add(programDTO.getPid());
            }
        } catch (Exception e) {
        }
    }

    private void setStudentPane(boolean value) {
        txtStudents.setDisable(value);
        cmbSid.setDisable(value);
        lblSid.setDisable(value);
        txtSid.setDisable(value);
        lblName.setDisable(value);
        txtName.setDisable(value);
        lblAddress.setDisable(value);
        txtAddress.setDisable(value);
        tblProgramAndRegistration.setDisable(value);
    }

    private void setProgramPane(boolean value) {
        txtPrograms.setDisable(value);
        cmbPid.setDisable(value);
        lblPid.setDisable(value);
        txtPid.setDisable(value);
        lblProgram.setDisable(value);
        txtProgram.setDisable(value);
        lblDuration.setDisable(value);
        txtDuration.setDisable(value);
        lblFee.setDisable(value);
        txtFee.setDisable(value);
        tblStudentAndRegistration.setDisable(value);
    }

    public void programPaneOnMouseEntered(MouseEvent mouseEvent) {
        setProgramPane(false);
        setStudentPane(true);
        cmbSid.getSelectionModel().clearSelection();
        txtSid.clear();
        txtName.clear();
        txtAddress.clear();
        tblProgramAndRegistration.getItems().clear();
    }

    public void studentPaneOnMouseEntered(MouseEvent mouseEvent) {
        setProgramPane(true);
        setStudentPane(false);
        cmbPid.getSelectionModel().clearSelection();
        txtPid.clear();
        txtProgram.clear();
        txtDuration.clear();
        txtFee.clear();
        tblStudentAndRegistration.getItems().clear();
    }

    public void tblStudentAndRegistrationOnMouseClicked(MouseEvent mouseEvent) {
    }

    public void tblProgramAndRegistrationOnAction(MouseEvent mouseEvent) {
    }

    public void cmbSidOnAction(ActionEvent actionEvent) {
        try {
            StudentDTO student = studentBO.search(cmbSid.getSelectionModel().getSelectedItem().toString());
            txtSid.setText(student.getSid());
            txtName.setText(student.getName());
            txtAddress.setText(student.getAddress());

            List<CustomeDTO> list = registrationDetailBO.getRegDetailsBySid(student.getSid());
            ObservableList<CustomeDTO> rows = FXCollections.observableArrayList();
            rows.addAll(list);
            tblProgramAndRegistration.setItems(rows);
            setTblProgramAndRegistrationCellValue();
        } catch (Exception ex) {
        }
    }

    private void setTblProgramAndRegistrationCellValue() {
        clmRegId1.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("regId"));
        clmDate.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("date"));
        clmProgram.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("program"));
    }

    private void setTblStudentAndRegistrationCellValue() {
        clmSid.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("sid"));
        clmRegId2.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("regId"));
        clmName.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("name"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("address"));
    }

    public void cmbPidOnAction(ActionEvent actionEvent) {
        try {
            ProgramDTO program = programBO.search(cmbPid.getSelectionModel().getSelectedItem().toString());
            txtPid.setText(program.getPid());
            txtProgram.setText(program.getProgram());
            txtDuration.setText(program.getDuration());
            txtFee.setText(program.getFee()+"");


            List<CustomeDTO> list = registrationDetailBO.getRegDetailsByPid(program.getPid());
            ObservableList<CustomeDTO> rows = FXCollections.observableArrayList();
            rows.addAll(list);
            tblStudentAndRegistration.setItems(rows);
            setTblStudentAndRegistrationCellValue();
        } catch (Exception ex) {
        }
    }
}
