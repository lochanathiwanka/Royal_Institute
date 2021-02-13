package com.royalinstitute.controller;

import animatefx.animation.SlideInRight;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.royalinstitute.bo.BOFactory;
import com.royalinstitute.bo.custom.UserBO;
import com.royalinstitute.dto.UserDTO;
import com.royalinstitute.stage.StageList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;

public class SettingsFormController extends StageList {
    public AnchorPane titlePane;
    public TextField txtID;
    public TextField txtName;
    public TextField txtUserName;
    public JFXButton btnUpdate;
    public TextField txtPassword;

    @FXML
    private TableView<UserDTO> tblUser;

    @FXML
    private TableColumn<UserDTO, String> clmId;

    @FXML
    private TableColumn<UserDTO, String> clmName;

    @FXML
    private TableColumn<UserDTO, String> clmUserName;

    @FXML
    private TableColumn<UserDTO, String> clmPassword;

    UserBO userBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        addUserDetailsToTblUser();
        new ZoomIn(titlePane).setSpeed(0.4).play();
    }

    private void addUserDetailsToTblUser(){
        ObservableList<UserDTO> list = FXCollections.observableArrayList();
        list.add(MainFormController.user);
        tblUser.setItems(list);
        setTblUserCellValue();

        txtID.setText(MainFormController.user.getId());
        txtName.setText(MainFormController.user.getName());
        txtUserName.setText(MainFormController.user.getUserName());
        txtPassword.setText(MainFormController.user.getPassword());
    }

    private void setTblUserCellValue() {
        clmId.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("id"));
        clmName.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("name"));
        clmUserName.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("userName"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<UserDTO, String>("password"));
    }

    public void tblUserOnMouseClicked(MouseEvent mouseEvent) {
        try {
            txtID.setText(tblUser.getSelectionModel().getSelectedItem().getId());
            txtName.setText(tblUser.getSelectionModel().getSelectedItem().getName());
            txtUserName.setText(tblUser.getSelectionModel().getSelectedItem().getUserName());
            txtPassword.setText(tblUser.getSelectionModel().getSelectedItem().getPassword());
        } catch (NullPointerException ex) {

        }
    }

    private void getUserDetails(String userName, String password) {
        try {
            UserDTO user = userBO.getUserDetails(userName, password);
            tblUser.setItems(FXCollections.observableArrayList(user));
            setTblUserCellValue();
        } catch (Exception e) {

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            userBO.update(new UserDTO(txtID.getText(), txtName.getText(), txtUserName.getText(), txtPassword.getText()));
            getUserDetails(txtUserName.getText(), txtPassword.getText());
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("User Detail");
            notification.setMessage("successfully updated!");
            notification.showAndDismiss(Duration.millis(1000));
        } catch (Exception e) {
            TrayNotification notification = new TrayNotification();
            notification.setNotificationType(NotificationType.ERROR);
            notification.setAnimationType(AnimationType.POPUP);
            notification.setTitle("User Detail");
            notification.setMessage("Error!");
            notification.showAndDismiss(Duration.millis(1000));
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        mainMenuFormStage.show();
        settingsFormStage.close();
    }
}
