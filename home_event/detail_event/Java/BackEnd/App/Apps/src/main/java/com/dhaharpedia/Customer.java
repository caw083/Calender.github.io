package com.dhaharpedia;

import org.json.JSONArray;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONObject;
import java.sql.PreparedStatement;

public class Customer extends User {
    private JSONArray rating = new JSONArray();

    public JSONArray getUlasan(){
        return rating;
    }
   
    @Override
    public void Restaurant_Rekomendasi(){
        if (this.RestoRekomendasi.length() == 0){
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = super.getConnection();
            
            try {
                stmt = conn.createStatement();
                String sql = "SELECT * from Restaurant Where `Kategori_Restoran` = '"+ this.kategori(this.Makanan_favorit)+ "' ORDER BY `Rating_Restoran` DESC;";
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
                sql = "SELECT  Ulasan, Nama_User, no_HP, Rating, Id_Restoran from Rating LEFT join `User` on Rating.no_HP = User.noHp where no_HP  = '"+ this.noHp+"';";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    JSONObject data = new JSONObject();
                    data.put("ulasan", rs.getString("Ulasan"));
                    data.put("nama_user", rs.getString("Nama_User"));
                    data.put("no_hp", rs.getString("no_HP"));
                    data.put("rating", rs.getFloat("Rating"));
                    data.put("id_resto", rs.getInt("Id_Restoran"));
                    this.rating.put(data);
                 }
                 System.out.println("helo"+this.rating);


                stmt.close();
                rs.close();
                conn.close();
               
            } catch(SQLException error){
                System.out.println("hello Maaf :( Sistem baru Eror");

            }
        }
    }
    // nanti tambah ini
    protected String kategori(String makanan){
        return "Western";
    };


    public void CreateRating(JSONObject data, Restaurant resto){
        System.out.println(data+" ID "+ resto.getIdResto());
        data.put("no_hp", this.noHp);
        data.put("id_resto", resto.getIdResto());
        this.rating.put(data);
        System.out.println(this.rating);
        
        //String sqli = "INSERT INTO Rating VALUES('"+this.noHp+"'',"+resto.getIdResto()+","+ data.getFloat("Rating")+", '"+data.getString("Ulasan")+"');";
        //System.out.println(sqli);


        Connection conn = super.getConnection();
        try {
            String sql = "INSERT INTO Rating VALUES('"+this.noHp+"',"+resto.getIdResto()+","+ data.getFloat("rating")+", '"+data.getString("ulasan")+"');";
            System.out.println(sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " rows Update.");
        } catch (SQLException e){
            System.out.println(e);
        }




    };

    public void UpdateRating(JSONObject data, Restaurant resto){
        System.out.println(this.rating);
        System.out.println(data);

        for ( Object obj : this.rating){
            JSONObject jsonObject = (JSONObject) obj;
            int idResto = jsonObject.getInt("id_resto");
            System.out.println("\n bajing"+idResto);
            
            if (idResto == resto.getIdResto()) {
                double rating = jsonObject.getDouble("rating");
                System.out.println("Rating sebelum dimodifikasi: " + rating);
                
                // Modifikasi nilai properti rating
                jsonObject.put("rating", data.getFloat("rating"));
                jsonObject.put("ulasan", data.getString("ulasan"));
                rating = jsonObject.getDouble("rating");
                System.out.println("Rating setelah dimodifikasi: " + rating);
                break;
            }
            System.out.println(this.rating);
        }



    };

    public void DeleteRating(Restaurant resto){

        int nomorhapus = 0;
        for ( Object obj : this.rating){
            JSONObject jsonObject = (JSONObject) obj;
            int idResto = jsonObject.getInt("id_resto");
            
            if (idResto == resto.getIdResto()) {
                this.rating.remove(nomorhapus);
                Connection conn = super.getConnection();
                try {
                    String sql = "DELETE FROM Rating where Id_Restoran ="+idResto+" And no_HP = '"+this.noHp+"'";
                    System.out.println(sql);
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                  
      
                    int rowsAffected = pstmt.executeUpdate();
                    System.out.println(rowsAffected + " rows deleted.");
                } catch (SQLException e){
                    System.out.println(e);
                }



                break;
            }
            nomorhapus += 1;
        }
        System.out.println(this.rating);

    };


    public Customer(String noHP, String password){
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = super.getConnection();
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * from User Where noHP = '"+ String.valueOf(noHP)+"'";
            rs = stmt.executeQuery(sql);
        
            while (rs.next()) {
                if (password.equals(rs.getString("password")) && rs.getString("Role").equals("Customers")){
                    this.noHp = rs.getString("noHp");
                    this.nama = rs.getString("Nama_User");
                    this.Role = rs.getString("Role");
                    this.Makanan_favorit = rs.getString("Makanan_Favorit");
                    this.password = rs.getString("password");
                    this.Login();
                    this.Restaurant_Rekomendasi(); 
                    this.getUlasan();
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
