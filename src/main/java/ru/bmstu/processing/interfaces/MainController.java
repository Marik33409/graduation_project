package ru.bmstu.processing.interfaces;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MainController {
    private Stage myStage;
    private Common common = new Common();

    public void setStage(Stage stage) {
        myStage = stage;
    }

    public void btnFindFile_click() {
        try {
            common.findFile(myStage);
        } catch (Exception ex) {
            common.ShowMessage(ex.getMessage());
        }

    }
}
