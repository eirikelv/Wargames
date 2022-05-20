package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {
    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



    @FXML
    public void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage", event);
    }

    public void csvToTableView(String armyName1, String armyName2){


    }

    public void simulate(String armyName1, String armyName2){
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();
        Army army1 = armyFileHandler.getArmyFromCSVInput(armyName1);
        Army army2 = armyFileHandler.getArmyFromCSVInput(armyName2);
        Battle battle = new Battle(army1,army2);
    }

}
