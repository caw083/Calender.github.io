def StringKode(id_menu,Penjelasan, harga, gambar):
    print("INSERT INTO Menu_Restoran Value")
    print(f"""(2,{id_menu},"{Penjelasan}",{harga},LOAD_FILE('/var/lib/mysql-files/Image/KFC/makanan/{gambar}') );  """)
    return ""
menu = 1
while True:
    penjelasan = str(input("Masukan penjelasan :"))
    harga = int(input("masukan harga :"))
    gambar = str(input("Masukan nama file gambar :"))
    StringKode(menu, penjelasan, harga, gambar)
    menu += 1