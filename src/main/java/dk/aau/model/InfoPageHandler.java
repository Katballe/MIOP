package dk.aau.model;

import dk.aau.App;
import dk.aau.biomark.ResultBiomarker;
import dk.aau.person.HealthCarePersonale;
import dk.aau.person.Patient;
import dk.aau.scorer.Score;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoPageHandler {
    // demografiske informationer
    @FXML
    private Label cprLabel;

    @FXML
    private Label patientNameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label genderLabel;
    // Sundhedspersonale
    @FXML
    private Label hcpNameLabel;

    @FXML
    private Label hcpIdLabel;

    // SOFA
    @FXML
    private Label bilirubinResultatLabel;
    @FXML
    private Label cratinineResultatLabel;
    @FXML
    private Label pao2ResultatLabel;
    @FXML
    private Label sysbpResultatLabel;
    @FXML
    private Label plateletsResultatLabel;
    @FXML
    private Label gcsResultatLabel;
    

    @FXML
    private Label lactateResultatLabel;
    @FXML
    private Label sysbpResultatLabel1;

    // TOKS
    @FXML
    private Label sysbpResultatLabel2;
    @FXML
    private Label gcsResultatLabel1;
    @FXML
    private Label satResultatLabel;
    @FXML
    private Label pulseResultatLabel;
    @FXML
    private Label respfrekResultatLabel;
    @FXML
    private Label tempResultatLabel;

    // point & score
    @FXML
    private Label toksScoreLabel;
    @FXML
    private Label sofaScoreLabel;
    @FXML
    private Label septicShockLabel;
    @FXML
    private Label satPointLabel;
    @FXML
    private Label sysbpToksPointLabel;
    @FXML
    private Label sysbpSofaPointLabel;
    @FXML
    private Label pulsePointLabel;
    @FXML
    private Label gcsToksPointLabel;
    @FXML
    private Label gcsSofaPointLabel;
    @FXML
    private Label respfrekPointLabel;
    @FXML
    private Label tempPointLabel;
    @FXML
    private Label biliPointLabel;
    @FXML
    private Label creaPointLabel;
    @FXML
    private Label pao2PointLabel;
    @FXML
    private Label platPointLabel;

    @FXML
    public void initialize() {
        // patient info
        cprLabel.setText(Long.toString(Patient.getCprNummer()));
        patientNameLabel.setText(Patient.getName());
        ageLabel.setText(Integer.toString(Patient.getAge()));
        genderLabel.setText(Patient.getGender());
        // sundhedspersonale
         hcpNameLabel.setText("Dummy Navn");
         hcpIdLabel.setText("Dummy ID");
        // Biomarkers
                if (ResultBiomarker.biomarkerList[0]==0){
                    pao2ResultatLabel.setText("-");
                } else {
                    pao2ResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[0]));
                }
                if (ResultBiomarker.biomarkerList[1]==0){
                    sysbpResultatLabel.setText("-");
                    sysbpResultatLabel1.setText("-");
                    sysbpResultatLabel2.setText("-");
                } else {
                    sysbpResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[1]));
                    sysbpResultatLabel1.setText(Double.toString(ResultBiomarker.biomarkerList[1]));
                    sysbpResultatLabel2.setText(Double.toString(ResultBiomarker.biomarkerList[1]));
                }
                if (ResultBiomarker.biomarkerList[2]==0){
                    bilirubinResultatLabel.setText("-");
                } else {   
                    bilirubinResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[2]));
                }
                
                if (ResultBiomarker.biomarkerList[3]==0){
                    cratinineResultatLabel.setText("-");
                } else {
                    cratinineResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[3]));    
                }
                
                if (ResultBiomarker.biomarkerList[4]==0){
                    plateletsResultatLabel.setText("-");
                } else {
                    plateletsResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[4]));
                }
                
                if (ResultBiomarker.biomarkerList[5]==0){
                    gcsResultatLabel.setText("-");
                    gcsResultatLabel1.setText("-");
                } else {
                    gcsResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[5]));
                    gcsResultatLabel1.setText(Double.toString(ResultBiomarker.biomarkerList[5]));
                }
                
                if (ResultBiomarker.biomarkerList[6]==0){
                    lactateResultatLabel.setText("-");
                } else {
                    lactateResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[6]));
                }
                
                if (ResultBiomarker.biomarkerList[7]==0){
                    respfrekResultatLabel.setText("-");
                } else {
                    respfrekResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[7]));
                }
                
                if (ResultBiomarker.biomarkerList[8]==0){
                    satResultatLabel.setText("-");
                } else {
                    satResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[8]));
                }
                
                if (ResultBiomarker.biomarkerList[9]==0){
                    pulseResultatLabel.setText("-");
                } else {
                    pulseResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[9]));
                }
                
                if (ResultBiomarker.biomarkerList[10]==0){
                    tempResultatLabel.setText("-");
                } else {
                    tempResultatLabel.setText(Double.toString(ResultBiomarker.biomarkerList[10]));
                }
                
            
            // Point
                satPointLabel.setText(Integer.toString(Score.getToksPoint(3)));
                sysbpToksPointLabel.setText(Integer.toString(Score.getToksPoint(1)));
                sysbpSofaPointLabel.setText(Integer.toString(Score.getSofaPoint(1)));
                pulsePointLabel.setText(Integer.toString(Score.getToksPoint(2)));
                gcsToksPointLabel.setText(Integer.toString(Score.getToksPoint(0)));
                gcsSofaPointLabel.setText(Integer.toString(Score.getSofaPoint(0)));
                respfrekPointLabel.setText(Integer.toString(Score.getToksPoint(5)));
                tempPointLabel.setText(Integer.toString(Score.getToksPoint(4)));
                biliPointLabel.setText(Integer.toString(Score.getSofaPoint(3)));
                creaPointLabel.setText(Integer.toString(Score.getSofaPoint(4)));
                pao2PointLabel.setText(Integer.toString(Score.getSofaPoint(2)));
                platPointLabel.setText(Integer.toString(Score.getSofaPoint(5)));
            

            // Score
                toksScoreLabel.setText(Integer.toString(Score.getToksScore()));
                sofaScoreLabel.setText(Integer.toString(Score.getSofaScore()));
                septicShockLabel.setText(Score.getSepticShock());
            
        }
    
    public void changePatientButton() {
        Patient.setName(null);
        App.changeStageToCpr();
//        HealthCarePersonale.changePatientButton();
    }
    
    public void endButton() {
        App.closeWindow();
    }
    

    

    public InfoPageHandler(){
        //showDetails();
    }
}
