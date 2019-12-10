package dk.aau.database;

import java.sql.ResultSet;
import java.sql.SQLException;

// Interface med to metoder, processResultSet og returnSqlQuery
// disse metoder bliver overskrevet i deres respektive handlers
public interface Queryable {

    void processResultSet(ResultSet rs) throws SQLException;
    String returnSqlQuery(); 
}