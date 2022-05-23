package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.units.UnitFactory;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddUnitsController1 implements Initializable {

    @FXML
    public TableView<Unit> tableViewArmy;
    @FXML
    public TableColumn<Unit, UnitType> columUnitType;
    @FXML
    public TableColumn<Unit, String> columName;
    @FXML
    public TableColumn<Unit, Integer> columHealth;
    @FXML
    public ChoiceBox<UnitType> checkBoxUnitType = new ChoiceBox<>();
    @FXML
    public TextField textFieldUnitName;
    @FXML
    public TextField textFieldHealth;
    @FXML
    public TextField textFieldQuantity;
    @FXML
    public Button buttonAddUnit;

    /** Initializes the controller and sets value to checkbox and tableView
     * @param url the url represented by Url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Army army1 = Singleton.getInstance().getArmy1();
        TableDecorator.fillTable(3, army1, tableViewArmy);
        checkBoxUnitType.getItems().add(0, UnitType.INFANTRYUNIT);
        checkBoxUnitType.getItems().add(1, UnitType.CAVALRYUNIT);
        checkBoxUnitType.getItems().add(2, UnitType.COMMANDERUNIT);
        checkBoxUnitType.getItems().add(3, UnitType.RANGEDUNIT);
        checkBoxUnitType.setValue(UnitType.INFANTRYUNIT);
    }


    public void addUnit(){
        if(!textFieldHealth.hasProperties()) throw new IllegalArgumentException("Type inn health");
        if(!textFieldUnitName.hasProperties()) throw new IllegalArgumentException("Type in unit name");
        if(!textFieldQuantity.hasProperties()) throw new IllegalArgumentException("Type in quantity of units");
        try {
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy1;
            UnitType unitType = findUnitType();
            String name = textFieldUnitName.getText();
            int health = Integer.parseInt(textFieldHealth.getText());
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            csvArmy1 = armyFileHandler.getArmyFromCSVInput(Singleton.getInstance().getArmyName1());
            csvArmy1.addAll(UnitFactory.addDuplicateUnitsAsList(unitType, name, health, quantity));

            armyFileHandler.writeUnitsToCSVFile(csvArmy1);

        }catch (Exception e){
            AlertBox.alertError(e.getMessage());
        }
        tableViewArmy.refresh();
        textFieldQuantity.clear();
        textFieldUnitName.clear();
        textFieldHealth.clear();
        checkBoxUnitType.setValue(UnitType.INFANTRYUNIT);

    }

    public UnitType findUnitType(){
        int index = checkBoxUnitType.getSelectionModel().getSelectedIndex();
        UnitType unitType = null;
        switch(index){
            case 0:
                unitType = UnitType.INFANTRYUNIT;
                break;
            case 1:
                unitType = UnitType.CAVALRYUNIT;
                break;
            case 2:
                unitType = UnitType.COMMANDERUNIT;
                break;
            case 3:
                unitType = UnitType.RANGEDUNIT;
        }
        return unitType;
    }
}
