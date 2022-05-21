package edu.ntnu.idatt2001.eirielv.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FrontPageController implements Initializable {



    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void goToTutorial(MouseEvent event) throws IOException {
        SwitchScene.switchScene("TutorialPage", event);
    }

    @FXML
    public void goToArmies(MouseEvent event) throws IOException {
        SwitchScene.switchScene("ArmiesPage", event);

    }

    @FXML
    public void goToSimulate(MouseEvent event) throws IOException {
        try {

            SwitchScene.switchScene("SimulationPage", event);
        } catch (Exception e){
            AlertBox.alertError("You need to add Armies. Visit the page called Armies");
        }
    }
}

