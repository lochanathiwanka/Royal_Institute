package com.royalinstitute.main;

import com.royalinstitute.stage.StageList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Appinitialzer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            StageList.mainFormStage = primaryStage;
            primaryStage.setScene(new Scene((Parent) FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
