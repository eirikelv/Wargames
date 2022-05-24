package edu.ntnu.idatt2001.eirielv.model.simulation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
     * initTable5Colum initializes a table with 5 columns representing an army, and columns represents units
     * @param tableView is the tableView to set with 5 columns
     */
    public static void initTable5Colum(TableView<Unit> tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Unit, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(100);
        TableColumn<Unit, Integer> health = new TableColumn<>("Health");
        health.setCellValueFactory(new PropertyValueFactory<>("health"));
        health.setMinWidth(70);
        TableColumn<Unit, Integer> attack = new TableColumn<>("Attack");
        attack.setCellValueFactory(new PropertyValueFactory<>("attack"));
        attack.setMinWidth(50);
        TableColumn<Unit, Integer> armor = new TableColumn<>("Armor");
        armor.setCellValueFactory(new PropertyValueFactory<>("armor"));
        armor.setMinWidth(50);
        TableColumn<Unit, String> type = new TableColumn<>("UnitType");
        type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        type.setMinWidth(100);
        tableView.getColumns().add(type);
        tableView.getColumns().add(name);
        tableView.getColumns().add(health);
        tableView.getColumns().add(attack);
        tableView.getColumns().add(armor);
    }

    /**
     * initTable3Colum initializes a table with 3 columns representing an army, and columns represents units
     * @param tableView is the tableView to set with 3 columns
     */
    public static void initTable3Colum(TableView<Unit> tableView){
        tableView.getItems().clear();
        tableView.getColumns().clear();
        TableColumn<Unit, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setMinWidth(100);
        TableColumn<Unit, Integer> health = new TableColumn<>("Health");
        health.setCellValueFactory(new PropertyValueFactory<>("health"));
        health.setMinWidth(100);
        TableColumn<Unit, String> type = new TableColumn<>("UnitType");
        type.setCellValueFactory(new PropertyValueFactory<>("unitType"));
        type.setMinWidth(100);
        tableView.getColumns().add(type);
        tableView.getColumns().add(name);
        tableView.getColumns().add(health);
    }

    /**
     * fillTable fills all army presentation to a table view
     * @param colum number of columns in the table represented ass integer
     * @param army the army to put in the table, represented as Army
     * @param tableView the tableView to fill information in
     */
    public static void fillTable(int colum, Army army, TableView<Unit> tableView){
        if(colum == 3) {
            initTable3Colum(tableView);
            ObservableList<Unit> observablearmyList = FXCollections.observableList(new ArrayList<>());
            observablearmyList.addAll(army.getAllUnits());
            tableView.setItems(observablearmyList);
        }
        else{
            initTable5Colum(tableView);
            ObservableList<Unit> observablearmyList = FXCollections.observableList(new ArrayList<>());
            observablearmyList.addAll(army.getAllUnits());
            tableView.setItems(observablearmyList);
        }
    }

    /**
     * fillListView fills all general army information to a listview
     * @param army the army to represent in the table, represented as Army
     * @param armiesListView the listView to fill information in, represented as ListView
     * @throws IOException if there is something wrong with the file represented
     */
    public static void fillListView(Army army, ListView<String> armiesListView) throws IOException {
        armiesListView.getItems().clear();
        List<String> armyList = FXCollections.observableList(new ArrayList<>());

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
