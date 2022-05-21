package edu.ntnu.idatt2001.eirielv.controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class AlertBox {

    /**
     * The method provides a general error alert box which can be specified with the display message
     * @param message the display message to the alert box
     */
    public static void alertError(String message) {
        Alert dateAlert = new Alert(Alert.AlertType.ERROR);
        dateAlert.setTitle("Error");
        dateAlert.setContentText(message);
        dateAlert.showAndWait();
    }

    /**
     * This method provides the user with helpful information during submission or creation
     * @param message popup message
     */
    public static void confirmBox(String message){
        Alert dateAlert = new Alert(Alert.AlertType.CONFIRMATION);
        dateAlert.setTitle("Confirmation");
        dateAlert.setContentText(message);
        dateAlert.showAndWait();
    }

}
