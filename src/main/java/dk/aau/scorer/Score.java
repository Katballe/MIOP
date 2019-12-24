package dk.aau.scorer;

import dk.aau.biomark.ResultBiomarker;
import dk.aau.person.Patient;


public class Score {

    // Variabler 
    private static int toksScore;
    private static int sofaScore;
    private static int sofaPointList[];
    private static int toksPointList[];
    private static Boolean septicShockScore;

    public static Boolean DataMissing = false;
    
    public Score() {
        // opretter to lister som biomarkørerne skal puttes i
        sofaPointList = new int[6];
        /*
        * 0 - gcs 1 - systolisk blodtryk 2 - pao2 3 - bilirubin 4 - kreatinin 5 -
        * trombocyttal
        */
        toksPointList = new int[6];
        /*
        * 0 - gcs 1 - systolisk blodtrk 2 - puls 3 - saturation 4 - temperatur 5 -
        * respirations frekvens
        */
        
        // udregner scorerne
        giveToksPoint(ResultBiomarker.biomarkerList[5], ResultBiomarker.biomarkerList[10],
        ResultBiomarker.biomarkerList[8], ResultBiomarker.biomarkerList[9],
        ResultBiomarker.biomarkerList[1], ResultBiomarker.biomarkerList[7]);

        calculateToksScore();
        giveSofaPoint(ResultBiomarker.biomarkerList[3], ResultBiomarker.biomarkerList[2],
        ResultBiomarker.biomarkerList[4], ResultBiomarker.biomarkerList[0],
        ResultBiomarker.biomarkerList[1], ResultBiomarker.biomarkerList[5]);
        calculateSofaScore();
        calculateSepticShockScore(ResultBiomarker.biomarkerList[6], ResultBiomarker.biomarkerList[1]);
        

        // temp  metoder til at vise ting
        Patient.showHealthCareData();
        visDataTemp();
        // bare for sjov
        if (DataMissing == true)
        System.out.println("--- SCORE DATA MISSING ---");
    }
    
    private boolean calculateSepticShockScore(double laktat, double systoliskBlodtryk) {
        if (laktat >= 2 && systoliskBlodtryk <= 90 && systoliskBlodtryk != 0) {
            septicShockScore = true;
            
        } else {
            septicShockScore = false;
        }
        return septicShockScore;
        
    }
    
    private void giveSofaPoint(double kreatinin, double bilirubin, double trombocyttal, double pao2,
    double systoliskBlodtryk, double gcs) {
        // gcs
        if (gcs == 15) {
            sofaScore = sofaScore + 0;
            sofaPointList[0] = 0;
        } else if (gcs == 13 || gcs == 14) {
            sofaPointList[0] = 1;
            System.out.println("Givet value: " + sofaPointList[0]);
        } else if (10 <= gcs && 12 >= gcs) {
            sofaPointList[0] = 2;
            
        } else if (6 <= gcs && 9 >= gcs) {
            sofaPointList[0] = 3;
        } else if (gcs == 0) {
            System.out.println("Givet value: " + sofaPointList[0]);
            DataMissing = true;
            sofaPointList[0] = 0;
        } else {
            sofaPointList[0] = 4;
        }
        // bilirubin
        if (bilirubin < 20) {
            sofaPointList[3] = 0;
        } else if (bilirubin >= 20 && bilirubin <= 32) {
            sofaPointList[3] = 1;
            
        } else if (bilirubin >= 33 && bilirubin <= 101) {
            sofaPointList[3] = 2;
            
        } else if (bilirubin >= 102 && bilirubin <= 204) {
            sofaPointList[3] = 3;
        } else if (bilirubin == 0) {
            DataMissing = true;
            sofaPointList[3] = 0;
            
        } else {
                        sofaPointList[3] = 4;
        }
        // kreatinin
        if (kreatinin < 110) {
            sofaPointList[4] = 0;
        } else if (kreatinin >= 111 && kreatinin <= 170) {
            sofaPointList[4] = 1;
        } else if (kreatinin >= 171 && kreatinin <= 299) {
            sofaPointList[4] = 2;
        } else if (kreatinin >= 300 && kreatinin <= 400) {
            sofaPointList[4] = 3;
        } else if (kreatinin == 0) {
            DataMissing = true;
            sofaPointList[4] = 0;
        } else {
            sofaPointList[4] = 4;
        }
        // trombocyttal
        if (trombocyttal >= 150) {
            sofaPointList[5] = 0;
        } else if (trombocyttal < 150 && 100 < trombocyttal) {
            sofaPointList[5] = 1;
            
        } else if (trombocyttal <= 100 && 50 < trombocyttal) {
            sofaPointList[5] = 2;
            
        } else if (trombocyttal <= 50 && 20 < trombocyttal) {
            sofaPointList[5] = 3;
            
        } else if (trombocyttal == 0) {
            DataMissing = true;
            sofaPointList[5] = 0;
        } else {
            sofaPointList[5] = 3;
        }
        // pao2
        if (pao2 >= 10.7) {
            sofaScore = sofaScore + 0;
            sofaPointList[2] = 0;
        } else if (pao2 <= 10.7 && pao2 >= 8.1) {
            sofaPointList[2] = 1;
            
        } else if (pao2 == 0) {
            DataMissing = true;
            sofaPointList[2] = 0;
        } else {
            sofaPointList[2] = 2;
        }
        // systoliskBlodtryk
        if (systoliskBlodtryk > 100) {
            sofaPointList[1] = 0;
            
        } else if (systoliskBlodtryk == 0) {
            DataMissing = true;
            sofaPointList[1] = 0;
        } else {
            sofaPointList[1] = 1;
        }
    }
    
