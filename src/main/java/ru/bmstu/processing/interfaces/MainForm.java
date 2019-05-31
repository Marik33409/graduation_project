package ru.bmstu.processing.interfaces;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * User: Mark Bryzgalov
 * Time: 2019-05-08 19:14
 */

public class MainForm extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //        URL url = getClass().getResource("/MainForm.fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../../../resources/MainForm.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();

        primaryStage.setTitle("AviaAnalysis");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        controller.setStage(primaryStage);

    }
    public static void main(String[] args) { launch(); }
}