package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.units.UnitFactory;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Objects;

/**
 * AddUnitsController is a controller for {@link SwitchScene} AddUnitsWindow.fxml, and adds a new unit to an army
 * @author Eirik Elvestad
 */
public class AddUnitsController {

    @FXML
    public TableView<Unit> tableViewArmy;

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

    /**
     * Initializes the controller and sets value to checkbox and tableView and uses setArmyToEdit() to decide
     * which army from {@link Singleton} to store in TableView
     */
    @FXML
    public void initialize() {
        TableDecorator.fillTable(3, setArmyToEdit(), tableViewArmy);
        checkBoxUnitType.getItems().add(0, UnitType.INFANTRYUNIT);
        checkBoxUnitType.getItems().add(1, UnitType.CAVALRYUNIT);
        checkBoxUnitType.getItems().add(2, UnitType.COMMANDERUNIT);
        checkBoxUnitType.getItems().add(3, UnitType.RANGEDUNIT);
        checkBoxUnitType.setValue(UnitType.INFANTRYUNIT);
    }

    /**
     * addUnit gets the csv file and takes the new units from input and overwrites this csv file, with the
     * new army. All information about the army is represented in a tableView declared in the
     * {@link TableDecorator} method
     */
    public void addUnit(){
        if(!textFieldHealth.hasProperties()) throw new IllegalArgumentException("Type inn health");
        if(!textFieldUnitName.hasProperties()) throw new IllegalArgumentException("Type in unit name");
        if(!textFieldQuantity.hasProperties()) throw new IllegalArgumentException("Type in quantity of units");
        try {
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy;
            UnitType unitType = findUnitType();
            String name = textFieldUnitName.getText();
            int health = Integer.parseInt(textFieldHealth.getText());
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            csvArmy = armyFileHandler.getArmyFromCSVInput(setArmyToEdit().getName());
            csvArmy.addAll(UnitFactory.addDuplicateUnitsAsList(unitType, name, health, quantity));
            armyFileHandler.writeUnitsToCSVFile(csvArmy);
            TableDecorator.fillTable(3, csvArmy, tableViewArmy);

        }catch (Exception e){
            AlertBox.alertError(e.getMessage());
        }
        tableViewArmy.refresh();
        textFieldQuantity.clear();
        textFieldUnitName.clear();
        textFieldHealth.clear();
        checkBoxUnitType.setValue(UnitType.INFANTRYUNIT);

    }

    /**
     * findUnitType() takes inn user input from checkBoxUnitType and decides what unit type the new
     * unit is
     * @return returns the unit type for the new unit, represented as UnitType
     */
    public UnitType findUnitType(){
        int index = checkBoxUnitType.getSelectionModel().getSelectedIndex();
        return switch (index) {
            case 0 -> UnitType.INFANTRYUNIT;
            case 1 -> UnitType.CAVALRYUNIT;
            case 2 -> UnitType.COMMANDERUNIT;
            case 3 -> UnitType.RANGEDUNIT;
            default -> null;
        };
    }


    /**
     * setArmyToEdit method gets the buttonID to button stored in {@link Singleton}, which was
     * the last button pressed before changing scene, and decides what army in {@link Singleton}
     * to store the new army.
     * @return {@link Singleton} army1 or army2 represented as Army
     */
    public Army setArmyToEdit(){
        if(Objects.equals(Singleton.getInstance().getButton().getId(), "addUnits1")){
            return Singleton.getInstance().getArmy1();
        }else{
            return Singleton.getInstance().getArmy2();
        }
    }
}
