-- Active: 1682419299217@@127.0.0.1@3306
Use Dhaharpedia;

-- Restoran
Select * from Menu_Restoran 
where Id_Restoran = 2;

-- Guess
Select * from Restaurant
ORDER BY Rating_Restoran DESC;

-- User
SELECT * from Restaurant
WHERE  Kategori_Restoran = "Western"
ORDER BY Rating_Restoran DESC;

