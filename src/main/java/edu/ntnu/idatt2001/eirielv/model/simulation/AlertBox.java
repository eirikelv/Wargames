package edu.ntnu.idatt2001.eirielv.model.simulation;

import javafx.scene.control.Alert;

/**
 * AlertBox makes alerterror and informationbox for representing winner of simulation
 * @author Eirik Elvestad
 */
public class AlertBox {

    /**
     * alertError method makes a general error alert box which takes in and display message
     * @param message the display message to the alert box
     */
    public static void alertError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }

    /**
     * winnerBox method represents the winner in the simulation
     * @param army is the winning army
     * @param message is the representation of the army
     */
    public static void winnerBox(Army army, String message){
        Alert winnerInformation = new Alert(Alert.AlertType.INFORMATION);
        winnerInformation.setTitle("WINNER");
        winnerInformation.setHeaderText(army.getName());
        winnerInformation.setContentText(message);
        winnerInformation.show();
    }

}
