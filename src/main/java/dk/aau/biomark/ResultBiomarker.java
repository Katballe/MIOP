package dk.aau.biomark;

import dk.aau.BiomarkerInfo;
import dk.aau.database.DatabaseManipulator;
import dk.aau.handler.*;

public class ResultBiomarker {
    // tidsvariabler 
    public static String pao2Time;
    public static String lactateTime;
    public static String temperaturTime;
    public static String saturationTime;
    public static String respfrekTime;
    public static String bilirubinTime;
    public static String kreatininTime;
    public static String plateletsTime;
    public static String pulseTime;
    public static String gcsTime;
    public static String sysbpTime;
    // Attributter
    private static double paO2;
    private static int systolicBloodPressure;
    private static double bilirubin;
    private static double creatine;
    private static double platelets;
    private static int gcs; 
    private static double lactate;
    private static int respirationrate;
    private static double saturation;
    private static int pulse;
    private static double temperature;
    public static double[] biomarkerList;  /* public, eftersom den ikke ændrer biomarkørerne i denne 
                                               klasse og skal anvendes til at udregne scorer
        PLASSERNE FOR DE ENKELTE BIOMARKØRER I biomarkoerList
    0 - paO2            
    1 - systoliskBlodtryk
    2 - bilirubin
    3 - kreatinin
    4 - trombocyttal
    5 - gcs
    6 - laktat
    7 - respirationsFrekvens
    8 - saturation
    9 - puls
    10 - temperatur
    */
    
    // Constructor for tempResultatbiomarkør. Henter data og kalder den "rigtige" biomarkør constructor
    public ResultBiomarker(){
        getBiomarkerDB();
    }
    
    
    // Den rigtige biomarkør constructor
    public ResultBiomarker(double temp, double sat, double p, double sysBlodtryk, 
    double respFrekvens, double GCS, double PaO2, double Bilirubin, double Kreatinin, 
    double Trombocyttal, double lak, String tempTid, String satTid, String pTid,
    String sysTid, String respfrekTid, String gcsTid, String pao2Tid, String biliTid,
    String kreatininTid, String tromboTid, String lakTid) {
        
        biomarkerList = new double[11];        // Opretter biomarkør listen
        
        // sætter tidsvariablerne
        pao2Time = pao2Tid;
        lactateTime = lakTid;
        temperaturTime = tempTid;
        saturationTime = satTid;
        respfrekTime = respfrekTid;
        bilirubinTime = biliTid;
        kreatininTime = kreatininTid;
        plateletsTime = tromboTid;
        pulseTime =pTid;
        gcsTime = gcsTid;
        sysbpTime = sysTid;
        
        //----- TOKS variabler sættes -----
        temperature = temp;
        saturation = sat;
        pulse = (int) p;
        respirationrate = (int) respFrekvens;
        
        //------ SOFA variabler sættes ------
        paO2 = PaO2;
        System.out.println("HER! " + paO2);
        bilirubin = Bilirubin;
        creatine = Kreatinin;
        platelets = Trombocyttal;

        //------ Variabler fælles for TOKS og SOFA sættes ------
        gcs = (int) GCS;
        systolicBloodPressure = (int) sysBlodtryk;
        
        //------ Laktat
        lactate = lak;

        //------ Biomarkør listens variabler sættes -----
        biomarkerList[10] = temperature;
        biomarkerList[9] = pulse;
        biomarkerList[8] = saturation;
        biomarkerList[7] = respirationrate;
        biomarkerList[6] = lactate;
        biomarkerList[5] = gcs;
        biomarkerList[4] = platelets;
        biomarkerList[3] = creatine;
        biomarkerList[2] = bilirubin;
        biomarkerList[1] = systolicBloodPressure;
        biomarkerList[0] = paO2;
        System.out.println("HER! 2 " + paO2);
        
        compareBiomarker();

        // System.out.println(temperatur);
        // System.out.println(puls);
        // System.out.println(systoliskBlodtryk);
    }
    
    // henter data fra databasen
    private void getBiomarkerDB(){
        BiomarkerInfo bi = new BiomarkerInfo();
        HandlerLactate hl = new HandlerLactate();
        DatabaseManipulator.executeQueryWithResultSet(hl);
        HandlerBilirubin hb = new HandlerBilirubin();
        DatabaseManipulator.executeQueryWithResultSet(hb);
        HandlerSysBP sb = new HandlerSysBP();
        DatabaseManipulator.executeQueryWithResultSet(sb);
        HandlerGCS hg = new HandlerGCS();
        DatabaseManipulator.executeQueryWithResultSet(hg);
        HandlerCreatine hk = new HandlerCreatine();
        DatabaseManipulator.executeQueryWithResultSet(hk);
        HandlerPaO2 hp = new HandlerPaO2();
        DatabaseManipulator.executeQueryWithResultSet(hp);
        HandlerPlatelets hpl = new HandlerPlatelets();
        DatabaseManipulator.executeQueryWithResultSet(hpl);
        HandlerPulse hpu = new HandlerPulse();
        DatabaseManipulator.executeQueryWithResultSet(hpu);
        HandlerRespRate hr = new HandlerRespRate();
        DatabaseManipulator.executeQueryWithResultSet(hr);
        HandlerSaturation hs = new HandlerSaturation();
        DatabaseManipulator.executeQueryWithResultSet(hs);
        HandlerTemperature ht = new HandlerTemperature();
        DatabaseManipulator.executeQueryWithResultSet(ht);
        bi.gatherBiomarkerData();  // samler data og opretter den "rigtige" Biomarkør Constructor


    }
    
    // Anvendes til at tjekke om værdier skal markeres som værende udenfor normalområdet
    public static Boolean tempBool = true;
    public static Boolean satBool = true;
    public static Boolean pulsBool = true; 
    public static Boolean sysBool = true;
    public static Boolean respfrekBool = true;
    public static Boolean gcsBool = true;
    public static Boolean pao2Bool = true;
    public static Boolean biliBool = true;
    public static Boolean kreaBool = true;
    public static Boolean tromboBool = true;
    public static Boolean laktBool = true;

    // sammenligner biomarkører med normalområdet (området hvor TOKS og SOFA giver nul point). Farvemarkering
    private void compareBiomarker(){
        if (temperature < 36 || temperature > 37.9) {
            tempBool = false;
        }
        if (saturation < 96){
            satBool = false;
        }
        if (pulse < 50 || pulse > 89) {
            pulsBool = false;
        }
        if (systolicBloodPressure > 100) {
            sysBool = false; 
        }
        if (respirationrate < 12 || respirationrate > 20){
            respfrekBool = false;
        }
        if (gcs < 15){
            gcsBool = false;
        }
        if (paO2 < 10.7 ){
            pao2Bool = false;
        }
        if (bilirubin > 20){
            biliBool = false;
        }
        if (creatine > 110){
            kreaBool = false;
        }
        if (lactate >= 2){
            laktBool = false;
        }
        if (platelets < 150) {
            tromboBool = false;
        }
    }

    public void showCompareBiomarker(){

    }


}


