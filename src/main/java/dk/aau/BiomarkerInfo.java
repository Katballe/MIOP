package dk.aau;

import dk.aau.biomark.*;
public class BiomarkerInfo {    
    /*
    Denne klasse samler data fra de 11 handlers der henter biomarkører og kalder ResultBiomarker contructoren
    Variablerne sættes i den enkelte handler.
    */
    public static double pao2;
    public static double laktat;
    public static double temperatur;
    public static double saturation;
    public static double respfrek;
    public static double bilirubin;
    public static double kreatinin;
    public static double platelets;
    public static double pulse;
    public static double gcs;
    public static double sysbp;

    public static String pao2Time;
    public static String laktatTime;
    public static String temperaturTime;
    public static String saturationTime;
    public static String respfrekTime;
    public static String bilirubinTime;
    public static String kreatininTime;
    public static String plateletsTime;
    public static String pulseTime;
    public static String gcsTime;
    public static String sysbpTime;

    public void gatherBiomarkerData(){
        ResultBiomarker biomarker = new ResultBiomarker(temperatur, saturation, pulse, sysbp, respfrek,
        gcs, pao2, bilirubin, kreatinin, platelets, laktat, temperaturTime, saturationTime, pulseTime, sysbpTime, 
        respfrekTime, gcsTime, pao2Time, bilirubinTime, kreatininTime, plateletsTime, laktatTime);
 
        printValue();
    }

    public void printValue(){           // for at kunne tjekke i terminalen
        System.out.println("Value: "+ pao2 + " Time: " + pao2Time);
        System.out.println("Value: "+ laktat + " Time: " + laktatTime);
        System.out.println("Value: "+ temperatur + " Time: " + temperaturTime);
        System.out.println("Value: "+ saturation + " Time: " + saturationTime);
        System.out.println("Value: "+ respfrek + " Time: " + respfrekTime);
        System.out.println("Value: "+ bilirubin + " Time: " + bilirubinTime);
        System.out.println("Value: "+ kreatinin + " Time: " + kreatininTime);
        System.out.println("Value: "+ platelets + " Time: " + plateletsTime);
        System.out.println("Value: "+ pulse + " Time: " + pulseTime);
        System.out.println("Value: "+ gcs + " Time: " + gcsTime);
        System.out.println("Value: "+ sysbp + " Time: " + sysbpTime);
    }

}