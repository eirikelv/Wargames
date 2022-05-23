package edu.ntnu.idatt2001.eirielv.controller;

import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class ArmiesController implements Initializable {

    @FXML
    private TableView<Unit> armiesTable1;
    @FXML
    private TableView<Unit> armiesTable2;

    @FXML
    private TextField TextFieldArmyName2;
    @FXML
    private TextField TextFieldArmyName1;

    @FXML
    private Button importArmy1;
    @FXML
    private Button importArmy2;
    @FXML
    private Button addUnits1;
    @FXML
    private Button addUnits2;

    @FXML
    private Text textArmyName1;
    @FXML
    private Text textArmyName2;

    @FXML
    private ListView<String> armiesList1;
    @FXML
    private ListView<String> armiesList2;

    /** Initialize the controller, and sets the tableview and listView for the scene
     * @param url is the url represented by Url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       TableDecorator.initTable5Colum(armiesTable1);
       TableDecorator.initTable5Colum(armiesTable2);
       if(Singleton.getInstance().getArmy1() != null && Singleton.getInstance().getArmy2() != null){
           Army army1 = Singleton.getInstance().getArmy1();
           Army army2 = Singleton.getInstance().getArmy2();
           TableDecorator.fillTable(5, army1, armiesTable1);
           try {
                TableDecorator.fillListView(army1, armiesList1);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
           TableDecorator.fillTable(5, army2, armiesTable2);
           try {
               TableDecorator.fillListView(army2, armiesList2);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
    }

    @FXML
    public void createNewArmy1(MouseEvent mouseEvent) throws IOException {
        SwitchScene.createNewStage("CreateNewArmy1");
    }

    @FXML
    public void createNewArmy2(MouseEvent mouseEvent) throws IOException {
        SwitchScene.createNewStage("CreateNewArmy2");
    }


    /**
     * addUnits1 shows the view for adding a unit
     * @param mouseEvent checks if the button "addUnit1" is bressed by the mouse
     * @throws IOException
     */
    @FXML
    public void addUnits1(MouseEvent mouseEvent) throws IOException {
        SwitchScene.createNewStage("AddUnitsWindow1");

    }

    /**
     * addUnits2 shows the view for adding a unit
     * @param mouseEvent checks if the button "addUnit2" is bressed by the mouse
     * @throws IOException
     */
    @FXML
    public void addUnits2(MouseEvent mouseEvent) throws IOException {
        SwitchScene.createNewStage("AddUnitsWindow2");
    }

    /**
     * goToFrontPage takes the user to frontPage
     * @param event checks if the button "goToFrontPage" is pressed by the mouse
     * @throws IOException
     */
    @FXML
    public void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage",event);
    }

    /**
     * goToSimulationPage saves army1 and army2 and sends the user to simulation page.
     * @param event checks if the button "Save" is bressed by the mouse
     * @throws IOException
     */
    @FXML
    private void goToSimulationPage(MouseEvent event) throws IOException {
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
     * changeArmy1 takes inn armyName from textField and changes army1 from csv file, and represents the army fetched
     * from csv, in a tableview. If the textfield TextFieldArmyName1 don't have text, an alertbox will announce an
     * alerterror, so the user knows what to do.
     * @throws IOException
     */
    public void changeArmy1() throws IOException {
        if(TextFieldArmyName1.hasProperties()){
            armiesTable1.refresh();
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy1;

            try{
                csvArmy1 = armyFileHandler.getArmyFromCSVInput(TextFieldArmyName1.getText());
            }catch (IOException e){
                AlertBox.alertError(e.getMessage());
                return;
            }
            Singleton.getInstance().setArmy1(csvArmy1);

            TableDecorator.fillTable(5, csvArmy1, armiesTable1);
            TableDecorator.fillListView(csvArmy1, armiesList1);
            textArmyName1.setText(csvArmy1.getName());


        }
        else AlertBox.alertError("You need to type in army name to search for army, the CSV should have the same name" +
                "as the army");
    }

    /**
     * changeArmy2 takes inn armyName from textField and changes army2 from csv file, and represents the army fetched
     * from csv, in a tableview. If the textfield TextFieldArmyName2 don't have text, an alertbox will announce an
     * alerterror, so the user knows what to do.
     * @throws IOException
     */
    public void changeArmy2() throws IOException {

        if(TextFieldArmyName2.hasProperties()){
            ArmyFileHandling armyFileHandler = new ArmyFileHandling();
            Army csvArmy2;

            try{
                csvArmy2 = armyFileHandler.getArmyFromCSVInput(TextFieldArmyName2.getText());
            } catch (IOException e){
                AlertBox.alertError(e.getMessage());
                return;
            }
            Singleton.getInstance().setArmy2(csvArmy2);
            TableDecorator.fillTable(5, csvArmy2, armiesTable2);
            TableDecorator.fillListView(csvArmy2, armiesList2);
            textArmyName2.setText(csvArmy2.getName());
        }
        else AlertBox.alertError("You need to type inn army name to search for army, the CSV should have the same name" +
                "as the army");
    }

    /**
     * This page imports chosen file to armyCSVfiles in resources
     * @throws IOException
     */
    @FXML
    private void importFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        System.out.println("penis");
        Files.copy(file.toPath(), new File("src/main/resources/edu/ntnu/idatt2001/eirielv/armycsvfiles").toPath());
    }


    /**
     *This method gets information from Tableview, and makes an army of the tableview. Then it passes the information
     * to Singleton to further use it in simulation
     */
    private void saveArmies(){
        Army army1;
        Army army2;
        ArmyFileHandling armyFileHandling = new ArmyFileHandling();
        try {
            army1 = armyFileHandling.getArmyFromCSVInput(TextFieldArmyName1.getText());
            army2 = armyFileHandling.getArmyFromCSVInput(TextFieldArmyName2.getText());
            ;
        } catch (Exception e) {
            AlertBox.alertError(e.getMessage() + "\n If there already exists armies, disregard this error");
            return;
        }
        Singleton.getInstance().setArmy1(army1);
        Singleton.getInstance().setArmy2(army2);
    }

    //private void createNewArmy
}
