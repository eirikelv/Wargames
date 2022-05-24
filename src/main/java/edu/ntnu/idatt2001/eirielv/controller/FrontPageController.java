package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.model.simulation.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

/**
 * FrontPageController is the controller for {@link SwitchScene} FrontPage
 * @author Eirik Elvestad
 */
public class FrontPageController {

    /**
     * Initializes the controller
     */
    public void initialize() {

    }

    /**
     * goToTutorial takes the user to {@link SwitchScene} TutorialPage.fxml when method called by event
     * @param event calls the method when mouse clicked
     * @throws IOException if there is damaged information in the new scene
     */
    @FXML
    public void goToTutorial(MouseEvent event) throws IOException {
        SwitchScene.switchScene("TutorialPage", event);
    }

    /**
     * goToArmies takes the user to {@link SwitchScene} ArmiesPage.fxml when method called by event
     * @param event calls the method when mouse clicked
     * @throws IOException if there is damaged information in the new scene
     */
    @FXML
    public void goToArmies(MouseEvent event) throws IOException {
        SwitchScene.switchScene("ArmiesPage", event);
    }

    /**
     * goToSimulation takes the user to simulation if there is stored armies in {@link SimulatorSingleton}, else it shows alerBox
     * @param event calls the method when mouse clicked
     * @throws IOException if there is damaged information in the new scene
     */
    @FXML
    public void goToSimulate(MouseEvent event) throws IOException {
        try {

            SwitchScene.switchScene("SimulationPage", event);
        } catch (Exception e){
            AlertBox.alertError("You need to add Armies. Visit the page called Armies");
        }
    }
}

