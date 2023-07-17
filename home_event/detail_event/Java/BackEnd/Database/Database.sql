-- Active: 1682419299217@@127.0.0.1@3306
CREATE DATABASE Dhaharpedia;

USE Dhaharpedia;

-- Tabel Database--
CREATE TABLE Restaurant(
    Id_Restoran INT PRIMARY KEY,
    Nama_Restoran VARCHAR(100) NOT NULL,
    Rating_Restoran FLOAT NOT NULL,
    Kontak_Restoran VARCHAR(100) NOT NULL,
    Kategori_Restoran VARCHAR(100) NOT NULL,
    Alamat_Restoran VARCHAR(100) NOT NULL
);

CREATE TABLE Menu (
    Id_Menu INT PRIMARY KEY,
    Nama_Menu VARCHAR(100) NOT NULL,
    Kategori_Menu VARCHAR(100) NOT NULL,
    Harga INT NOT NULL
);

CREATE TABLE User(
    noHp VARCHAR(20) PRIMARY KEY,
    Nama_User VARCHAR(100) NOT NULL,
    Role VARCHAR(20) NOT NULL,
    Makanan_Favorit VARCHAR(100) NOT NULL,
    Email_User VARCHAR(100) NOT NULL    
);

CREATE TABLE Rating(
    no_HP VARCHAR(20),
    Id_Restoran INT,
    Rating FLOAT,
    Ulasan TEXT, 
    PRIMARY KEY(no_HP, Id_Restoran),
    FOREIGN KEY (Id_Restoran) REFERENCES Restaurant(Id_Restoran),
    FOREIGN KEY (no_Hp) REFERENCES User(noHP)
);

CREATE TABLE Orders(
    no_HP VARCHAR(20),
    Id_Restoran INT,
    Id_Menu INT,
    Jumlah int NOT NULL,
    PRIMARY KEY(no_HP, Id_Restoran, Id_Menu),
    FOREIGN KEY (Id_Restoran) REFERENCES Restaurant(Id_Restoran),
    FOREIGN KEY (no_Hp) REFERENCES User(noHP),
    FOREIGN KEY (Id_Menu) REFERENCES Menu(Id_Menu)
);

CREATE TABLE Menu_Restoran(
    Id_Restoran INT,
    Id_Menu INT,
    Penjelasan TEXT,
    PRIMARY KEY(Id_Restoran, Id_Menu),
    FOREIGN KEY (Id_Restoran) REFERENCES Restaurant(Id_Restoran),
    FOREIGN KEY (Id_Menu) REFERENCES Menu(Id_Menu)
);

ALTER TABLE User MODIFY Role ENUM("Admin", "Customers") DEFAULT 'Customers';
ALTER TABLE User ADD COLUMN password VARCHAR(20);

SET FOREIGN_KEY_CHECKS = 0;
Alter TABLE Menu MODIFY Id_Menu INT AUTO_INCREMENT;
SET FOREIGN_KEY_CHECKS = 1;
ALTER TABLE Menu DROP COLUMN harga;

Alter TABLE Menu MODIFY Nama_Menu VARCHAR(100) UNIQUE NOT NULL;

ALTER TABLE Menu_Restoran ADD COLUMN gambar text;
Alter table Menu_Restoran MODIFY gambar longblob not null;

ALTER TABLE Menu_Restoran ADD COLUMN harga int NOT NULL;

Alter TABLE Restaurant ADD COLUMN gambar text;
alter table Restaurant add column jumlah_rating int;
Alter table Restaurant MODIFY gambar longblob NOT NULL;
-- Syntax Logic
INSERT into User (noHp, Nama_User, Role, Makanan_Favorit,Email_User, password) VALUES 
("088216323520", "Christopher", "Admin", "Steak", "christotopher083@gmail.com", "eyfb22Q0F*" ),
('087715300989', "Andreas Anditya P", "Admin", "Roti", "andreas.anditya@ti.ukdw.ac.id", "get128RE*"),
("085879508547", "Gian Pradipta P", "Admin", "Burger", "gian.pradipta@ti.ukdw.ac.id","pot873rO-" ),
("082111249178", "Stefanus Yandi", "Customers", "Mie", "stefanus.yandi@ti.ukdw.ac.id", "yes01832Tp>" ),
("081575811655", "Kevin Christian", "Customers", "Ice Cream", "kevin.christian@ti.ukdw.ac.id", "kevin01924Tp<") ;


