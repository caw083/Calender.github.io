package com.dhaharpedia;

import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;


public class App {

   public static void main(String[] args) {
    
      //Restaurant a = new Restaurant();
      //a.ReadMenu();

      Restaurant[] neko = new Restaurant[2];


      Guest b = new Guest();
      JSONArray Rekomendasi = b.getRestoRekomendasi();
      ArrayList<java.sql.Blob> RestoGambar = b.getGambarResto();
      System.out.println(Rekomendasi);

      for (int i= 0; i< Rekomendasi.length(); i++){
        System.out.println(i+" ="+Rekomendasi.getJSONObject(i));
        Restaurant a = new Restaurant(Rekomendasi.getJSONObject(i), RestoGambar.get(i));
        neko[i] = a;
      }
      System.out.println(neko);

      System.out.println("");


      //b.SortByKategori("Western");
      //
      b.SortByRating();
      
      System.out.println("");
      Customer Kevin = new Customer("081575811655","kevin01924Tp<" );
      System.out.println("");
      Customer Yandi = new Customer("082111249178", "yes01832Tp>");

      JSONObject data = new JSONObject();
      data.put("rating", 4.5);
      data.put("ulasan", "Es krimnya enak");

      Kevin.CreateRating(data, neko[1]);
      System.out.println("Taik" +Kevin.getUlasan());
      

      System.out.println("");
      System.out.println("Yandi Update Resto");
      System.out.println("");
      data = new JSONObject();
      data.put("rating", 3.0);
      data.put("ulasan", "Apa beli burger besar dapet kecil");
      Kevin.UpdateRating(data, neko[1]);
      Kevin.DeleteRating(neko[1]);


        
   }
}

