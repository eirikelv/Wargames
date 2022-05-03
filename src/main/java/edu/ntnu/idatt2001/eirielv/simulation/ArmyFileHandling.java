package edu.ntnu.idatt2001.eirielv.simulation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import edu.ntnu.idatt2001.eirielv.Units.*;

import java.util.regex.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * ArmyFileHandling reads and writes csv files, to export and import army objects
 * @author Eirik Elvestad
 */
public class ArmyFileHandling {

    static String stringPath = ("src/main/resources/ArmyCSVFiles/");

    /**
     * getArmyFromCSVInput reads a CSV file and makes an army object out of it. The first line it reads is the name of
     * the army. Then it reads the next lines, where the first string combination is the Unit type, the second is the
     * unit name, and the last is the health
     * @param armyName is the name of the army, and the name of the file
     * @return army object
     */
    public Army getArmyFromCSVInput(String armyName) {
        Army army = new Army(armyName);
        List<Unit> newArmy = new ArrayList<>();
        try{
            Reader reader = Files.newBufferedReader(Path.of(stringPath + armyName + ".csv"));
            CSVReader csvReader = new CSVReader(reader);
            String[] line;
            while((line = csvReader.readNext()) != null){
                switch(line[0]){
                    case "InfantryUnit":
                        String name = line[1];
                        int health = Integer.parseInt(line[2]) ;
                        newArmy.add(new InfantryUnit(name, health));
                        break;

                    case "RangedUnit":
                        name = line[1];
                        health = Integer.parseInt(line[2]) ;
                        newArmy.add(new RangedUnit(name, health));
                        break;

                    case "CommanderUnit":
                        name = line[1];
                        health = Integer.parseInt(line[2]) ;
                        newArmy.add(new CommanderUnit(name, health));
                        break;

                    case "CavalryUnit":
                        name = line[1];
                        health = Integer.parseInt(line[2]) ;
                        newArmy.add(new CavalryUnit(name, health));
                        break;
                }

            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        army.addAll(newArmy);
        return army;
    }


    /**
     * writeUnitsToCSVFile takes in a army and. The csv file will be named as the army name. If a file with the same
     * name already exists or the name is with special characters, the method throws illegalArgumentException.
     * @param army is the army object that will be made to a CSV file
     */
    public void writeUnitsToCSVFile(Army army) {
        File armyCSVFile = new File(stringPath + army.getName().trim() + ".csv");
        if(invalidArmyName(army.getName())) throw new IllegalArgumentException("Name cant contain special characters");
        if(armyCSVFile.exists()) throw new IllegalArgumentException("file already exist");
        try(FileWriter output = new FileWriter(armyCSVFile);
            CSVWriter writer = new CSVWriter(output,',',CSVWriter.NO_QUOTE_CHARACTER
                    ,CSVWriter.NO_ESCAPE_CHARACTER
                    ,CSVWriter.DEFAULT_LINE_END)){
            String[] header = {army.getName()};
            writer.writeNext(header);
            army.getAllUnits().forEach(unit -> {
                String[] line = {unit.getClass().getSimpleName(), unit.getName(),String.valueOf(unit.getHealth()),
                        String.valueOf(unit.getAttack()), String.valueOf(unit.getArmor())};
                writer.writeNext(line);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks if a army name has special characters. If the name contains special characters, it returns
     * true, and the name is invalid.
     * @param name takes a String name
     * @return true if the name has special characters, else it returns false
     */
    public boolean invalidArmyName(String name){
        Pattern pattern = Pattern.compile("\\p{Punct}");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
