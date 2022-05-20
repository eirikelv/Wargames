package edu.ntnu.idatt2001.eirielv.controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SwitchScene {

    public static void switchScene(String location, Event event) throws IOException {
        Parent viewPage = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource("/edu/ntnu/idatt2001/eirielv/view/" + location + ".fxml")));

        Scene page = new Scene(viewPage, 1300, 866);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(page);
        window.setResizable(false);
        window.show();
    }
    public static void createNewStage(String location) throws IOException {
        Parent viewPage = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource("/edu/ntnu/idatt2001/eirielv/view/" + location + ".fxml")));

        Scene page = new Scene(viewPage, 650, 433);
        Stage window = new Stage();
        window.setScene(page);
        window.setResizable(false);
        window.show();
    }
}
