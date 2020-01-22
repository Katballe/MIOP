package dk.aau.controller;

import dk.aau.person.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CprViewHandler {
    
    private static long cpr;
    private Stage dialogStage;
    
    @FXML
    private TextField writeCPR;
    
    // Oversætter tekststrængen i brugergrænsefladen til en long
    public static long setCprNumber(String cprNumber) {
        cpr = Long.valueOf(cprNumber).longValue();
        return cpr;
    }
    
    // Giver funktionalitet til knappen
    public void getInformation() {
        try {   // succes kriterie
            Patient.setCprNumber(CprViewHandler.setCprNumber(writeCPR.getText()));
            
            Patient pa = new Patient();
        } catch (Exception e) {
            // forkert format grundet tegn og/eller bogstaver
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ugyldigt CPR-nummer format");
            alert.setHeaderText("CPR-nummeret skal være 10 tal.");
            alert.setContentText("CPR-nummeret skal have formatet 10 tal.\nIngen bogstaver, tegn eller mellemrum. ");
            alert.showAndWait();
        }   
    }
}