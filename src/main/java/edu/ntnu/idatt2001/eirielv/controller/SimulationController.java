package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.*;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {

    @FXML
    private Button buttonRunSimulation;
    @FXML
    private Button buttonRunAnimation;
    @FXML
    private Button buttonReset;
    @FXML
    private ChoiceBox<TerrainType> choiceBoxTerrain;

    @FXML
    private TableView<Unit> army1Representation;

    @FXML
    private TableView<Unit> army2Representation;

    @FXML
    private ListView<String> armiesList1;

    @FXML
    private ListView<String> armiesList2;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Army army1 = Singleton.getInstance().getArmy1();
        Army army2 = Singleton.getInstance().getArmy2();
        TableDecorator.fillTable(3, army1, army1Representation);
        TableDecorator.fillTable(3, army2, army2Representation);
        try {
            TableDecorator.fillListView(army1, armiesList1);
            TableDecorator.fillListView(army2, armiesList2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        choiceBoxTerrain.getItems().add(0, TerrainType.HILL);
        choiceBoxTerrain.getItems().add(1,TerrainType.FOREST);
        choiceBoxTerrain.getItems().add(2,TerrainType.PLAINS);
        choiceBoxTerrain.setValue(TerrainType.HILL);
    }


    @FXML
    private void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage", event);
    }

    private TerrainType findTerrainType(){
        int index = choiceBoxTerrain.getSelectionModel().getSelectedIndex();
        TerrainType terrainType = switch (index) {
            case 0 -> TerrainType.HILL;
            case 1 -> TerrainType.FOREST;
            case 2 -> TerrainType.PLAINS;
            default -> null;
        };
        return terrainType;
    }

    public void simulate() {
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();
        Army army1;
        Army army2;
        try {
            army1 = armyFileHandler.getArmyFromCSVInput(Singleton.getInstance().getArmyName1());
            army2 = armyFileHandler.getArmyFromCSVInput(Singleton.getInstance().getArmyName2());
        } catch (IOException e){
            AlertBox.alertError(e.getMessage());
            return;
        }
        Battle battle = new Battle(army1,army2);
        Timeline timeline = new Timeline();
        TerrainType terrainType = findTerrainType();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(10), event -> {
            if (army1.hasUnits() && army2.hasUnits()) {
                disableButtons(true);
                battle.singleSimulate(terrainType);
                ObservableList<Unit> army1List = (FXCollections.observableList(battle.getArmyOne().getAllUnits()));
                army1Representation.setItems(army1List);
                ObservableList<Unit> army2List = (FXCollections.observableList(battle.getArmyTwo().getAllUnits()));
                army2Representation.setItems(army2List);
                army1Representation.refresh();
                army2Representation.refresh();
                try {
                    TableDecorator.fillListView(army1, armiesList1);
                    TableDecorator.fillListView(army2, armiesList2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
            else{
                timeline.stop();
                disableButtons(false);
            }
        }));
        timeline.playFromStart();
    }

    public void disableButtons(Boolean disbaleButton){
        buttonReset.setDisable(disbaleButton);
        buttonRunAnimation.setDisable(disbaleButton);
        buttonRunSimulation.setDisable(disbaleButton);
        choiceBoxTerrain.setDisable(disbaleButton);
    }
}
