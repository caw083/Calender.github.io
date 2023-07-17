package com.dhaharpedia;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;

public class Guest extends User{

    public Guest (){
        this.Restaurant_Rekomendasi();
    }
    @Override
    public void Restaurant_Rekomendasi(){
        if (this.RestoRekomendasi.length() == 0){
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = super.getConnection();
            
            try {
                stmt = conn.createStatement();
                String sql = "SELECT * from Restaurant ORDER BY Rating_Restoran DESC;";
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
                System.out.println("hello Maaf :( Sistem baru Eror"+ error);

            }
        }

       
    }

}
