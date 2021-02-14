package com.royalinstitute.main;

import com.royalinstitute.stage.StageList;
import com.royalinstitute.util.FactoryConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;

public class Appinitialzer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            session.getTransaction().begin();
            session.getTransaction().commit();
            session.close();

            StageList.mainFormStage = primaryStage;
            primaryStage.setScene(new Scene((Parent) FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"))));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
