package dk.aau.person;
import dk.aau.App;
import dk.aau.biomark.ResultBiomarker;
import dk.aau.database.DatabaseManipulator;
import dk.aau.scorer.Score;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Patient extends Person {   // gør patinet til subklasse til Person-klassen
    
    // Attributter
    private static long cpr;
    private static String gender;
    private static int age;
    private Stage dialogStage;
    
    // constructor for tempPatient. Bliver CPR-nummeret ikke verificeret, bliver den "rigtige" patient instans aldrig oprettet
    // hvis CPR nummeret bliver verificeret, bliver den "rigtige" patinet construktor kaldt, og en instans af den bliver oprettet
    // inkapsulering
    public Patient(){
        
        //    Scanner myInput= new Scanner(System.in);
        //    System.out.println("Indtast CPR-nummer:");
        //    long CPRnummerHent = myInput.nextLong();
        
        verifyCpr(cpr);
        
        //    myInput.close(); // scannere skal lukkes 
    }
    
    public static void setCprNumber(long cprNumber){
        cpr = cprNumber;
    }
    
    /*
    Constructor for den rigtige "Patient". Denne construtor bliver kaldt
    når data tilhørende patienten hentes.
    */
    public Patient(String FirstName, String LastName) {
        
        firstName = FirstName;
        lastName = LastName;
        
    }
    
    
    private void verifyCpr(long CPRnumber){
        int length = String.valueOf(CPRnumber).length();            // Laver en int der er ekvivalent til længden af det  
        if (length != 10){                                          // indtastede CPR nummer og tjekker at dets længde 
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ugyldigt CPR-nummer format");
            alert.setHeaderText("CPR-nummeret skal være 10 tal.");
            alert.setContentText("Indsæt alle 10 cifre i CPR-nummeret.");
            
            alert.showAndWait();
        } else {                                                    // Hvis længden er 10, gemmes CPRnummeret til senere metoder
            cpr=CPRnumber;                                    
            getHealthCaredata(cpr);                                 // Henter Sundhedsdata for patienten
            if (firstName == null){                                 // Hvis patineten IKKE HAR et fornavn, indikerer det at ..
                Alert alert = new Alert(AlertType.ERROR);           // .. der ikke er en person med det CPR-nummer, og der laves en fejlmeddelelse
                alert.initOwner(dialogStage);
                alert.setTitle("Ugyldigt CPR-nummer");
                alert.setHeaderText("Ingen patienter med det indtastede CPR-nummer.");
                alert.setContentText("CPR-nummeret er ikke registreret, eller du har ikke adgang til det.\nPrøv igen eller kontakt administrationen.");
                
                alert.showAndWait();             
            } else {                                            // succes                                
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.initOwner(dialogStage);
                alert.setTitle("Succes");
                alert.setHeaderText("CPR-nummeret blev succesfuldt verificerert");
                alert.setContentText("--- Resultatsiden er under konstruktion --- \nResultaterne kan ses i terminalen.");
                alert.show();
                App.closeWindow();
                
                ResultBiomarker b = new ResultBiomarker();        // Hvis petienten HAR et fornavn oprettes en instans af ResultBiomarker og Score 
                Score Score = new Score(); 
            }
       } 
    }
    
    public static long getCprNummer(){                              // Getter der anvedes til at hente data i alle handlers
        return cpr;                                                 // alle kan hente den, men de kan ikke ændre attributten cpr
    }
    
    private void getHealthCaredata(long CPRnummerHent){              // Opretter PersonHandler og henter sundhedsdata
        PersonHandler ph = new PersonHandler();             
        DatabaseManipulator.executeQueryWithResultSet(ph);
    }
    
    public static void showHealthCareData(){                        // skal anvendes til user interface
        System.out.println("");
        System.out.println("---------------------- MIOP ----------------------");
        System.out.println("");                             
        System.out.println("CPR-number:  " + cpr);
        System.out.println("First name:  " + firstName);
        System.out.println("Last name:   " + lastName);
        System.out.println("Gender:      " + gender);
        System.out.println("Age:         " + age);
    }
    
}