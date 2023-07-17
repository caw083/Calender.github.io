import java.sql.SQLException;
import java.sql.Statement;
import  java.sql.ResultSet;

public class Restaurant extends DatabaseConnection{
    // Data Restoran
    private int id;
    private String nama;
    private String Kategori;

    private Statement stmt = null;
    private ResultSet rs = null;


    // @Override
    // protected void ReadData(){
    //     try {
       
    //         System.out.println(super.getConnection());
    //         stmt = super.getConnection().createStatement();
    //         System.out.println("Access table in given database...");
    //         String sql = "SELECT * FROM Menu";
    //         rs = stmt.executeQuery(sql);
    
    //         while (rs.next()) {
    //             String id = rs.getString("Id_Menu");
    //             String name = rs.getString("Nama_Menu");
    //             String age = rs.getString("Kategori_Menu");
    //             System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
    //         }
    
    //     }
    //         catch(SQLException e) {
    //             e.printStackTrace();
    //         }
        
    // };

    public static void main(String[] args) {
       Restaurant A = new Restaurant();
    //    A.ReadData();
     }


}

