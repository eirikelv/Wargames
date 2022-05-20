package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
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

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    public void addUnits(MouseEvent event) throws IOException {
        SwitchScene.createNewStage("AddUnitsWindow");
    }

    @FXML
    public void goToFrontPageAndSaveData(MouseEvent event) throws IOException {

        SwitchScene.switchScene("FrontPage", event);
    }

    public void changeArmy1(){
        if(TextFieldArmyName1.hasProperties()){
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy;
            List<Unit> unitList;
            csvArmy = armyFileHandler.getArmyFromCSVInput(TextFieldArmyName1.getText());
            unitList = csvArmy.getAllUnits();
            unitNameColum1.setCellValueFactory(new PropertyValueFactory<>("name"));
            unitHealthColum1.setCellValueFactory(new PropertyValueFactory<>("health"));
            unitAttackColum1.setCellValueFactory(new PropertyValueFactory<>("attack"));
            unitDefenceColum1.setCellValueFactory(new PropertyValueFactory<>("armor"));
            unitTypeColum1.setCellValueFactory(new PropertyValueFactory<>("unitType"));
            armiesTable1.setItems(FXCollections.observableList(unitList));
        }
        else AlertBox.alertError("You need to type inn army name to search for army, the CSV should have the same name" +
                "as the army");
    }

    public void changeArmy2(){
        if(TextFieldArmyName2.hasProperties()){
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy;
            List<Unit> unitList;
            csvArmy = armyFileHandler.getArmyFromCSVInput(TextFieldArmyName2.getText());
            unitList = csvArmy.getAllUnits();
            unitNameColum2.setCellValueFactory(new PropertyValueFactory<>("name"));
            unitHealthColum2.setCellValueFactory(new PropertyValueFactory<>("health"));
            unitAttackColum2.setCellValueFactory(new PropertyValueFactory<>("attack"));
            unitDefenceColum2.setCellValueFactory(new PropertyValueFactory<>("armor"));
            unitTypeColum2.setCellValueFactory(new PropertyValueFactory<>("unitType"));
            armiesTable2.setItems(FXCollections.observableList(unitList));
        }
        else AlertBox.alertError("You need to type inn army name to search for army, the CSV should have the same name" +
                "as the army");
    }

    //public void importArmyFromCSVtoTableView(TableView<Unit> tableview, String armyName){

    //    ArmyFileHandling armyFileHandler = new ArmyFileHandling();
   //     Army csvArmy;
    //    List<Unit> unitList;
   //     csvArmy = armyFileHandler.getArmyFromCSVInput(armyName);
    //    unitList = csvArmy.getAllUnits();
    //    unitNameColum.setCellValueFactory(new PropertyValueFactory<>("name"));
    //    unitHealthColum.setCellValueFactory(new PropertyValueFactory<>("health"));
    //    unitAttackColum.setCellValueFactory(new PropertyValueFactory<>("attack"));
    //    unitDefenceColum.setCellValueFactory(new PropertyValueFactory<>("defence"));
    //    unitTypeColum.setCellValueFactory(new PropertyValueFactory<>("unittype"));
    //    tableview.setItems(FXCollections.observableList(unitList));

    //}
}
