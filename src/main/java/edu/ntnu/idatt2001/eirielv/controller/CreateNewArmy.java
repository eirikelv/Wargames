package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import edu.ntnu.idatt2001.eirielv.units.UnitFactory;
import edu.ntnu.idatt2001.eirielv.units.UnitType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import java.util.Objects;

/**
 * CreateNewArmy is a controller for {@link SwitchScene} CreateNewArmy.fxml. This class creates a new army with
 * new units based on user input
 * @author Eirik Elvestad
 */
public class CreateNewArmy {

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

    /**
     * Initializes the controller, sets a Tableview and sets value to checkbox and tableView
     */
    @FXML
    public void initialize() {
        TableDecorator.initTable3Colum(tableViewArmy);
        checkBoxUnitType.getItems().add(0, UnitType.INFANTRYUNIT);
        checkBoxUnitType.getItems().add(1, UnitType.CAVALRYUNIT);
        checkBoxUnitType.getItems().add(2, UnitType.COMMANDERUNIT);
        checkBoxUnitType.getItems().add(3, UnitType.RANGEDUNIT);
        checkBoxUnitType.setValue(UnitType.INFANTRYUNIT);
    }

    /**
     * addUnit method takes in information from user and adds a unit to the new army and changes the csv file to the
     * army, so army and csv has the same information. After adding a unit, the method updates the tableView
     */
    public void addUnit() {

        if (!textFieldHealth.hasProperties()) throw new IllegalArgumentException("Type inn health");
        if (!textFieldUnitName.hasProperties()) throw new IllegalArgumentException("Type in unit name");
        if (!textFieldQuantity.hasProperties()) throw new IllegalArgumentException("Type in quantity of units");
        Army csvArmy;
        try {
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            UnitType unitType = findUnitType();
            String name = textFieldUnitName.getText();
            int health = Integer.parseInt(textFieldHealth.getText());
            int quantity = Integer.parseInt(textFieldQuantity.getText());

            csvArmy = armyFileHandler.getArmyFromCSVInput(setArmyToEdit().getName());
            csvArmy.addAll(UnitFactory.addDuplicateUnitsAsList(unitType, name, health, quantity));

            armyFileHandler.writeUnitsToCSVFile(csvArmy);
            TableDecorator.fillTable(3, csvArmy, tableViewArmy);

        } catch (Exception e) {
            AlertBox.alertError(e.getMessage());
        }
        textFieldQuantity.clear();
        textFieldUnitName.clear();
        textFieldHealth.clear();
        checkBoxUnitType.setValue(UnitType.INFANTRYUNIT);

    }

    /**
     * newArmyName method creates the new army and writes it to a csv file. It decides which {@link Singleton}
     * army to change by looking for the button which was pressed in {@link ArmiesController}.
     */
    public void newArmyName() {
        String newArmyName = textFieldarmyName.getText();
        Army newArmy = new Army(newArmyName);
        tableViewArmy.refresh();
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();
        armyFileHandler.writeUnitsToCSVFile(newArmy);
        if(Objects.equals(Singleton.getInstance().getButton().getId(), "newArmy1")){
            Singleton.getInstance().setArmy1(newArmy);
        }
        else{
            Singleton.getInstance().setArmy2(newArmy);
        }
        textArmyName.setText(setArmyToEdit().getName());
        textFieldUnitName.disableProperty();
        buttonCreateArmy.setDisable(true);

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
     * the last button pressed before changing scene, and decides what army in{@link Singleton}
     * to store the new army.
     * @return {@link Singleton} army1 or army2 represented as Army
     */
    public Army setArmyToEdit(){
        if(Objects.equals(Singleton.getInstance().getButton().getId(), "newArmy1")){
            return Singleton.getInstance().getArmy1();
        }else{
            return Singleton.getInstance().getArmy2();
        }
    }
}
