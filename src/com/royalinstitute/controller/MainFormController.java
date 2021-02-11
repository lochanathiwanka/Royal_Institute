package com.royalinstitute.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.royalinstitute.stage.StageList;
import com.royalinstitute.util.FactoryConfiguration;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import org.hibernate.Session;

import java.io.IOException;

public class MainFormController extends StageList {
    public JFXTextField txtUserName;
    public JFXPasswordField pwd;
    public TextField txtShowedPwd;
    public JFXRadioButton rbShowPassword;

    public void initialize () {
        txtShowedPwd.setVisible(false);
    }

    public void pwdOnKeyReleased(KeyEvent keyEvent) {
        txtShowedPwd.clear();

        txtShowedPwd.setMinWidth(Region.USE_PREF_SIZE);
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
        });
        txtShowedPwd.setText(pwd.getText());
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        mainMenuFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/MainMenuForm.fxml"))));
        mainMenuFormStage.show();
        mainFormStage.close();
    }

    public void rbShowPassword(ActionEvent actionEvent) {
        if (rbShowPassword.isSelected()) {
            pwd.setVisible(false);
            txtShowedPwd.setPrefSize(238, 26);
            txtShowedPwd.setMaxSize(238, 26);
            txtShowedPwd.setMinSize(238, 26);
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
}
