package dk.aau.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManipulator {
    
    // tjekker forbindelsen til "com.mysql.cj.jdbc.Driver"
    // med detaljerne indtasteti DatabaseDetails
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                conn = DriverManager.getConnection(DatabaseDetails.host, DatabaseDetails.username, DatabaseDetails.password);
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }

    //metoden kalder sin søstermetode med to input
    public static void executeQueryWithResultSet(Queryable queryable){
        executeQueryWithResultSet(queryable.returnSqlQuery(), queryable);
    }
    
    // opretter forbindelse til databasen og henter data med de overskrevne metoder fra interfacet
    public static void executeQueryWithResultSet(String sqlStatement, Queryable queryable){
        Connection conn = getConnection();
        
        Statement stmt = null;
        ResultSet rs = null;
        
        if (conn != null) {
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlStatement);   // Anvender SELECT puls. temp, sys FROM BiomarkDatabase WHERE cpr==true
                queryable.processResultSet(rs);         // Anvender rs.getInt("puls"), rs.getString("fornavn") osv.
            } catch (SQLException sqlex) {
                System.out.println(sqlex.getMessage());
            }
            finally {
                try {
                    rs.close();                         // lukker forbindelsen til databasen igen
                } catch (SQLException sqlex) {
                    System.out.println(sqlex.getMessage());
                }
            }
        }
        
        
        
    }
    
}