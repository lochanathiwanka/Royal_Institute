package com.royalinstitute.controller;

import com.royalinstitute.stage.StageList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MainMenuFormController extends StageList {

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        manageStudentsFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageStudentsForm.fxml"))));
        manageStudentsFormStage.show();
        mainMenuFormStage.close();
    }

    public void btnManageProgramsOnAction(ActionEvent actionEvent) throws IOException {
        manageProgramsFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageProgramsForm.fxml"))));
        manageProgramsFormStage.show();
        mainMenuFormStage.close();
    }

    public void btnRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        manageRegistrationFormStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/ManageRegistrationForm.fxml"))));
        manageRegistrationFormStage.show();
        mainMenuFormStage.close();
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        mainFormStage.show();
        mainMenuFormStage.close();
    }
}
