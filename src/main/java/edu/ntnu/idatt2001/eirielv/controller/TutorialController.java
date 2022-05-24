package edu.ntnu.idatt2001.eirielv.controller;


import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

/**
 * TutorialController is the controller to {@link SwitchScene} TutorialPage.fxml and shows the tutorial
 * to the program
 * @author Eirik Elvestad
 */
public class TutorialController {

    /**
     *initializes the controller
     */
    public void initialize() {

    }

    /**
     * goToFrontPage takes the user to {@link SwitchScene} FrontPage.fxml
     * @param event checks if the button "goToFrontPage" is pressed by the mouse
     * @throws IOException if the new scene has invalid information
     */
    @FXML
    public void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage",event);
    }

}
