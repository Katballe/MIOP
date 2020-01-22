package dk.aau.person;

// subklasse til Person-klassen
public class HealthCarePersonale extends Person{  
    private int id;
    
    // constructor
    public HealthCarePersonale(int id) {
        this.id = id;
    }

    // metode til at skifte patient
    private static void changePatient(){
        // opretter ny instans af tempPatinet som overskriver den gamle
        Patient p = new Patient(); // det her kan udskiftes med en nulstil funktion for at forsikre at der altid kun er en patient.
        Patient.setCprNumber((Long) null);
    }

    public static void changePatientButton(){
        changePatient();
    }

    public void showHealthCarePersonaledata(){  // skal bruges til det endelige user interface
    }
}