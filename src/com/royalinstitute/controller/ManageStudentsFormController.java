package com.royalinstitute.controller;

import com.jfoenix.controls.JFXButton;
import com.royalinstitute.stage.StageList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ManageStudentsFormController extends StageList {
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnAdd;

    @FXML
    private TableView<?> tblStudents;

    @FXML
    private TableColumn<?, ?> clmID;

    @FXML
    private TableColumn<?, ?> clmName;

    @FXML
    private TableColumn<?, ?> clmAddress;


    public void txtFindOnActionOnKeyReleased(KeyEvent keyEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        mainMenuFormStage.show();
        manageStudentsFormStage.close();
    }
}
