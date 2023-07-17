package com.dhaharpedia;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

public class Restaurant extends ConnectionDatabase{

    private int ID;
    private String Nama;
    private String NomorKontak;
    private String Alamat;
    private float Rating;
    private int JumlahRating;
    private java.sql.Blob GambarResto;

    // data gambar
    // private bytes Gambar
    private JSONArray Menu_Resto = new JSONArray();
    private ArrayList<java.sql.Blob> Gambar_Menu = new ArrayList<java.sql.Blob>();
    private JSONArray Ulasan = new JSONArray();

    // Isi dengan Array
    public Restaurant(JSONObject Data, java.sql.Blob Resto){
        this.ID = Data.getInt("id");
        this.NomorKontak = Data.getString("kontak");
        this.Alamat = Data.getString("alamat");
        this.Rating = Data.getFloat("rating");
        this.JumlahRating = Data.getInt("jumlahrating");
        this.Nama = Data.getString("nama");

        this.GambarResto = Resto;
        this.getData();
        
    }



    @Override
    protected void getData(){
        if (this.Menu_Resto.length() == 0){
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = super.getConnection();
            try {
                stmt = conn.createStatement();
                String sql = "SELECT Nama_Menu,Penjelasan,Harga,Kategori_Menu,gambar from Menu_Restoran right join Menu On Menu_Restoran.Id_Menu = Menu.Id_Menu WHERE Menu_Restoran.Id_Restoran ="+ String.valueOf(this.ID)+";";
                rs = stmt.executeQuery(sql);
                
                while (rs.next()) {
                    JSONObject data = new JSONObject();
                    data.put("nama menu", rs.getString("Nama_Menu"));
                    data.put("penjelasan", rs.getString("Penjelasan"));
                    data.put("kategori", rs.getString("Kategori_Menu"));
                    data.put("harga", rs.getInt("Harga"));
                    this.Gambar_Menu.add(rs.getBlob("gambar"));
                    this.Menu_Resto.put(data);
                 }
                //System.out.println("Jumlah JSON : " + this.Menu_Resto.length());
                //System.out.println("Jumlah ArrayList : " + this.Gambar_Menu.size());
                System.out.println("Jumlah ArrayList : " + this.Menu_Resto);//getJSONObject(1));//getInt("harga"));
                

                sql = "SELECT  Ulasan, Nama_User, no_HP, Rating from Rating LEFT join `User` on Rating.no_HP = User.noHp where Id_Restoran  = "+ String.valueOf(this.ID)+";";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    JSONObject data = new JSONObject();
                    data.put("ulasan", rs.getString("Ulasan"));
                    data.put("nama_user", rs.getString("Nama_User"));
                    data.put("no_hp", rs.getString("no_HP"));
                    data.put("rating", rs.getFloat("Rating"));
                    this.Ulasan.put(data);
                 }
                 System.out.println(this.Ulasan);


                
                stmt.close();
                rs.close();
                conn.close();
            }   
            catch(SQLException se){
                System.out.println("Tunggu beberapa saat terjadi eror di Server");
            }
        }
    
    };

    public JSONArray getMenu(){
        return this.Menu_Resto;
    }

    public ArrayList<java.sql.Blob> getGambarMenu(){
        return this.Gambar_Menu;
    }

    public int getIdResto(){
        return this.ID;
    };
    public float getRating(){
        return this.Rating;
    }

    public int getJumlahRating(){
        return this.JumlahRating;
    }


    // if User == Admin then do this Operation 
    // else {close}

    public void InsertMenu(){}
    public void UpdateMenu(){}
    public void DeleteMenu(){}
}
