package dk.aau.person; 

import java.sql.ResultSet;
import java.sql.SQLException;

import dk.aau.person.Patient;
import dk.aau.database.Queryable;

public class PersonHandler implements Queryable {

    // overskriver kommandoen i interfacet. Denne metode bliver kun kaldt hvis returnSqlQuery kommandoen går igennem
    @Override
    public void processResultSet(ResultSet rs) throws SQLException {
           while(rs.next()){
            Patient patientIFokus = new Patient(rs.getString("FirstName"), rs.getString("LastName"));
        }
    }

    // kommandoen til SQL databasen
    @Override
    public String returnSqlQuery() {
        String sqlStatement = "SELECT FirstName, LastName FROM Patients WHERE Patients.CPRnumber=" + Patient.getCprNummer();
        return sqlStatement;
    }
 }