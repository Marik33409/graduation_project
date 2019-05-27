package ru.bmstu.processing.interfaces;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * User: Mark Bryzgalov
 * Time: 2019-05-08 19:14
 */

public class MainForm extends Application {
    @Override
    public void start(Stage stage) throws Exception{
            Scene scene = new Scene(new StackPane(), 800, 800);
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }

    }