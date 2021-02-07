package com.royalinstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.royalinstitute.stage.StageList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageRegistrationFormController extends StageList {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXComboBox cmbId;
    public JFXButton btnRemove;
    public JFXButton btnClear;
    public JFXButton btnRegister;

    @FXML
    private TableView<?> tblProgram;

    @FXML
    private TableColumn<?, ?> clmID;

    @FXML
    private TableColumn<?, ?> clmProgram;

    @FXML
    private TableColumn<?, ?> clmDuration;

    @FXML
    private TableColumn<?, ?> clmFee;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private TableColumn<?, ?> clmSid;

    public void btnRemoveOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        mainMenuFormStage.show();
        manageRegistrationFormStage.close();
    }
}
