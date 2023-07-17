package App ;

import java.sql.DriverManager;
import java.sql.Statement;
import  java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

public class DataConnect {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost/Dhaharpedia";
   static final String USER = "root";
   static final String PASS = "";

   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
         Class.forName("com.mysql.jdbc.Driver");

         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL,USER,PASS);

         System.out.println("Access table in given database...");
         stmt = conn.createStatement();

         String sql = "SELECT * FROM Menu";
         rs = stmt.executeQuery(sql);

         System.out.println("hello " + rs);

         

         


        
        

      } catch(SQLException se) {
         se.printStackTrace();
      } catch(Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if(stmt!=null)
               conn.close();
         } catch(SQLException se) {
         }
         try {
            if(conn!=null)
               conn.close();
         } catch(SQLException se) {
            se.printStackTrace();
         }
      }
      System.out.println("Goodbye!");
   }
}