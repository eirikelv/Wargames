package edu.ntnu.idatt2001.eirielv.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import edu.ntnu.idatt2001.eirielv.simulation.Army;
import edu.ntnu.idatt2001.eirielv.simulation.ArmyFileHandling;
import edu.ntnu.idatt2001.eirielv.simulation.Unit;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * ArmiesController is a controller for {@link SwitchScene} ArmiesPage.fxml. This class interactions with armies
 * in the program
 * @author Eirik Elvestad
 */
public class ArmiesController{

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

    /** Initialize the controller, and sets the tableview and listView for the scene. If there already exists armies
     * in {@link Singleton}, it imports these armies and set their information in tableView and ListView
     */
    @FXML
    public void initialize() {
        TableDecorator.initTable5Colum(armiesTable1);
        TableDecorator.initTable5Colum(armiesTable2);
       if(Singleton.getInstance().getArmy1() != null && Singleton.getInstance().getArmy2() != null){
           Army army1 = Singleton.getInstance().getArmy1();
           Army army2 = Singleton.getInstance().getArmy2();
           TextFieldArmyName1.setText(army1.getName());
           TextFieldArmyName2.setText(army2.getName());
           textArmyName1.setText(army1.getName());
           textArmyName2.setText(army2.getName());

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

    /**
     * createNewArmy switches sceen to {@link SwitchScene} CreateNewArmy.fxml
     * @param mouseEvent checks if the button "newArmy" is clicked by the mouse
     * @throws IOException if the new scene has invalid information
     */
    @FXML
    public void createNewArmy(MouseEvent mouseEvent) throws IOException {
        SwitchScene.createNewStage("CreateNewArmy");
    }

    /**
     * addUnits shows the view for adding a unit {@link SwitchScene} AddUnitsWindow.fxml. If there is no set army,
     * addUnits returns a AlertBox
     * @param mouseEvent checks if the button "addUnit" is clicked by the mouse
     * @throws IOException if the new scene has invalid information
     */
    @FXML
    public void addUnits(MouseEvent mouseEvent) throws IOException {
        if(Objects.equals(Singleton.getInstance().getButton().getId(), "addUnits1")
                && Singleton.getInstance().getArmy1() == null){
            AlertBox.alertError("set \"Change army\" on left side to add a unit to this army");
        }
        else if(Objects.equals(Singleton.getInstance().getButton().getId(), "addUnits2")
                && Singleton.getInstance().getArmy2() == null){
            AlertBox.alertError("set \"Change army\" on right side to add a unit to this army");
        }
        else{SwitchScene.createNewStage("AddUnitsWindow");}
    }

    /**
     * goToFrontPage takes the user to {@link SwitchScene} FrontPage.fxml
     * @param event checks if the button "goToFrontPage" is pressed by the mouse
     * @throws IOException if the new scene has invalid information
     */
    @FXML
    public void goToFrontPage(MouseEvent event) throws IOException {
        SwitchScene.switchScene("FrontPage",event);
    }

    /**
     * goToSimulationPage saves army1 and army2 to {@link Singleton}, and sends the user to {@link SwitchScene} SimulationPage.fxml.
     * If information is missing, it shows an alertbox.
     * @param event checks if the button "Save" is clicked by the mouse
     * @throws IOException if the new scene has invalid information
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
     *saveArmies method gets information from Tableview, and makes an army of the tableview. Then it passes the information
     * to Singleton, to further use it in simulation
     */
    @FXML
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

    /**
     * importFile method imports chosen file to directory armyCSVfiles in resources, then imports army from the new csv and sets
     * a new army in {@link Singleton}.
     * @throws IOException if there is something wrong with the files
     * @throws CsvValidationException if the csv file is damaged
     */
    @FXML
    private void importFile() throws IOException, CsvValidationException {
        ArmyFileHandling armyFileHandler = new ArmyFileHandling();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File file = fileChooser.showOpenDialog(WargamesGUI.getStage());
        try {
            Files.move(file.toPath(), Path.of("src/main/resources/edu/ntnu/idatt2001/eirielv/armycsvfiles/copiedFile.csv"));
        }catch (Exception e){
            AlertBox.alertError("Ingen fil ble valgt");
        }
        Army army = armyFileHandler.getArmyFromCSVInput(renameCSV());
        if(Objects.equals(Singleton.getInstance().getButton().getId(), "importArmy1")) {
            Singleton.getInstance().setArmy1(army);
            TableDecorator.fillTable(5, army, armiesTable1);
            TableDecorator.fillListView(army, armiesList1);
            textArmyName1.setText(army.getName());
        }
        else if(Objects.equals(Singleton.getInstance().getButton().getId(), "importArmy2")) {
            Singleton.getInstance().setArmy2(army);
            TableDecorator.fillTable(5, army, armiesTable2);
            TableDecorator.fillListView(army, armiesList2);
            textArmyName1.setText(army.getName());
        }

    }

    /**
     * renameCSV renames the imported file to armyname.
     * @return armyName received from the imported csv
     * @throws IOException if there is something wrong with the files
     * @throws CsvValidationException if the csv file is damaged
     */
    private String renameCSV() throws IOException, CsvValidationException {

        File file = new File("src/main/resources/edu/ntnu/idatt2001/eirielv/armycsvfiles/copiedFile.csv");
        Reader reader = Files.newBufferedReader(Path.of(file.getAbsolutePath()));
        CSVReader csvReader = new CSVReader(reader);
        String armyName = String.valueOf(csvReader.readNext()[0]);
        File rename = new File("src/main/resources/edu/ntnu/idatt2001/eirielv/armycsvfiles/" + armyName+".csv");
        file.renameTo(rename);
        return armyName;
    }

    /**
     * setButton recieves an actionEvent, and gets the button source of the event, then stores the information about
     * the last button pressed in ArmiesPage.fxml, and stores it in {@link Singleton}
     * @param actionEvent is an event triggered when there is a event on a button
     */
    public void setButton(javafx.event.ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Singleton.getInstance().setButton(button);
    }
}
