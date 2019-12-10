package dk.aau.handler;

import dk.aau.database.*;
import dk.aau.person.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import dk.aau.*;

public class HandlerRespRate implements Queryable {



    
    @Override
    public void processResultSet(ResultSet rs) throws SQLException {
        while(rs.next()){
            String navn = rs.getString("Biomarker");
            double value = rs.getDouble("BiomarkerResult");
            String time = rs.getString("TimeOfTest");
            BiomarkerInfo.respfrek = value;
            BiomarkerInfo.respfrekTime = time;
            System.out.println(navn);
            System.out.println(value);
            System.out.println(time);
        }
    }
    
    @Override
    public String returnSqlQuery() {
        String sqlStatement = "SELECT Biomarker, BiomarkerResult, TimeOfTest FROM BiomarkerRegister WHERE Biomarker = 'RespirationRate' AND TimeOfTest = (SELECT MAX(TimeOfTest) FROM BiomarkerRegister WHERE Biomarker = 'RespirationRate' AND TimeOfTest BETWEEN "+"'"+App.previousDate+"' AND '"+ App.currentDate+"') AND CPRnumber="+ Patient.getCprNummer();
        return sqlStatement;
    }
}