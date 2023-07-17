package com.dhaharpedia;

import org.json.JSONObject;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;

abstract class User {
    protected String noHp;
    protected String nama;
    protected String Role;
    protected String Makanan_favorit;
    protected String password;
    protected boolean isSignIn = false;

    private String DB_URL = "jdbc:mysql://localhost/Dhaharpedia";
    private String USER = "root";
    private String PASS = ""; 
    protected JSONArray RestoRekomendasi = new JSONArray();
    protected ArrayList<java.sql.Blob> RestoGambar = new ArrayList<java.sql.Blob>();


    protected Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    };
    
    

    public abstract void Restaurant_Rekomendasi();

    public ArrayList<java.sql.Blob> getGambarResto(){
        return this.RestoGambar;
    }


    public JSONArray getRestoRekomendasi(){
        return this.RestoRekomendasi;
    };

    public boolean isSignIn(){
        return this.isSignIn;
    }

    public void Login(){
        this.isSignIn = true;
    }
    public void Logout(){
        this.isSignIn = false;
    }

    public JSONArray SortByKategori(String Kategori){
            JSONArray dataArray = new JSONArray(); 
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = this.getConnection();
            System.out.print(conn);
            
            try {
                stmt = conn.createStatement();
                String sql = "SELECT *  from Restaurant Where `Kategori_Restoran` = '"+ Kategori+ "' ORDER BY `Rating_Restoran` DESC;";
                rs = stmt.executeQuery(sql);
                // Id_Restoran,Nama_Restoran,Rating_Restoran,Kontak_Restoran,Kategori_Restoran,Alamat_Restoran,jumlah_rating
                
                while (rs.next()) {
                    JSONObject data = new JSONObject();
                    data.put("id", rs.getInt("Id_Restoran"));
                    data.put("nama", rs.getString("Nama_Restoran"));
                    data.put("rating", rs.getFloat("Rating_Restoran"));
                    data.put("kontak", rs.getString("Kontak_Restoran"));
                    data.put("kategori", rs.getString("Kategori_Restoran"));
                    data.put("alamat", rs.getString("Alamat_Restoran"));
                    data.put("jumlahrating", rs.getInt("jumlah_rating"));
                    dataArray.put(data);
                 }
                //System.out.println("Jumlah JSON : " + this.RestoRekomendasi.length());
                //System.out.println("Jumlah ArrayList : " + this.RestoGambar.size());
                //System.out.println("Jumlah ArrayList : " + this.RestoRekomendasi);//.getJSONObject(1));//getInt("harga"));
                
                stmt.close();
                rs.close();
                conn.close();
               
            } catch(SQLException error){
                System.out.println("hello Maaf :( Sistem baru Eror" + error);

            }
            System.out.print(dataArray);
          return dataArray;  
        };


    public JSONArray SortByRating(){
        JSONArray dataArray = new JSONArray(); 
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = this.getConnection();
            System.out.print(conn);
            
            try {
                stmt = conn.createStatement();
                String sql = "SELECT *  from Restaurant ORDER BY `Rating_Restoran` DESC;";
                rs = stmt.executeQuery(sql);
                // Id_Restoran,Nama_Restoran,Rating_Restoran,Kontak_Restoran,Kategori_Restoran,Alamat_Restoran,jumlah_rating
                
                while (rs.next()) {
                    JSONObject data = new JSONObject();
                    data.put("id", rs.getInt("Id_Restoran"));
                    data.put("nama", rs.getString("Nama_Restoran"));
                    data.put("rating", rs.getFloat("Rating_Restoran"));
                    data.put("kontak", rs.getString("Kontak_Restoran"));
                    data.put("kategori", rs.getString("Kategori_Restoran"));
                    data.put("alamat", rs.getString("Alamat_Restoran"));
                    data.put("jumlahrating", rs.getInt("jumlah_rating"));
                    dataArray.put(data);
                 }
                //System.out.println("Jumlah JSON : " + this.RestoRekomendasi.length());
                //System.out.println("Jumlah ArrayList : " + this.RestoGambar.size());
                //System.out.println("Jumlah ArrayList : " + this.RestoRekomendasi);//.getJSONObject(1));//getInt("harga"));
                
                stmt.close();
                rs.close();
                conn.close();
               
            } catch(SQLException error){
                System.out.println("hello Maaf :( Sistem baru Eror" + error);

            }
            System.out.print(dataArray);
          return dataArray;  

    };


    


    
}
