package edu.ntnu.idatt2001.eirielv.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * WarGamesGUI starts the application
 * @author Eirik Elvestad
 */
public class WargamesGUI extends Application {

    private static Stage stage;

    /**
     * Method for launching the application
     * @param args
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * The start method starts the first stage, and sets the size of it
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        try {
            this.stage=stage;
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ntnu/idatt2001/eirielv/view/FrontPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1300, 866);
            stage.setTitle("The War Games");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * init decides what the application should do before scene start
     * @throws Exception if there is a fault before startup
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * stop decides the program should do while exiting the application
     * @throws Exception if there is a fault in the closing stage
     */
    @Override
    public void stop() throws Exception{
        super.stop();
    }

    /**
     * getStage returns the stage the application is on
     * @return stage represented as Stage
     */
    public static Stage getStage(){
        return stage;
    }
}
