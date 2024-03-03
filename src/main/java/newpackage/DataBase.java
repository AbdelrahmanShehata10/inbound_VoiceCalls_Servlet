/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author heba
 */
public class DataBase {

 static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "root";


    public Connection connectDB() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return conn;
    }

     String[] getcred() throws SQLException {
        String[] info = new String[3];

        String sql = "SELECT * FROM credentials";
        Statement statement = connectDB().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String auth_token = resultSet.getString("AUTH_TOKEN");
            String auth_sid = resultSet.getString("ACCOUNT_SID");
            info[0] = auth_token;
            info[1] = auth_sid;

            System.out.println(auth_token);
            System.out.println(auth_sid);

        }

        return info;
    }
     
     void insertToDB(String accountSID, String CallSid,String sender,String from, String CallDuration) throws SQLException {

        String sql = "INSERT INTO call_log (accountSID,CallSid,senderr,recieverr,CallDuration ) VALUES (?,?,?,?,?)";

        try (Connection connection = connectDB(); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

              preparedStatement.setString(1, accountSID);
            preparedStatement.setString(2, CallSid);
            preparedStatement.setString(3, sender);
               preparedStatement.setString(4, from);
            preparedStatement.setString(5, CallDuration);

            int DataAffected = preparedStatement.executeUpdate();

            if (DataAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     
       public ResultSet getcalls() {

        Connection conn = connectDB();
        boolean findsms=false;
         ResultSet resultSet = null;

        String sql = "select * from call_log;";
        try {
            Statement statement = conn.createStatement();
             resultSet = statement.executeQuery(sql);
  
        } catch (SQLException ex) {

            ex.printStackTrace();
            findsms= false;
        }
        return resultSet;
    }
       
   

}