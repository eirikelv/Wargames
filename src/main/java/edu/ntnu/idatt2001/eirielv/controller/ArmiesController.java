package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArmiesController implements Initializable {

    @FXML
    private TableView<Unit> armiesTable1;
    @FXML
    private TableColumn<UnitType, UnitType> unitTypeColum1;
    @FXML
    private TableColumn<Unit, String> unitNameColum1;
    @FXML
    private TableColumn<Unit, Integer> unitHealthColum1;
    @FXML
    private TableColumn<Unit, Integer> unitAttackColum1;
    @FXML
    private TableColumn<Unit, Integer> unitDefenceColum1;

    @FXML
    private TableView<Unit> armiesTable2;
    @FXML
    private TableColumn<UnitType, UnitType> unitTypeColum2;
    @FXML
    private TableColumn<Unit, String> unitNameColum2;
    @FXML
    private TableColumn<Unit, Integer> unitHealthColum2;
    @FXML
    private TableColumn<Unit, Integer> unitAttackColum2;
    @FXML
    private TableColumn<Unit, Integer> unitDefenceColum2;

    @FXML
    private Button importArmy1;
    @FXML
    private TextField TextFieldArmyName1;

    @FXML
    private Button importArmy2;
    @FXML
    private TextField TextFieldArmyName2;

    @FXML
    private Text textArmyName1;

    @FXML
    private Text textArmyName2;

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
        checkForSavedArmies();
    }

    @FXML
    public void addUnits(MouseEvent event) throws IOException {
        SwitchScene.createNewStage("AddUnitsWindow");
    }

    @FXML
    public void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage",event);
    }

    @FXML
    public void goToSimulationPageAndSaveData(MouseEvent event) throws IOException {
        if(armiesTable1.getItems().size() == 0 && armiesTable2.getItems().size() == 0){
            AlertBox.alertError("You need to add two armies");
        }
        else if(armiesTable1.getItems().size() == 0){
            AlertBox.alertError("You need to add armyOne, left side");
        }
        else if(armiesTable2.getItems().size() == 0){
            AlertBox.alertError("You need to add armyTwo, right side");
        }
        else {
            try {
                saveArmies();
                SwitchScene.switchScene("SimulationPage", event);
            } catch (Exception e) {
                AlertBox.alertError(e.getMessage());
            }
        }

    }

    /**
     * This method changes army1 from csv file, and represents the army fetched from csv, in a tableview. If the text
     * field TextFieldArmyName1 don't have text, an alertbox will announce an alerterror, so the user knows what to do.
     */
    public void changeArmy1(){
        if(TextFieldArmyName1.hasProperties()){
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy1;
            List<String> army1Information = new ArrayList<>();
            List<Unit> unitList1;
            try{
                csvArmy1 = armyFileHandler.getArmyFromCSVInput(TextFieldArmyName1.getText());
            } catch (IOException e){
                AlertBox.alertError(e.getMessage());
                return;
            }
            army1Information.add(0, csvArmy1.getName());
            army1Information.add(1, "CavalryUnits: "+ csvArmy1.getCavalryUnits().size());
            army1Information.add(2, "CommanderUnits: "+ csvArmy1.getCommanderUnits().size());
            army1Information.add(3, "InfantryUnits: "+ csvArmy1.getInfantryUnits().size());
            army1Information.add(4, "RangedUnits: "+ csvArmy1.getRangedUnits().size());
            armiesList1.setItems(FXCollections.observableList(army1Information));

            unitList1 = csvArmy1.getAllUnits();
            unitNameColum1.setCellValueFactory(new PropertyValueFactory<>("name"));
            unitHealthColum1.setCellValueFactory(new PropertyValueFactory<>("health"));
            unitAttackColum1.setCellValueFactory(new PropertyValueFactory<>("attack"));
            unitDefenceColum1.setCellValueFactory(new PropertyValueFactory<>("armor"));
            unitTypeColum1.setCellValueFactory(new PropertyValueFactory<>("unitType"));
            armiesTable1.setItems(FXCollections.observableList(unitList1));
            textArmyName1.setText(csvArmy1.getName());


        }
        else AlertBox.alertError("You need to type in army name to search for army, the CSV should have the same name" +
                "as the army");
    }

    /**
     * This method changes army2 from csv file, and represents the army fetched from csv, in a tableview. If the text
     *      * field TextFieldArmyName1 don't have text, an alertbox will announce an alerterror, so the user knows what to do.
     */
    public void changeArmy2(){
        if(TextFieldArmyName2.hasProperties()){
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy2;
            List<String> army2Information = new ArrayList<>();
            List<Unit> unitList2;
            try{
                csvArmy2 = armyFileHandler.getArmyFromCSVInput(TextFieldArmyName2.getText());
            } catch (IOException e){
                AlertBox.alertError(e.getMessage());
                return;
            }
            army2Information.add(0, csvArmy2.getName());
            army2Information.add(1, "CavalryUnits: "+ csvArmy2.getCavalryUnits().size());
            army2Information.add(2, "CommanderUnits: "+ csvArmy2.getCommanderUnits().size());
            army2Information.add(3, "InfantryUnits: "+ csvArmy2.getInfantryUnits().size());
            army2Information.add(4, "RangedUnits: "+ csvArmy2.getRangedUnits().size());
            armiesList2.setItems(FXCollections.observableList(army2Information));

            unitList2 = csvArmy2.getAllUnits();
            unitNameColum2.setCellValueFactory(new PropertyValueFactory<>("name"));
            unitHealthColum2.setCellValueFactory(new PropertyValueFactory<>("health"));
            unitAttackColum2.setCellValueFactory(new PropertyValueFactory<>("attack"));
            unitDefenceColum2.setCellValueFactory(new PropertyValueFactory<>("armor"));
            unitTypeColum2.setCellValueFactory(new PropertyValueFactory<>("unitType"));
            armiesTable2.setItems(FXCollections.observableList(unitList2));
            textArmyName2.setText(csvArmy2.getName());
        }
        else AlertBox.alertError("You need to type inn army name to search for army, the CSV should have the same name" +
                "as the army");
    }

    /**
     *This method gets information from Tableview, and makes an army of the tableview. Then it passes the information
     * to Singleton to further use it in simulation
     */
    public void saveArmies(){
        List<Unit> army1List;
        List<Unit> army2List;
        Army army1;
        Army army2;
        try {
            army1 = new Army(TextFieldArmyName1.getText());
            army2 = new Army(TextFieldArmyName2.getText());
        }catch(Exception e){
            AlertBox.alertError(e.getMessage());
            return;
        }
        army1List = this.armiesTable1.getItems();
        army2List = this.armiesTable2.getItems();
        army1.addAll(army1List);
        army2.addAll(army2List);
        Singleton.getInstance().setArmyName1(army1.getName());
        Singleton.getInstance().setArmyName2(army2.getName());
    }

    public void checkForSavedArmies(){
        if(Singleton.getInstance().getArmyName1() != null) {
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
            unitAttackColum1.setCellValueFactory(new PropertyValueFactory<>("attack"));
            unitDefenceColum1.setCellValueFactory(new PropertyValueFactory<>("armor"));
            unitTypeColum1.setCellValueFactory(new PropertyValueFactory<>("unitType"));
            armiesTable1.setItems(FXCollections.observableList(unitList1));
            textArmyName1.setText(csvArmy1.getName());

            unitNameColum2.setCellValueFactory(new PropertyValueFactory<>("name"));
            unitHealthColum2.setCellValueFactory(new PropertyValueFactory<>("health"));
            unitAttackColum2.setCellValueFactory(new PropertyValueFactory<>("attack"));
            unitDefenceColum2.setCellValueFactory(new PropertyValueFactory<>("armor"));
            unitTypeColum2.setCellValueFactory(new PropertyValueFactory<>("unitType"));
            armiesTable2.setItems(FXCollections.observableList(unitList2));
            textArmyName2.setText(csvArmy2.getName());
        }
    }
}
