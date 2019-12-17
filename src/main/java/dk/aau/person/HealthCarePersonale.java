package dk.aau.person;

// subklasse til Person-klassen
public class HealthCarePersonale extends Person{  
    private String id;
    
    // constructor
    public HealthCarePersonale() {
        
    }

    // metode til at skifte patient
    private void changePatient(){
        // opretter ny instans af tempPatinet som overskriver den gamle
        Patient p = new Patient();
    }

    public void showHealthCarePersonaledata(){  // skal bruges til det endelige user interface
    }
}