    private int calculateSofaScore() {
        for (int i = 0; i <= sofaPointList.length - 1; i++) {
            sofaScore += sofaPointList[i];
            System.out.println("Calc " + sofaPointList[i]);
        }
        return sofaScore;
    }
    
    private void giveToksPoint(double gcs, double temperatur, double sauration, double puls, double sys,
    double respFrek) {
        {
            // respFrek
            if (respFrek >= 12 && 20 >= respFrek) {
                toksPointList[5] = 0;
            } else if (respFrek >= 8 && 11 >= respFrek) {
                toksPointList[5] = 1;
            } else if (respFrek >= 21 && 24 >= respFrek) {
                toksPointList[5] = 2;
                
            } else if (respFrek == 0) {
                DataMissing = true;
                toksPointList[5] = 0;
            } else {
                toksPointList[5] = 3;
            }
            
            // saturation
            if (sauration >= 96) {
                toksPointList[3] = 0;
            } else if (sauration == 94 || 95 == sauration) {
                toksPointList[3] = 1;
            } else if (sauration == 92 || 93 == sauration) {
                toksPointList[3] = 2;
                System.out.println("HER!!! " + toksPointList[3]);
            } else if (sauration == 0) {
                DataMissing = true;
                toksPointList[3] = 0;
            } else {
                toksPointList[3] = 3;
            }
            
            // systoliskBP
            if (110 <= sys && 219 >= sys) {
                toksPointList[1] = 0;
            } else if (100 <= sys && 109 >= sys) {
                toksPointList[1] = 1;
            } else if (90 <= sys && 99 >= sys) {
                toksPointList[1] = 2;
            } else if (sys == 0) {
                DataMissing = true;
                toksPointList[1] = 0;
            } else {
                toksPointList[1] = 3;
            }
            
            // gcs
            if (gcs == 15) {
                toksPointList[0] = 0;
            } else if (gcs == 0) {
                DataMissing = true;
                toksPointList[0] = 0;
            } else {
                toksPointList[0] = 3;
            }
            
            // temp
            if (36 <= temperatur && 37.9 >= temperatur) {
                toksPointList[4] = 0;
            } else if (35 <= temperatur && 35.9 >= temperatur || 38 <= temperatur && 38.9 >= temperatur) {
                toksPointList[4] = 1;
            } else if (temperatur >= 39) {
                toksPointList[4] = 2;
            } else if (temperatur == 0) {
                DataMissing = true;
                toksPointList[4] = 0;
            } else {
                toksPointList[4] = 3;
                toksScore = toksScore + 3;
            }
            
            // puls
            if (50 <= puls && 89 >= puls) {
                toksPointList[2] = 0;
            } else if (40 <= puls && 49 >= puls || 90 <= puls && 109 >= puls) {
                toksPointList[2] = 1;
            } else if (110 <= puls && 129 >= puls) {
                toksPointList[2] = 2;
            } else if (puls == 0) {
                DataMissing = true;
                toksPointList[2] = 0;
                
            } else {
                toksPointList[2] = 3;
            }
        }
    }
    
