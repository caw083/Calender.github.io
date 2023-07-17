package com.dhaharpedia;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

public class Admin extends User{
    @Override
    public void Restaurant_Rekomendasi(){
        if (this.RestoRekomendasi.length() == 0){
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = super.getConnection();
            
            try {
                stmt = conn.createStatement();
                String sql = "SELECT * from Restaurant Where `Kategori_Restoran` = "+ this.Makanan_favorit+ " ORDER BY `Rating_Restoran` DESC;";
                rs = stmt.executeQuery(sql);
                
                while (rs.next()) {
                    JSONObject data = new JSONObject();
                    data.put("id", rs.getInt("Id_Restoran"));
                    data.put("nama", rs.getString("Nama_Restoran"));
                    data.put("rating", rs.getFloat("Rating_Restoran"));
                    data.put("kontak", rs.getString("Kontak_Restoran"));
                    data.put("kategori", rs.getString("Kategori_Restoran"));
                    data.put("alamat", rs.getString("Alamat_Restoran"));
                    data.put("jumlahrating", rs.getInt("jumlah_rating"));
                    this.RestoGambar.add(rs.getBlob("gambar"));
                    this.RestoRekomendasi.put(data);
                 }
                //System.out.println("Jumlah JSON : " + this.RestoRekomendasi.length());
                //System.out.println("Jumlah ArrayList : " + this.RestoGambar.size());
                //System.out.println("Jumlah ArrayList : " + this.RestoRekomendasi);//.getJSONObject(1));//getInt("harga"));
                
                stmt.close();
                rs.close();
                conn.close();
               
            } catch(SQLException error){
                System.out.println("hello Maaf :( Sistem baru Eror");

            }
        }
    }
    

   

    public void CreateRestaurant(){};
    public void UpdateRestaurant(){};
    public void DeleteRestaurant(){};

    public Admin(String noHP, String password){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = super.getConnection();
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * from User Where noHP = '"+ String.valueOf(noHP)+"'";
            rs = stmt.executeQuery(sql);
        
            while (rs.next()) {
                if (password.equals(rs.getString("password")) && rs.getString("Role").equals("Admin")){
                    this.noHp = rs.getString("noHp");
                    this.nama = rs.getString("Nama_User");
                    this.Role = rs.getString("Role");
                    this.Makanan_favorit = rs.getString("Makanan_Favorit");
                    this.password = rs.getString("password");
                    this.Login();
                    this.Restaurant_Rekomendasi(); 
                    System.out.println(this.Makanan_favorit+this.Role+ this.isSignIn+ this.password);


                } else {
                    System.out.println("maaf password salah");
                }

             }
            
            stmt.close();
            rs.close();
            conn.close();
           
        } catch(SQLException error){
            System.out.println("hello Maaf :( Sistem baru Eror"+ error);

        }

       
        
    }


}
