package dk.aau.handler;

import dk.aau.database.*;
import dk.aau.person.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import dk.aau.*;

public class HandlerTemperature implements Queryable {



    
    @Override
    public void processResultSet(ResultSet rs) throws SQLException {
        while(rs.next()){
            String navn = rs.getString("Biomarker");
            double value = rs.getDouble("BiomarkerResult");
            String time = rs.getString("TimeOfTest");
            BiomarkerInfo.temperatur = value;
            BiomarkerInfo.temperaturTime = time;
            System.out.println(navn);
            System.out.println(value);
            System.out.println(time);
        }
    }
    
    @Override
    public String returnSqlQuery() {
        String sqlStatement = "SELECT Biomarker, BiomarkerResult, TimeOfTest FROM BiomarkerRegister WHERE Biomarker='Temperature' AND TimeOfTest = (SELECT MAX(TimeOfTest) FROM BiomarkerRegister WHERE Biomarker='Temperature' AND TimeOfTest BETWEEN  '"+ App.previousDate +"' AND '"+ App.currentDate +"') AND CPRnumber="+ Patient.getCprNummer();
    //    String sqlStatement = SELECT Biomarker, BiomarkerResult, TimeOfTest FROM `BiomarkerRegister` WHERE Biomarker="Temperature" AND TimeOfTest = (SELECT MAX(TimeOfTest) FROM BiomarkerRegister WHERE Biomarker="Temperature" AND TimeOfTest BETWEEN '2019-12-03 14:48:091 "AND" "2019-12-08 14:48:09") AND CPRnumber=" + Patient.getCprNummer();
        return sqlStatement;
    }
}