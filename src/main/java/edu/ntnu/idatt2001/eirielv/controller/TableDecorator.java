package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TableDecorator is a decorator for Tables- and ListView. This method it used for making views and add Army information
 * to views
 * @author Eirik Elvestad
 */
public class TableDecorator {

    /**
     * This method initialize a table with 5 collums representing a army, and colums represents units
     * @param tableView
     */
    public static void initTable5Colum(TableView<Unit> tableView){
        tableView.getItems().clear();
        TableColumn<Unit, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Unit, Integer> health = new TableColumn<>("Health");
        health.setCellValueFactory(new PropertyValueFactory<>("health"));
        TableColumn<Unit, Integer> attack = new TableColumn<>("Attack");
        attack.setCellValueFactory(new PropertyValueFactory<>("attack"));
        TableColumn<Unit, Integer> armor = new TableColumn<>("Armor");
        armor.setCellValueFactory(new PropertyValueFactory<>("armor"));
        TableColumn<Unit, String> type = new TableColumn<>("UnitType");
        type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        tableView.getColumns().add(type);
        tableView.getColumns().add(name);
        tableView.getColumns().add(health);
        tableView.getColumns().add(attack);
        tableView.getColumns().add(armor);
    }

    /**
     * This method initialize a table with 3 collums representing a army, an colums represents units
     * @param tableView
     */
    public static void initTable3Colum(TableView<Unit> tableView){
        tableView.getItems().clear();
        TableColumn<Unit, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Unit, Integer> health = new TableColumn<>("Health");
        health.setCellValueFactory(new PropertyValueFactory<>("health"));
        TableColumn<Unit, String> type = new TableColumn<>("UnitType");
        type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        tableView.getColumns().add(type);
        tableView.getColumns().add(name);
        tableView.getColumns().add(health);
    }

    /**
     * fillTable fills all army presentation to a table view
     * @param colum number of colums in the table represented ass integer
     * @param army the army to put in the table, represented as Army
     * @param tableView the tableView to fill information in
     */
    public static void fillTable(int colum, Army army, TableView<Unit> tableView){

        if(colum == 3) {
            army.getAllUnits().forEach(unit -> tableView.getItems().add(unit));
            initTable3Colum(tableView);
        }
        else{
            initTable5Colum(tableView);
            army.getAllUnits().forEach(unit -> tableView.getItems().add(unit));
        }
    }

    /**
     * fillListView fills all general army information to a listview
     * @param army the army to put in the table, represented as Army
     * @param armiesListView the listView to fill information in
     * @throws IOException
     */
    public static void fillListView(Army army, ListView<String> armiesListView) throws IOException {
        armiesListView.getItems().clear();
        List<String> armyList = new ArrayList<>();

        ArmyFileHandling armyFileHandling = new ArmyFileHandling();
        String path = armyFileHandling.getFilePath(army.getName());

        armyList.add(0, army.getName());
        armyList.add(1, "Path: "+ path);
        armyList.add(2, "Total units: "+ army.getAllUnits().size());
        armyList.add(3," ");
        armyList.add(4, "CavalryUnits: "+ army.getCavalryUnits().size());
        armyList.add(5, "CommanderUnits: "+ army.getCommanderUnits().size());
        armyList.add(6, "InfantryUnits: "+ army.getInfantryUnits().size());
        armyList.add(7, "RangedUnits: "+ army.getRangedUnits().size());
        armiesListView.setItems(FXCollections.observableList(armyList));
    }

}
