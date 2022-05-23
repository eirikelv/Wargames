package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.units.UnitFactory;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class createNewArmy2 implements Initializable {


    @FXML
    public TableView<Unit> tableViewArmy;
    @FXML
    public ChoiceBox<UnitType> checkBoxUnitType = new ChoiceBox<>();

    @FXML
    public TextField textFieldarmyName;
    @FXML
    public TextField textFieldUnitName;
    @FXML
    public TextField textFieldHealth;
    @FXML
    public TextField textFieldQuantity;

    @FXML
    public Text textArmyName;

    @FXML
    public Button buttonCreateArmy;

    /** Initializes the controller and sets value to checkbox and tableView
     * @param url the url represented by Url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        TableDecorator.initTable3Colum(tableViewArmy);
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
            Army csvArmy2;
            UnitType unitType = findUnitType();
            String name = textFieldUnitName.getText();
            int health = Integer.parseInt(textFieldHealth.getText());
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            csvArmy2 = armyFileHandler.getArmyFromCSVInput(Singleton.getInstance().getArmyName2());
            csvArmy2.addAll(UnitFactory.addDuplicateUnitsAsList(unitType, name, health, quantity));

            armyFileHandler.writeUnitsToCSVFile(csvArmy2);

        }catch (Exception e){
            AlertBox.alertError(e.getMessage());
        }
        tableViewArmy.refresh();
        textFieldQuantity.clear();
        textFieldUnitName.clear();
        textFieldHealth.clear();
        checkBoxUnitType.setValue(UnitType.INFANTRYUNIT);
        TableDecorator.initTable3Colum(tableViewArmy);

    }

    public void newArmyName() {
        String armyName = textFieldarmyName.getText();
        Army newArmy = new Army(armyName);
        tableViewArmy.refresh();
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();

        armyFileHandler.writeUnitsToCSVFile(newArmy);
        Singleton.getInstance().setArmy2(newArmy);
        textArmyName.setText(armyName);
        textFieldUnitName.disableProperty();
        buttonCreateArmy.setDisable(true);

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
