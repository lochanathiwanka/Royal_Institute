package com.royalinstitute.controller;

import animatefx.animation.SlideInRight;
import com.royalinstitute.stage.StageList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Optional;

public class MainMenuFormController extends StageList {

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ManageStudentsForm.fxml"));
        manageStudentsFormStage.setScene(new Scene(pane));
        manageStudentsFormStage.setResizable(false);
        manageStudentsFormStage.show();
        new SlideInRight(pane).setSpeed(0.8).play();
        mainMenuFormStage.close();
    }

    public void btnManageProgramsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ManageProgramsForm.fxml"));
        manageProgramsFormStage.setScene(new Scene(pane));
        manageProgramsFormStage.setResizable(false);
        manageProgramsFormStage.show();
        new SlideInRight(pane).setSpeed(0.8).play();
        mainMenuFormStage.close();
    }

    public void btnRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        manageRegistrationFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageRegistrationForm.fxml"))));
        manageRegistrationFormStage.setResizable(false);
        manageRegistrationFormStage.show();
        mainMenuFormStage.close();
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/SettingsForm.fxml"));
        settingsFormStage.setScene(new Scene(pane));
        settingsFormStage.setResizable(false);
        settingsFormStage.show();
        new SlideInRight(pane).setSpeed(0.8).play();
        mainMenuFormStage.close();
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to go back ?", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            mainFormStage.show();
            mainMenuFormStage.close();
        }else if (result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }
}
