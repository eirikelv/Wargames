package edu.ntnu.idatt2001.eirielv.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WargamesGUI extends Application {

    public static void main(String[] args){
        launch(args);
    }

    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/ntnu/idatt2001/eirielv/view/FrontPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1300, 866);
            stage.setTitle("The War Games");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception{
        super.stop();
    }
}
