package com.royalinstitute.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.royalinstitute.bo.BOFactory;
import com.royalinstitute.bo.custom.UserBO;
import com.royalinstitute.dto.UserDTO;
import com.royalinstitute.stage.StageList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFormController extends StageList {
    public JFXTextField txtUserName;
    public JFXPasswordField pwd;
    public TextField txtShowedPwd;
    public JFXRadioButton rbShowPassword;
    public static UserDTO user;

    UserBO userBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        txtShowedPwd.setVisible(false);
    }

    public void pwdOnKeyReleased(KeyEvent keyEvent) {
        txtShowedPwd.clear();

        /*txtShowedPwd.setMinWidth(Region.USE_PREF_SIZE);
        txtShowedPwd.setMaxWidth(Region.USE_PREF_SIZE);
        txtShowedPwd.textProperty().addListener((ov, prevText, currText) -> {
            // Do this in a Platform.runLater because of Textfield has no padding at first time and so on
            Platform.runLater(() -> {
                Text text = new Text(currText);
                text.setFont(txtShowedPwd.getFont()); // Set the same font, so the size is the same
                double width = text.getLayoutBounds().getWidth() // This big is the Text in the TextField
                        + txtShowedPwd.getPadding().getLeft() + txtShowedPwd.getPadding().getRight() // Add the padding of the TextField
                        + 2d; // Add some spacing
                txtShowedPwd.setPrefWidth(width); // Set the width
                txtShowedPwd.positionCaret(txtShowedPwd.getCaretPosition()); // If you remove this line, it flashes a little bit
            });
        });*/
        txtShowedPwd.setText(pwd.getText());
    }

    public boolean checkRegEx(String pattern, String text) {
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(text);
        return matcher.matches();
    }

    private void login() {
        try {
            user = userBO.getUserDetails(txtUserName.getText(), pwd.getText());
            txtUserName.clear();
            pwd.clear();
            txtShowedPwd.clear();
            mainMenuFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainMenuForm.fxml"))));
            mainMenuFormStage.setResizable(false);
            mainMenuFormStage.show();
            mainFormStage.close();
            txtShowedPwd.setVisible(false);
            pwd.setVisible(true);
            txtUserName.requestFocus();
            rbShowPassword.setSelected(false);

        } catch (Exception e) {
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Login");
            notification.setMessage("User Name or Password is wrong..try again!");
            notification.showAndDismiss(Duration.millis(1000));
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().length() > 0 && pwd.getText().length() > 0) {
            login();
        } else {
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Login");
            notification.setMessage("User Name or Password can not be empty!");
            notification.showAndDismiss(Duration.millis(1000));
        }
    }

    public void rbShowPassword(ActionEvent actionEvent) {
        if (rbShowPassword.isSelected()) {
            pwd.setVisible(false);
            txtShowedPwd.setPrefSize(238, 30);
            txtShowedPwd.setMaxSize(238, 30);
            txtShowedPwd.setMinSize(238, 30);
            txtShowedPwd.requestFocus();
            txtShowedPwd.setVisible(rbShowPassword.isSelected());
        } else {
            pwd.setVisible(true);
            txtShowedPwd.setVisible(false);
        }
    }

    public void txtShowedPwdOnKeyRelaesed(KeyEvent keyEvent) {
        pwd.setText(txtShowedPwd.getText());
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        if (checkRegEx("^[A-z0-9]{1,}$", txtUserName.getText())) {
            pwd.requestFocus();
            txtShowedPwd.requestFocus();
        } else {
            txtUserName.requestFocus();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Login");
            notification.setMessage("Can not add symbols for User Name!");
            notification.showAndDismiss(Duration.millis(1000));
        }
    }

    public void pwdOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().length() > 0) {
            if (checkRegEx("^[A-z0-9]{1,}$", txtUserName.getText())) {
                pwd.requestFocus();
                if (checkRegEx("^[A-z0-9]{1,}$", pwd.getText())) {
                    login();
                } else {
                    pwd.requestFocus();
                    TrayNotification notification = new TrayNotification();
                    notification.setAnimationType(AnimationType.POPUP);
                    notification.setNotificationType(NotificationType.ERROR);
                    notification.setTitle("Login");
                    notification.setMessage("Can not add symbols for Password!");
                    notification.showAndDismiss(Duration.millis(1000));
                }
            } else {
                txtUserName.requestFocus();
                TrayNotification notification = new TrayNotification();
                notification.setAnimationType(AnimationType.POPUP);
                notification.setNotificationType(NotificationType.ERROR);
                notification.setTitle("Login");
                notification.setMessage("Can not add symbols for User Name!");
                notification.showAndDismiss(Duration.millis(1000));
            }
        } else {
            txtUserName.requestFocus();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Login");
            notification.setMessage("User Name can not be empty!");
            notification.showAndDismiss(Duration.millis(1000));
        }
    }

    public void txtShowedPwdOnAction(ActionEvent actionEvent) {
        if (txtUserName.getText().length() > 0) {
            if (checkRegEx("^[A-z0-9]{1,}$", txtUserName.getText())) {
                pwd.requestFocus();
                if (checkRegEx("^[A-z0-9]{1,}$", txtShowedPwd.getText())) {
                    login();
                } else {
                    pwd.requestFocus();
                    TrayNotification notification = new TrayNotification();
                    notification.setAnimationType(AnimationType.POPUP);
                    notification.setNotificationType(NotificationType.ERROR);
                    notification.setTitle("Login");
                    notification.setMessage("Can not add symbols for Password!");
                    notification.showAndDismiss(Duration.millis(1000));
                }
            } else {
                txtUserName.requestFocus();
                TrayNotification notification = new TrayNotification();
                notification.setAnimationType(AnimationType.POPUP);
                notification.setNotificationType(NotificationType.ERROR);
                notification.setTitle("Login");
                notification.setMessage("Can not add symbols for User Name!");
                notification.showAndDismiss(Duration.millis(1000));
            }
        } else {
            txtUserName.requestFocus();
            TrayNotification notification = new TrayNotification();
            notification.setAnimationType(AnimationType.POPUP);
            notification.setNotificationType(NotificationType.ERROR);
            notification.setTitle("Login");
            notification.setMessage("User Name can not be empty!");
            notification.showAndDismiss(Duration.millis(1000));
        }
    }
}