-- Menu MD
INSERT into Menu (Nama_Menu, Kategori_Menu) VALUES
('McFlurry feat. Oreo', "Desert"),
("McFlurry Choco", "Desert"),
("BigMac", "Western"),
("PaNas 1","Western"),
("PaNas 2 with Fries", "Western"),
("PaNas 2 with Rice", "Western"),
("Happy Meal Chicken", "Western"),
("French Fries", "Snack"),
("Hot Tea", "Tea and Coffee"),
("Iced Coffee/Chocolate Float", "Tea and Coffee");

-- Menu KFC
INSERT into Menu (Nama_Menu, Kategori_Menu) VALUES
("9pcs Bucket", "Western"),
("Colonel Burger", "Western"),
("Colonen Yakiniku", "Japanese"),
("Snack Bucket BBQ", "Western"),
("Chococha Float", " Beverage"),
("Mocha Float", "Tea and Coffee"),
("Manga Float", "Juice"),
("Super Breakfast Combo", "Western"),
("Double Choco Donut", "Bread")
;


-- menu Moen-Moen

insert into Restaurant VALUES
("1", "Mc Donald's", 4.0, "08111927525", "Western","Jl. Jend. Sudirman No.38, Kotabaru, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55224",LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/Restoran/McDonalds.jpg'),1000 );

insert into Restaurant VALUES
("2", "KFC", 4.5, "02114022","Western" ,"Jl. Jend. Sudirman No.69, Terban, Kec. Gondokusuman, Kota Yogyakarta, Daerah Istimewa Yogyakarta 55223",LOAD_FILE('/var/lib/mysql-files/Image/KFC/Restoran/Kfc.jpg'),1000);

ALTER TABLE Restaurant MODIFY column  Alamat_Restoran text not null;
ALTER TABLE Restaurant MODIFY column  jumlah_rating int not null;

ALTER TABLE Menu AUTO_INCREMENT = 1;
DELETE from Menu;
DESCRIBE Menu;
DESCRIBE User;
DESCRIBE Restaurant;




Insert into Menu_Restoran VALUES
(1,1, "Es krim vanilla lembut dengan campuran biskuit Oreo Crumbs", 13500, LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/McFlurry_feat._Oreo.png'));


Insert into Menu_Restoran VALUES
(1,2, "Es krim vanilla lembut dengan campuran bubuk coklat", 13500, LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/McFlurryChocho.png'));

Insert into Menu_Restoran VALUES
(1,3, "Burger Ikonik McDonald's. Dua lapis daging sapi gurih disajikan dengan saus spesial, selada segar, keju, acar timun, bawang, diapit roti bertabur wijen", 34500, LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/BigMac.png'));



INSERT INTO Menu_Restoran Value
(1,4,"Ayam Goreng khas McDonald's dengan nasi hangat dan minuman FruitTea lemon yang menyegarkan. Tersedia dalam pilihan Ayam Krispy atau Spicy",32000,LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/Panas1.png'));

INSERT INTO Menu_Restoran Value
(1,5,"2 pcs Ayam Goreng khas McDonald's dengan kentang goreng dan minuman FruitTea lemon yang menyegarkan. Tersedia dalam pilihan Ayam Krispy atau Spicy",46500,LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/Panas2Fries.png'))   ;

INSERT INTO Menu_Restoran Value
(1,6,"2 pcs Ayam Goreng khas McDonald's dengan nasi hangat dan minuman FruitTea lemon yang menyegarkan. Tersedia dalam pilihan Ayam Krispy atau Spicy",44000,LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/Panas2Rice.png') );  

INSERT INTO Menu_Restoran Value
(1,7,"Menu seimbang untuk Si Kecil dengan Ayam goreng McD, Nasi hangat, Susu UHT dan hadiah mainan atau buku",35500,LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/HappyMealChicken.png') );  

INSERT INTO Menu_Restoran Value
(1,8,"Kentang goreng renyah dan gurih",10500,LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/FrenchFries.png') );  

INSERT INTO Menu_Restoran Value
(1,9,"Teh melati yang tersaji panas dan dapat ditambahkan gula sesuai selera.",7000,LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/HotTea.png') );  

INSERT INTO Menu_Restoran Value
(1,10,"Kesegaran es kopi dari 100% Arabica khas McCafe",18000,LOAD_FILE('/var/lib/mysql-files/Image/MacDonald/makanan/IcedCoffee.png') );  

INSERT INTO Menu_Restoran Value
(2,11,"9 potong ayam dalam bucket",139545,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/9pcsBucket.png') );  

INSERT INTO Menu_Restoran Value
(2,12,"Colonel Burger khas KFC",34000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/ColonelBurger.png') );  

INSERT INTO Menu_Restoran Value
(2,13,"Potongan daging ayam KFC yang dilumuri sama saus yakiniku",30000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/colonelyakinikuc.png') );  
INSERT INTO Menu_Restoran Value
(2,14,"Renyah dan lezatnya ayam goreng KFC dibalur dengan bumbu Sweet and Spicy ala Korea yang rasanya PAS di mulut.",72000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/snackbucket1bbq.png') );  
INSERT INTO Menu_Restoran Value
(2,15," Chokocha Float terdiri dari campuran cokelat dan matcha yang menghadirkan rasa manis yang unik dan lezat. ",13000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/chococha-float.png') );  
INSERT INTO Menu_Restoran Value
(2,16,"es kopi mocha dengan tambahan es krim vanila",12000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/mochafloat.png') );  
INSERT INTO Menu_Restoran Value
(2,17,"Jus manga yang menyegarkan ditambah dengan es krim vanila ",13500,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/mangofloat.png') );  
INSERT INTO Menu_Restoran Value
(2,18,"Nasi + Ayam + Scrambled Egg + Minuman",31000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/super-breakfast-combo.png') );  

INSERT INTO Menu_Restoran Value
(2,19,"Double Choco Donut yang memili coklat 2 lapis",8000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/double-choco-donut.png') );  

INSERT INTO Menu_Restoran Value
(2,8,"Kentang French Fries dengan rasa yang khas",12000,LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/frenchfries.png') );  


SELECT Id_Restoran, Id_Menu, Penjelasan, harga from Menu_Restoran;

INSERT INTO Orders Value
("082111249178", 1, 1, 3);

INSERT INTO Rating VALUES
("082111249178", 1, 4.5, "Pelayanannya ramah masakannya berkualitas");

SHOW TRIGGERS;

delete from Rating;

Use Dhaharpedia;
DELIMITER //
CREATE TRIGGER AfterInsert
AFTER INSERT ON Rating
FOR EACH ROW
BEGIN
    UPDATE Restaurant
    SET Rating_Restoran = (Rating_Restoran * jumlah_rating + NEW.Rating)/ (jumlah_rating + 1),
        jumlah_rating = jumlah_rating + 1
    WHERE Id_Restoran = NEW.Id_Restoran;
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterDelete
AFTER DELETE ON Rating
FOR EACH ROW
BEGIN
    UPDATE Restaurant
    SET Rating_Restoran = (Rating_Restoran * jumlah_rating - OLD.Rating)/ (jumlah_rating - 1),
        jumlah_rating = jumlah_rating - 1
    WHERE Id_Restoran = OLD.Id_Restoran;
END//
DELIMITER ;


DELIMITER //
CREATE TRIGGER AfterUpdate
AFTER UPDATE ON Rating
FOR EACH ROW
BEGIN
    UPDATE Restaurant
    SET Rating_Restoran = (Rating_Restoran * jumlah_rating - OLD.Rating + NEW.Rating)/ (jumlah_rating)
    WHERE Id_Restoran = OLD.Id_Restoran;
END//
DELIMITER ;

Update Rating
SET Rating = 3.5 
WHERE no_HP = "082111249178";

show TRIGGERS;

drop TRIGGER UpdateRating;

UPDATE Restaurant
SET Rating_Restoran = 4,
    jumlah_rating = 1000
WHERE Id_Restoran = 1;

