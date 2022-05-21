package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.*;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private TableColumn<UnitType, UnitType> unitTypeColum1;
    @FXML
    private TableColumn<Unit, String> unitNameColum1;
    @FXML
    private TableColumn<Unit, Integer> unitHealthColum1;

    @FXML
    private TableView<Unit> army2Representation;
    @FXML
    private TableColumn<UnitType, UnitType> unitTypeColum2;
    @FXML
    private TableColumn<Unit, String> unitNameColum2;
    @FXML
    private TableColumn<Unit, Integer> unitHealthColum2;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        csvToTableView();
        choiceBoxTerrain.getItems().add(0, TerrainType.HILL);
        choiceBoxTerrain.getItems().add(1,TerrainType.FOREST);
        choiceBoxTerrain.getItems().add(2,TerrainType.PLAINS);
        choiceBoxTerrain.setValue(TerrainType.HILL);
    }



    @FXML
    public void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage", event);
    }

    public void csvToTableView(){
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();
        Army csvArmy1 = null;
        Army csvArmy2 = null;
        List<Unit> unitList1;
        List<Unit> unitList2;
        try {
            csvArmy1 = armyFileHandler.getArmyFromCSVInput(Singleton.getInstance().getArmyName1());
            csvArmy2 = armyFileHandler.getArmyFromCSVInput(Singleton.getInstance().getArmyName2());
        }catch (Exception e){
            AlertBox.alertError(e.getMessage());
        }
        unitList1 = csvArmy1.getAllUnits();
        unitList2 = csvArmy2.getAllUnits();

        unitNameColum1.setCellValueFactory(new PropertyValueFactory<>("name"));
        unitHealthColum1.setCellValueFactory(new PropertyValueFactory<>("health"));
        unitTypeColum1.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        army1Representation.setItems(FXCollections.observableList(unitList1));

        unitNameColum2.setCellValueFactory(new PropertyValueFactory<>("name"));
        unitHealthColum2.setCellValueFactory(new PropertyValueFactory<>("health"));
        unitTypeColum2.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        army2Representation.setItems(FXCollections.observableList(unitList2));
    }

    public TerrainType findTerrainType(){
        int index = choiceBoxTerrain.getSelectionModel().getSelectedIndex();
        TerrainType terrainType = null;
        switch(index){
            case 0:
                terrainType = TerrainType.HILL;
                break;
            case 1:
                terrainType = TerrainType.FOREST;
                break;
            case 2:
                terrainType = TerrainType.PLAINS;
                break;
        }
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
        int tall = 0;
        Timeline timeline = new Timeline();
        TerrainType terrainType = findTerrainType();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(4000), event -> {
            if (army1.hasUnits() && army2.hasUnits()) {
                disableButtons(true);
                battle.singleSimulate(terrainType);
                System.out.println("steg 1");
                ObservableList<Unit> army1List = (FXCollections.observableList(battle.getArmyOne().getAllUnits()));
                army1Representation.setItems(army1List);
                System.out.println("steg 2");
                ObservableList<Unit> army2List = (FXCollections.observableList(battle.getArmyTwo().getAllUnits()));
                army2Representation.setItems(army2List);
                System.out.println("steg 3");

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
