package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.model.simulation.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.io.IOException;

/**
 * SimulationController is the controller for {@link SwitchScene} SimulationPage.fxml. Takes in army1 and army2
 * and simulates a battle between them
 * @author Eirik Elvestad
 */
public class SimulationController{

    @FXML
    private Button buttonRunSimulation;
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
     * initialize initializes the contstructor and imports army1 and army2 stored in {@link SimulatorSingleton}.
     * After importing the armies gets represented in TableView and ListView
     */
    public void initialize() {
        Army army1 = SimulatorSingleton.getInstance().getArmy1();
        Army army2 = SimulatorSingleton.getInstance().getArmy2();
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

    /**
     * goToFrontPage takes the user to {@link SwitchScene} FrontPage.fxml when method called by event
     * @param event calls the method when mouse clicked
     * @throws IOException if there is damaged information in the new scene
     */
    @FXML
    private void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage", event);
    }

    /**
     * goToArmiesPage takes the user to {@link SwitchScene} ArmiesPage.fxml when method called by event
     * @param event calls the method when mouse clicked
     * @throws IOException if there is damaged information in the new scene
     */
    @FXML
    public void goToArmiesPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("ArmiesPage", event);

    }

    /**
     * TerrainType takes the user input from choiceBoxTerrain, and returns the TerrainType chosen by the user
     * @return the terrainType to simulate the battle in, represented as TerrainType
     */
    private TerrainType findTerrainType(){
        int index = choiceBoxTerrain.getSelectionModel().getSelectedIndex();
        return switch (index) {
            case 0 -> TerrainType.HILL;
            case 1 -> TerrainType.FOREST;
            case 2 -> TerrainType.PLAINS;
            default -> null;
        };
    }

    /**
     * simulate simulates a battle between the two armies imported from {@link SimulatorSingleton}, and makes a object
     * of {@link Battle} with army1 and army2 as variables. Then in a Timeline, it runs battle.simulate where one
     * random unit from army1 attacks one random unit from army2, and represents the winner in an
     * {@link AlertBox} winnerBox, which is a informationBox
     */
    public void simulate() {
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();
        Army army1;
        Army army2;
        try {
            army1 = armyFileHandler.getArmyFromCSVInput(SimulatorSingleton.getInstance().getArmyName1());
            army2 = armyFileHandler.getArmyFromCSVInput(SimulatorSingleton.getInstance().getArmyName2());
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
                if(army1Representation.getItems().size() == 0) AlertBox.winnerBox(army2, setTableViewAsString(armiesList2));
                if(army2Representation.getItems().size() == 0) AlertBox.winnerBox(army1, setTableViewAsString(armiesList1));

                disableButtons(false);
            }
        }));
        timeline.playFromStart();
    }

    /**
     * disableButtons takes in a boolean that decides if buttons and choicebox on scene should be disabled or not
     * @param disbaleButton boolean that decides the state of the buttons and choicebox
     */
    public void disableButtons(Boolean disbaleButton){
        buttonReset.setDisable(disbaleButton);
        buttonRunSimulation.setDisable(disbaleButton);
        choiceBoxTerrain.setDisable(disbaleButton);
    }

    /**
     * setTableViewAsString takes in a listView and builds a string of it, to further pass the string to the winnerBox
     * @param listView the ListView to get as a string
     * @return simulation winner, represented as String
     */
    public String setTableViewAsString(ListView<String> listView) {
        return (
                listView.getItems().get(2) + "\n"
                + listView.getItems().get(4) + "\n"
                + listView.getItems().get(5) + "\n"
                + listView.getItems().get(6) + "\n"
                + listView.getItems().get(7) + "\n"
        );
    }

    /**
     * resetSimulation resets the stage in the simulation
     */
    public void resetSimulation(){
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();
        Army army1;
        Army army2;
        try {
            army1 = armyFileHandler.getArmyFromCSVInput(SimulatorSingleton.getInstance().getArmyName1());
            army2 = armyFileHandler.getArmyFromCSVInput(SimulatorSingleton.getInstance().getArmyName2());
        } catch (IOException e){
            AlertBox.alertError(e.getMessage());
            return;
        }
        try {
            TableDecorator.fillListView(army1, armiesList1);
            TableDecorator.fillListView(army2, armiesList2);
        } catch (Exception e){
            AlertBox.alertError(e.getMessage());
        }
        ObservableList<Unit> army1List = (FXCollections.observableList(army1.getAllUnits()));
        army1Representation.setItems(army1List);
        ObservableList<Unit> army2List = (FXCollections.observableList(army2.getAllUnits()));
        army2Representation.setItems(army2List);
    }
}