    private int calculateToksScore() {
        for (int i = 0; i <= toksPointList.length - 1; i++) {
            toksScore += toksPointList[i];
        }
        return toksScore;
    }
    
    public void showToksScore(){

    }

    public void showToksPoint(){

    }

    public void showSofaScore(){

    }

    public void showSofaPoint(){

    }   
    public void showSepticShock(){

    }


    public static void visDataTemp() {
        System.out.println("");
        System.out.println("");
        System.out.println("        TOKS Point                      SOFA Point");
        System.out.println(
        "GCS:                   " + toksPointList[0] + "          GCS:                 " + sofaPointList[0]);
        System.out.println(
        "Systolic BP:           " + toksPointList[1] + "          Systolic BP:         " + sofaPointList[1]);
        System.out.println(
        "Pulse:                 " + toksPointList[2] + "          PaO2:                " + sofaPointList[2]);
        System.out.println(
        "Saturation:            " + toksPointList[3] + "          Bilirubin:           " + sofaPointList[3]);
        System.out.println(
        "Temperature:           " + toksPointList[4] + "          Creatine:            " + sofaPointList[4]);
        System.out.println(
        "Respiratory rate:      " + toksPointList[5] + "          Platelats:           " + sofaPointList[5]);
        System.out.println("");
        System.out.println("TOKS Score:     " + toksScore);
        System.out.println("SOFA Score:     " + sofaScore);
        System.out.print("Septic Shock:   ");
        if (septicShockScore == true){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        System.out.println("");
        System.out.println(" Biomarker               Ref.      Result");
        System.out.println("- Temperature:            36-37.9 "+ ResultBiomarker.biomarkerList[10] + "   Timestamp: " + ResultBiomarker.temperaturTime);
        System.out.println("- Saturation:             96+     "+ (int)ResultBiomarker.biomarkerList[8] + "     Timestamp: " + ResultBiomarker.saturationTime);
        System.out.println("- Pulse:                  50-89   "+ (int)ResultBiomarker.biomarkerList[9] + "     Timestamp: " + ResultBiomarker.pulseTime);
        System.out.println("- Systolisc BP:         < 100     "+ (int)ResultBiomarker.biomarkerList[1] + "    Timestamp: " + ResultBiomarker.sysbpTime);
        System.out.println("- Respiratory Rate:       12-20   "+ (int)ResultBiomarker.biomarkerList[7] + "     Timestamp: " + ResultBiomarker.respfrekTime);
        System.out.println("- GCS:                    15      "+ (int)ResultBiomarker.biomarkerList[5] + "     Timestamp: " + ResultBiomarker.gcsTime);
        System.out.println("- PaO2:                 > 10.7    "+ ResultBiomarker.biomarkerList[0] + "   Timestamp: " + ResultBiomarker.pao2Time);
        System.out.println("- Bilirubin:            < 20      "+ (int)ResultBiomarker.biomarkerList[2] + "      Timestamp: " + ResultBiomarker.bilirubinTime);
        System.out.println("- Creatinine:           < 110     "+ (int)ResultBiomarker.biomarkerList[3] + "    Timestamp: " + ResultBiomarker.kreatininTime);
        System.out.println("- Platelets:            > 150     "+ (int)ResultBiomarker.biomarkerList[4] + "    Timestamp: " + ResultBiomarker.plateletsTime);
        System.out.println("- Lactate:              < 2       "+ ResultBiomarker.biomarkerList[6] + "    Timestamp: " + ResultBiomarker.lactateTime);
        
        System.out.println("");
        System.out.println("Data missing: " + DataMissing);
        System.out.println("");
    }
}
